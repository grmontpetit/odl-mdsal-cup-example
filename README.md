odl-mdsal-cup-example
=====================

This is a clone of the MD-SAL toaster example but with Tea and Cups instead.

## Pre-requisite
- Java JDK 1.7
- Maven 3.2.5+

## Using the project
There is a karaf distribution that has been included as well as a feature file to load the required features. To use the project, compile it then extract the tar.gz located in the /cup-karaf/target folder. To run karaf, use the bin/karaf of the extracted archive to lauch it. Once you have the karaf shell, input feature:install odl-cup to install the project and use it.

## Using JMX
To use JMX, run karaf with the jmx tag:
karaf -jmx

## Karaf integration (this section is deprecated)

* Modify the controller/opendaylight/commons/opendaylight/pom.xml file and add the following line in the properties section:

```xml
<config.cup.configfile>02-cup.xml</config.cup.configfile>
```

* Compile controller/opendaylight/commons/opendaylight/pom.xml with mvn clean install.

* Modify the controller/features/mdsal/src/main/resources/features.xml file and add this section:

```xml
    <feature name='odl-cup' version='0.0.1-SNAPSHOT' description="OpenDaylight :: Cup">
        <feature version='${yangtools.version}'>odl-yangtools-common</feature>
        <feature version='${yangtools.version}'>odl-yangtools-binding</feature>
        <feature version='${project.version}'>odl-mdsal-broker</feature>
        <bundle>mvn:org.opendaylight.controller/cup/0.0.1-SNAPSHOT</bundle>
        <bundle>mvn:org.opendaylight.controller/cup-consumer/0.0.1-SNAPSHOT</bundle>
        <bundle>mvn:org.opendaylight.controller/cup-provider/0.0.1-SNAPSHOT</bundle>
        <configfile finalname="${config.configfile.directory}/${config.cup.configfile}">mvn:org.opendaylight.controller/cup-config/0.0.1-SNAPSHOT/xml/config</configfile>
    </feature>
```
* Compile controller/features/mdsal/pom.xml with mvn clean install

* Run karaf -jmx (to access the MBeans through the jconsole)

* Load the base features needed by the cup example: "feature:install odl-base-all"

* Load restconf: "feature:install odl-restconf"

* Load the cup example: "feature:install odl-cup"
