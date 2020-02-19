import pandas
import os
import platform
import subprocess
import datetime
from datetime import timedelta

cmd = ''

todayDT = datetime.datetime.now()

previousDT = (todayDT-(datetime.timedelta(days=4))).strftime("%Y-%m-%d 00:00:00")
posteriorDT=(todayDT-(datetime.timedelta(days=3))).strftime("%Y-%m-%d 00:00:00")

import os
curDir = os.path.dirname(os.path.realpath(__file__))

dataDIR= curDir + '/data/colour/currentData'             # specify path to input data

print("data output dir: " + dataDIR)

print(subprocess.call("python -m motuclient --motu http://nrt.cmems-du.eu/motu-web/Motu  --service-id OCEANCOLOUR_ATL_CHL_L4_NRT_OBSERVATIONS_009_037-TDS --product-id dataset-oc-atl-chl-multi-l4-oi_1km_daily-rt-v02 --longitude-min -45.99479293823242 --longitude-max 12.994793891906738 --latitude-min 20.005207061767578 --latitude-max 65.99478912353516 --date-min "+str(previousDT)+" --date-max "+str(posteriorDT)+" --variable CHL --variable CHL_error -o . --out-dir "+ dataDIR +" --user=IDura --pwd=e301Isaac* --verbose", shell=True))
