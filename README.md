# AIESEC Opportunity Viewer (AOV)

## Prequisition

Ensure java runtime is installed in host machine.

## Usage

1. Search for GEP

    `./aov.bat -gep`

1. Search for US Opportunity

    `./aov.bat -country us`

1. Search for Canada Opportunity

    `./aov.bat -country ca`

1. Search for keyword Developer

    `./aov.bat -keyword developer`

1. Show opportunity detail(e.g. opp id 817356)

    `./aov.bat -show 817356`


## Note

* AOV consumes AIESEC GIS API with root service of v2(production), but people who are interested in testing the feature of the API can alter it with v1(testing) and configure the api url according to the [GIS API Document](http://apidocs.aies.ec)
