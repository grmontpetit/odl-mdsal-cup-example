odl-mdsal-cup-example
=====================

## About
This is a clone of the MD-SAL toaster example but with Tea and Cups instead.
The project has its own distribution of karaf included and the features are loaded automatically on startup.
The project has been updated to be closer to Berylium than to Lithium.

## Improvements
The current version is its own standalone distribution and the cup project is loaded on startup.
The next iteration will be completely standalone, using artefacts from odlparent, yangtools and controller. This means there will not be a need to download and compile those 3 projects before beign able to use the cup project.

## Pre-requisite
- Java JDK 1.7
- Maven 3.2.5+
- odlparent ([https://git.opendaylight.org/gerrit/#/admin/projects/odlparent](cloned) and compiled)
- yangtools ([https://git.opendaylight.org/gerrit/#/admin/projects/yangtools](cloned) and compiled)
- controller ([https://git.opendaylight.org/gerrit/#/admin/projects/controller](cloned) and compiled)

## Using the project
1. Compile the whole project
2. Once the compilation is finished, cd into the cup-karaf/target folder
3. Depending on your operating system, extract the cup-karaf-0.1.0-SNAPSHOT.tar.gz
4. Run the the cup-karaf-0.1.0-SNAPSHOT/bin/karaf binary
5. The feature odl-cup should be loaded automatically, you can check with:

```

opendaylight-user@root>feature:list | grep cup
odl-cup                         | 0.1.0-SNAPSHOT   | x         | odl-cup-0.1.0-SNAPSHOT                | OpenDaylight :: Cup

```

## Aditionnal debugging tools
You can check the status of the bundles, logs and bundle diagnostic with these:
```
opendaylight-user@root>bundle:list | grep cup
198 | Active   |  80 | 0.1.0.SNAPSHOT                            | cup                                                                      
199 | Active   |  80 | 0.1.0.SNAPSHOT                            | cup-consumer                                                             
200 | Active   |  80 | 0.1.0.SNAPSHOT                            | cup-provider  

opendaylight-user@root>log:display

opendaylight-user@root>bundle:diag

```
## RESTConf Endpoints
The endpoints have been documented here:

[https://github.com/sniggel/odl-mdsal-cup-example/wiki/odl-mdsal-cup-example-documentation](Cup-Endpoints)

I recomment using a Rest client such as Postman or Advanced Rest Client (both for google chrome).

## Using JMX
To use JMX, run karaf with the jmx tag:
karaf -jmx
