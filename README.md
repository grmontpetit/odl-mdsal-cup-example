odl-mdsal-cup-example
=====================

## About
This is a clone of the MD-SAL toaster example but with Tea and Cups instead.
The project has its own distribution of karaf included and the features are loaded automatically on startup.

## Pre-requisite
- Java JDK 1.7
- Maven 3.2.5+

## Using the project
1. Compile the whole project
2. Once the compilation is finished, cd into the cup-karaf/target folder
3. Depending on your operating system, extract the cup-karaf-0.1.0-SNAPSHOT.tar.gz
4. Run the the cup-karaf-0.1.0-SNAPSHOT/bin/karaf binary
5. The feature odl-cup should be loaded automatically, you can check with:
```

feature:list | grep odl-cup

```
6. Additionally, you can check the status of the bundles, logs and bundle diagnostic with these:
```
bundle:list | grep cup
log:display
bundle:diag

```
7. Check https://github.com/sniggel/odl-mdsal-cup-example/wiki/odl-mdsal-cup-example-documentation for how to use the project.

## Using JMX
To use JMX, run karaf with the jmx tag:
karaf -jmx
