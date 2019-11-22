# run this on all historic data
from keras.models import Sequential
from keras.layers import Dense
import keras

import numpy as np
from netCDF4 import Dataset
import glob
import sys

###### parameters ##################################################
epochs = 1						# specify the number of epochs you want to use for training
historic_path = 'historicData/' # specify path to colour data here
console_path = ''				# specify path for console output

model_path = ''					# specify path to model here
Colour_data_size = 4416*5664	# size of the colour data
Insitu_data_size = 1			# size of the insitu data
Input_data_size = Colour_data_size + Insitu_data_size
####################################################################

# write console output to txt file
sys.stdout = open(console_path+'console_output_train_all.txt', 'w')

# list all files in the data folder
files = [f for f in glob.glob(historic_path + "*.nc", recursive=True)]

# Create the model.
# Right now, it is a very simple model with just one unit and one layer. Should be extended.
model=Sequential()
model.add(Dense(1, input_dim=Input_data_size, activation='relu'))
model.add(Dense(Colour_data_size, activation='sigmoid'))

# Compile the model
model.compile(loss='mse', optimizer='adam', metrics=['mae'])

# Train the model on all of the available data files
for e in range(epochs):
	for i in range(len(files)-1):
		try:
		    input_data = Dataset(files[i])         # model input (colour data at t=n)
		    output_data = Dataset(files[i+1])	   # model output (colour data at t=n+1)
		    
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
		    model.fit(np.expand_dims(X,axis=0), CHL_levels_Y, epochs=1, batch_size=1)
		except:
		    pass


# Save the model (by serializing it to JSON)
model_json = model.to_json()
with open(model_path+"model.json", "w") as json_file:
    json_file.write(model_json)
# serialize weights to HDF5
model.save_weights(model_path+"model.h5")
print("Saved model to disk")    

# close filewriter
sys.stdout.close()