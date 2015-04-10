odl-mdsal-cup-example
=====================

## The project is still having problems with the distribution but the rest should work fine.

This is a clone of the MD-SAL toaster example but with Tea and Cups instead.

## Pre-requisite
- Java JDK 1.7
- Maven 3.2.5+

## Using the project
There is a karaf distribution that has been included as well as a feature file to load the required features. To use the project, compile it then extract the tar.gz located in the /cup-karaf/target folder. To run karaf, use the bin/karaf of the extracted archive to lauch it. Once you have the karaf shell, input feature:install odl-cup to install the project and use it.

## Using JMX
To use JMX, run karaf with the jmx tag:
karaf -jmx
