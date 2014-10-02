odl-mdsal-cup-example
=====================

This is a clone of the MD-SAL toaster example but with Tea and Cups instead.

Karaf integration
=================
1. Modify the controller/opendaylight/commons/opendaylight/pom.xml file and add the following line in the properties section:
// The 02-cup.xml is the configuration file located in the cup-config project.

<config.cup.configfile>02-cup.xml</config.cup.configfile>


2. Compile controller/opendaylight/commons/opendaylight/pom.xml with mvn clean install.

3. Modify the controller/features/mdsal/src/main/resources/features.xml file and add this section:
// This section is used to load the jars and the config file into the controller.

    <feature name='odl-cup' version='0.0.1-SNAPSHOT' description="OpenDaylight :: Cup">
        <feature version='${yangtools.version}'>odl-yangtools-common</feature>
        <feature version='${yangtools.version}'>odl-yangtools-binding</feature>
        <feature version='${project.version}'>odl-mdsal-broker</feature>
        <bundle>mvn:org.opendaylight.controller/cup/0.0.1-SNAPSHOT</bundle>
        <!--bundle>mvn:org.opendaylight.controller.samples/sample-toaster-consumer/${project.version}</bundle-->
        <bundle>mvn:org.opendaylight.controller/cup-provider/0.0.1-SNAPSHOT</bundle>
        <configfile finalname="${config.configfile.directory}/${config.cup.configfile}">mvn:org.opendaylight.controller/cup-config/0.0.1-SNAPSHOT/xml/config</configfile>
    </feature>

4. Compile controller/features/mdsal/pom.xml with mvn clean install

5. Load the karaf distro and input the command "feature:install odl-cup"