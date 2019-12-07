import keras
from keras.models import model_from_json
import numpy as np
from netCDF4 import Dataset
import matplotlib.pyplot as plt
import glob, sys
import cartopy.crs as ccrs



def scale_bar(ax, length=None, location=(0.5, 0.05), linewidth=3):
    """
    ax is the axes to draw the scalebar on.
    length is the length of the scalebar in km.
    location is center of the scalebar in axis coordinates.
    (ie. 0.5 is the middle of the plot)
    linewidth is the thickness of the scalebar.
    credits to https://stackoverflow.com/users/4278282/siyh
    """
    #Get the limits of the axis in lat long
    llx0, llx1, lly0, lly1 = ax.get_extent(ccrs.PlateCarree())
    #Make tmc horizontally centred on the middle of the map,
    #vertically at scale bar location
    sbllx = (llx1 + llx0) / 2
    sblly = lly0 + (lly1 - lly0) * location[1]
    tmc = ccrs.TransverseMercator(sbllx, sblly)
    #Get the extent of the plotted area in coordinates in metres
    x0, x1, y0, y1 = ax.get_extent(tmc)
    #Turn the specified scalebar location into coordinates in metres
    sbx = x0 + (x1 - x0) * location[0]
    sby = y0 + (y1 - y0) * location[1]

    #Calculate a scale bar length if none has been given
    #(Theres probably a more pythonic way of rounding the number but this works)
    if not length: 
        length = (x1 - x0) / 5000 #in km
        ndim = int(np.floor(np.log10(length))) #number of digits in number
        length = round(length, -ndim) #round to 1sf
        #Returns numbers starting with the list
        def scale_number(x):
            if str(x)[0] in ['1', '2', '5']: return int(x)        
            else: return scale_number(x - 10 ** ndim)
        length = scale_number(length) 

    #Generate the x coordinate for the ends of the scalebar
    bar_xs = [sbx - length * 500, sbx + length * 500]
    #Plot the scalebar
    ax.plot(bar_xs, [sby, sby], transform=tmc, color='k', linewidth=linewidth)
    #Plot the scalebar label
    ax.text(sbx, sby, str(length) + ' km', transform=tmc,
            horizontalalignment='center', verticalalignment='bottom')

## parameters
Colour_data_shape = (4416,5664) # shape of output data (needed for plotting)
current_path = 'data/colour/currentData/' 	# path to current data file
output_path = '../presentation/swing/'
console_path = ''
model_path = ''					# path to trained model
insitu_input = 0 				# insitu data input (for now random)
max_level = 1	# maximum CHL concentration which should trigger an email
#################################################

# write console output to txt file
sys.stdout = open(console_path+'console_output_use_model.txt', 'w')

# load JSON file with model
json_file = open(model_path+'model.json', 'r')
loaded_model_json = json_file.read()
json_file.close()
loaded_model = model_from_json(loaded_model_json)

# load weights into new model
loaded_model.load_weights("model.h5")
print("Loaded model from disk")

# load CHL levels from colour data
current_files = [f for f in glob.glob(current_path + "*.nc")]
colour_input_data = Dataset(current_files[-1])
CHL_levels = colour_input_data.variables["CHL"][0]
lats = colour_input_data.variables['lat'][:]
lons = colour_input_data.variables['lon'][:]

X = np.append(CHL_levels.flatten(), insitu_input)

# predict CHL concentrations for the next day
forecast = loaded_model.predict(np.expand_dims(X,axis=0))

# plot yesterdays forecast
ax = plt.axes(projection=ccrs.PlateCarree())
plt.contourf(lons, lats, np.log(CHL_levels), 60, transform=ccrs.PlateCarree())
print('plot created')
ax.coastlines()
scale_bar(ax,1000)
plt.savefig(output_path+"model_input.png")

# produce plot of predictions
print("generating forecast")
ax = plt.axes(projection=ccrs.PlateCarree())
forecast[forecast==np.min(forecast)] = np.nan
plt.contourf(lons, lats, forecast.reshape((4416, 5664)), 60, transform=ccrs.PlateCarree())
ax.coastlines()
scale_bar(ax,1000)
#Image needs to be saved on presentation/swing
plt.savefig(output_path+"predictions_CHL_next_day.png")

# produce plot of high CHL concentration regions
print("computing regions with high CHL concentrations")
forecast[forecast < max_level] = np.nan
forecast[forecast >= max_level] = 1
ax = plt.axes(projection=ccrs.PlateCarree())
plt.contourf(lons, lats, forecast.reshape((4416, 5664)), 60, transform=ccrs.PlateCarree())
ax.coastlines()
scale_bar(ax,1000)
plt.savefig(output_path+'predictions_high_CHL_locations.png')

#Sending Email

if forecast[forecast >= max_level].all(0)==True:
    
   CHL_Max_Limit=-1
    

#plt.imshow(forecast.reshape((4416, 5664)))


passToJava="forecastVAlue"+str(CHL_Max_Limit)

print(passToJava)


# close filewriter
sys.stdout.close()