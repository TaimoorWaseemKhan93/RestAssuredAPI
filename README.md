# Insructions:

Packages

### Recources Folder
1. Update the **ApplicationConfig.properties** file for changing the project credentials
2. All Features files.

### Com.cucumber.tests Package
1.update tags in **RunCukesTest** for executing particular feature file.  
**@All** - This tag includes both frontend and backend cases  
**@backend** - This tag is for all backend cases  
**@frontend** - This tag is for all frontend cases  

### Execution Through CMD ###
Execute these commands according to the cases that you want to execute  
mvn test -Dcucumber.filter.tags="@All"  
mvn test -Dcucumber.filter.tags="@frontend"  
mvn test -Dcucumber.filter.tags="@backend"  

### General Package
1. General functions are defined in **ResusableMethods** class
2. WebDriver related functions defined in **WebDriverFactory** class

### stepdefs Package
1. defined step definition for cucumber steps in **stepdefs** Packages

# Setup Code

Pre-requisites:
- Java JDK 8+ should be installed
- JAVA_HOME environment variable should be set

Steps to setup:
- Clone repository
- Open project on IntelliJ through pom.xml file

Steps to execute script:
- Right click TestRunner file and Run it
- Wait for full execution


