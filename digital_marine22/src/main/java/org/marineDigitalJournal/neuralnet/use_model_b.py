#!/usr/bin/env python3
import keras
from keras.models import model_from_json
import numpy as np
from netCDF4 import Dataset
import matplotlib.pyplot as plt
import sys

#print(sys.argv[1]);
##parameters

Colour_data_shape = (4416,5664) # shape of output data (needed for plotting)
# path to the colour data
colour_input = '/application/pi/test-Eclipse/digital_marineBackUpWorking/data.nc'
insitu_input = 0.5 # insitu data input (for now random)
max_level = 0.5 # maximum CHL concentration which should trigger an email
#################################################


# load JSON file with model
print("entering load json")
json_file = open('/application/pi/eclipse-workspace/blueMaritimeDigitalJournal/src/main/java/org/marineDigitalJournal/neuralnet/model.json', 'r')
loaded_model_json = json_file.read()
print("fileread")
json_file.close()
loaded_model = model_from_json(loaded_model_json)

# load weights into new model
loaded_model.load_weights("/application/pi/eclipse-workspace/blueMaritimeDigitalJournal/src/main/java/org/marineDigitalJournal/neuralnet/model.h5")
print("Loaded model from disk")

# load CHL levels from colour data
colour_input_data = Dataset(colour_input)
CHL_levels = colour_input_data.variables["CHL"][0]

X = np.append(CHL_levels.flatten(), insitu_input)

# predict CHL concentrations for the next day
forecast = loaded_model.predict(np.expand_dims(X,axis=0))

# produce plot of predictions
plt.imshow(forecast.reshape((4416, 5664)))
#plt.savefig("CHL_predictions_next_day")
print("at save results")
save_results_to1 = '/application/pi/eclipse-workspace/blueMaritimeDigitalJournal/src/main/java/org/marineDigitalJournal/presentation/swing/'
plt.savefig(save_results_to1+ 'CHL_predictions_next_day.png', dpi = 300)


# produce plot of high CHL concentration regions

forecast[forecast < max_level] = 0
#if()
forecast[forecast >= max_level] = 1

#if forecast[forecast >= max_level].all(0)==True:
    
 #  CHL_Max_Limit=-1
    

plt.imshow(forecast.reshape((4416, 5664)))
#change by Isaac

#passToJava="forecastVAlue"+str(CHL_Max_Limit)

#print(passToJava)


save_results_to = '/application/pi/eclipse-workspace/blueMaritimeDigitalJournal/src/main/java/org/marineDigitalJournal/presentation/swing/'
plt.savefig(save_results_to + 'predictions_high_CHL_locations.png', dpi = 300)


