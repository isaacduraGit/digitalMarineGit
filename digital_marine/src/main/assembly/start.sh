#!/bin/sh

DIR=`dirname $0`
cd $DIR
java -jar ${project.artifactId}-${project.version}.jar
