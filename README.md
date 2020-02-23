## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)

## General info
Java 8 Swing application, machine learning predicting Python scripts and model to leverage the benefits of Copernicus satellite data. It supports the early identification of environmental hazards like the occurrence of harmful algal blooms in the North Atlantic ocean. App developed under the EOValue project of the Joint Research Center of the European Commission.

## Technologies

java version "1.8.0_121"
Java(TM) SE Runtime Environment (build 1.8.0_121-b13)
Java HotSpot(TM) 64-Bit Server VM (build 25.121-b13, mixed mode)
Python 3.6.9 :: Anaconda, Inc. (MotuClient 1.8.4)

## Setup

The database used is MySql. It does not need to be installed any longer because it is hosted
already in a remote AWS instance server, the local app connects automatically.

In order to run it, please extract the content of the .zip file;
digital_marine-1.0.0-application.zip
in a location of your choice.

Then, please follow the next steps;
### Option A (All Operating systems with Java installed)

1) Open the explorer navigating to the folder where the file;
digital_marine-1.0.0-application
is located.

2) Extract the content, navigate to the folder digital_marine-1.0.0-application
and click twice in the file digital_marine-1.0.0-application.jar”

### Option B (All Operating systems)

1) Open the terminal navigating to the folder where the file;
digital_marine-1.0.0-application
is located.

2) Extract the content, navigate to the folder digital_marine-1.0.0-application
and type the command “java -jar digital_marine-1.0.0-application.jar”

### Option C (Only for Linux or Mac)

1) Open the explorer navigating to the folder where the file;
digital_marine-1.0.0-application
is located.

2) Click twice in the file start.sh.

##### The application has been tested in the next environment;

MAcOs High Sierra version 10.13.6

##### Model explanation Jupyter.

https://nbviewer.jupyter.org/github/ipushin/CHL-level-in-the-ocean/blob/master/CHL%20concentrations_31.01.ipynb

## Generated using E.U. Copernicus Marine Service Information

