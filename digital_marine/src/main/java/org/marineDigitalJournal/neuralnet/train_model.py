from keras.models import Sequential
from keras.layers import Dense
import keras
from keras.models import model_from_json

import numpy as np
from netCDF4 import Dataset
import glob, sys
print("test")

import os
curDir = os.path.dirname(os.path.realpath(__file__))

###### parameters ##################################################
current_path = curDir + '/data/colour/currentData/' 	# specify path to current data here
historic_path = curDir + '/data/colour/historicData/'	# specity path to historic data here
model_path = curDir					# specify path to model here
console_path = curDir				# specify path for console output

epochs = 1						# specify the number of epochs you want to use for training
Colour_data_size = 4416*5664	# size of the colour data
Insitu_data_size = 1			# size of the insitu data
Input_data_size = Colour_data_size + Insitu_data_size
####################################################################

# write console output to txt file
sys.stdout = open(console_path+'console_output_train_one_day.txt', 'w')

## open existing model and load weights ############################
# load JSON file with model
json_file = open(model_path+'/model.json', 'r')
loaded_model_json = json_file.read()
json_file.close()
loaded_model = model_from_json(loaded_model_json)

# load weights into new model
loaded_model.load_weights(model_path+"/model.h5")
loaded_model.compile(loss='mse', optimizer='adam', metrics=['mae'])
print("Loaded model from disk")
####################################################################

# list all files in the data folder
historic_files = [f for f in glob.glob(historic_path + "*.nc")]
current_files = [f for f in glob.glob(current_path + "*.nc")]

# Train the model on the new data
input_data = Dataset(historic_files[-1])       # model input (most recent historic data)
output_data = Dataset(current_files[-1])	   # model output (current data file)
		    
# this is X (colour data)
CHL_levels = input_data.variables["CHL"][0].data.reshape(1,-1)
CHL_levels[CHL_levels==np.min(CHL_levels)] == 0
		    
# this should be replaced by the insitu data input
insitu_data = np.random.rand(Insitu_data_size)
		    
# concatenate colour data and insitu data to create one array of feature inputs
X = np.append(CHL_levels,insitu_data)
		    
# this is the desired output (colour data for the next day)
CHL_levels_Y = output_data.variables["CHL"][0].data.reshape(1,-1)
CHL_levels_Y[CHL_levels_Y==np.min(CHL_levels_Y)] == 0
		    
#Fit the model to the data
loaded_model.fit(np.expand_dims(X,axis=0), CHL_levels_Y, epochs=1, batch_size=1)

# serialize weights to HDF5
loaded_model.save_weights(model_path+"/model.h5")
print("Saved model to disk")    

# close filewriter
sys.stdout.close()
