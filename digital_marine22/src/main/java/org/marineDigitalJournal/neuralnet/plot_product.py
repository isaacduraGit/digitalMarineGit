#!/usr/bin/env python3
import cartopy.crs as ccrs
from IPython import get_ipython
import matplotlib.pyplot as plt
import xarray as xr
import numpy as np
print('wefwefwewefwwwefwfwfewefwefwefw')
dataDIR='/application/pi/eclipse-workspace/blueMaritimeDigitalJournal/src/main/java/org/marineDigitalJournal/neuralnet/data/colour/currentData/data.nc'
DS=xr.open_dataset(dataDIR)

# load values from the ns dataset and print them
chl_data = DS.CHL.values[0]
print(chl_data)

# plot dataset in a plot and save it under 'results.png'

ipy = get_ipython()

if ipy is not None:
    ipy.run_line_magic('matplotlib', 'inline')
    
fig = plt.figure() 
plt.title(DS.parameter + ' (log scale)')
plt.imshow(np.log(chl_data))
plt.colorbar()
plt.savefig('results.png')