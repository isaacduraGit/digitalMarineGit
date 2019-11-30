import numpy as np
from netCDF4 import Dataset
import glob, sys
import cartopy.crs as ccrs
import matplotlib as mpl
mpl.use('Agg') # no UI backend
import matplotlib.pyplot as plt

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


import os
curDir = os.path.dirname(os.path.realpath(__file__))

dataDIR= curDir + '/data/colour/currentData/data.nc'             # specify path to input data
output_path = curDir +'/../presentation/swing/' # specify folder for output data

DS=Dataset(dataDIR)

# load values from the ns dataset and print them
chl_data = DS.variables["CHL"][0]
lats = DS.variables["lat"][:]
lons = DS.variables["lon"][:]
print(chl_data)

# plot dataset in a plot and save it under 'results.png'
ax = mpl.pyplot.axes(projection=ccrs.PlateCarree())
mpl.pyplot.contourf(lons, lats, np.log(chl_data), 60, transform=ccrs.PlateCarree())
ax.coastlines()
scale_bar(ax,1000)
mpl.pyplot.savefig(output_path+"current_CHL_concentrations.png")
