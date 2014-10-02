odl-mdsal-cup-example
=====================

This is a clone of the MD-SAL toaster example but with Tea and Cups instead.

## Karaf integration

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
        <!--bundle>mvn:org.opendaylight.controller.samples/sample-toaster-consumer/${project.version}</bundle-->
        <bundle>mvn:org.opendaylight.controller/cup-provider/0.0.1-SNAPSHOT</bundle>
        <configfile finalname="${config.configfile.directory}/${config.cup.configfile}">mvn:org.opendaylight.controller/cup-config/0.0.1-SNAPSHOT/xml/config</configfile>
    </feature>
```
* Compile controller/features/mdsal/pom.xml with mvn clean install

* Run karaf

* Load the base features needed by the cup example: "feature:install odl-base-all"

* Load restconf: "feature:install odl-restconf"

* Load the cup example: "feature:install odl-cup"
