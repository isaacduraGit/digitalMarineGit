import cartopy.crs as ccrs
from IPython import get_ipython
import matplotlib.pyplot as plt
import xarray as xr
import numpy as np
from netCDF4 import Dataset

dataDIR='data.nc'

# load values from the ns dataset and print them
colour_input_data = Dataset(dataDIR)
chl_data = colour_input_data.variables["CHL"][0]
lats = colour_input_data.variables['lat'][:]
lons = colour_input_data.variables['lon'][:]
print(chl_data)

# plot dataset in a plot and save it under 'results.png'
ipy = get_ipython()

if ipy is not None:
    ipy.run_line_magic('matplotlib', 'inline')
    
# plot data
ax = plt.axes(projection=ccrs.PlateCarree())
plt.contourf(lons, lats, np.log(chl_data), 60, transform=ccrs.PlateCarree())
ax.coastlines()
scale_bar(ax,1000)
plt.savefig("results.png")