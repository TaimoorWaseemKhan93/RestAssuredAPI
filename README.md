# Setup Code

#Pre-requisites:
- Java JDK 8+ should be installed
- JAVA_HOME environment variable should be set

#Steps:
- Clone repository
- Open project on IntelliJ through pom.xml file

#Steps to execute script:
- Right click TestRunner file and Run it
- Click on extend report folder and open the last created file to view the report in details

# Instructions:

- All Packages Details step wise: 

# Actions
- "CasesActions" file is used to defined all the actions of CaseSteps file.

# Data
- "CasesEndData" file is used to define the endpoints and env variables.

# General 
- "Assertion" file is used to define assertions.
- In "CommonFunctions" file we have defined the reusable methods.

# MainCall
- "ApplicationConfigReader" file is used to set and get the functions
- In "configProperties" file we have get the functions from applicationConfigReader file.
- "GlobalVariables" file is define the Rest assured variables.

# stepsdef
- "CasesStep" file is used call the cases actions .
- "Hooks" file is define the extends reports and its working.

# tests
- "TestRunner" file is used to run the feature file.
- 
# Utils
- "ExtendReport" file is to print and add steps of scenarios in extend file

# features
- "BackendCases" file is define the steps of cases.


# Others
ApplicationC config.properties file is used to define the baseURL and API KEY, etc
Execute these commands according to the cases that you want to execute
mvn test -Dcucumber.filter.tags="@frontend"  