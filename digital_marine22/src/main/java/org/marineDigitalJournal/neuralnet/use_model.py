import keras
from keras.models import model_from_json
import numpy as np
from netCDF4 import Dataset
import matplotlib.pyplot as plt

## parameters
Colour_data_shape = (4416,5664) # shape of output data (needed for plotting)
current_path = 'currentData/' 	# path to current data file
model_path = ''					# path to trained model
insitu_input = 0.5 				# insitu data input (for now random)
max_level = 0.5 				# maximum CHL concentration which should trigger an email
#################################################

# load JSON file with model
json_file = open(model_path+'model.json', 'r')
loaded_model_json = json_file.read()
json_file.close()
loaded_model = model_from_json(loaded_model_json)

# load weights into new model
loaded_model.load_weights("model.h5")
print("Loaded model from disk")

# load CHL levels from colour data
colour_input_data = Dataset(colour_input)
CHL_levels = colour_input_data.variables["CHL"][0]

X = np.append(CHL_levels.flatten(), insitu_input)

# predict CHL concentrations for the next day
forecast = loaded_model.predict(np.expand_dims(X,axis=0))

# produce plot of predictions
plt.imshow(forecast.reshape((4416, 5664)))
plt.savefig("CHL_predictions_next_day")

# produce plot of high CHL concentration regions
forecast[forecast < max_level] = 0
forecast[forecast >= max_level] = 1
plt.imshow(forecast.reshape((4416, 5664)))
plt.savefig('predictions_high_CHL_locations.png')