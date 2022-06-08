# API Automation Suite - REST Assured

## How to run the test suite

### Prerequisites
- Configure Java and Maven in the environment that you are planning to run the tests
- Please note that Java 11 & Maven 3.6.3 were used for this project

### Configurations

There is no other special setup required to run the automation suite.
Just clone the project, build it and run the tests.

To setup some initial configurations please edit the environment.properties file located at src/main/resources. Refer the property file on how to config the below properties correctly.

- API_PROTOCOL = Set the protocol the script should run on
- API_HOST= Set the hostname of the System Under Test (SUT)
- API_VERSION=v1 = Set the API version of the API

### Tools and Frameworks used

- REST Assured
- TestNG
- Extent Reports

### Run all tests via command line

```sh
$ mvn clean install test
```

There is one test suite created(Testng file) for the Categories API tests (CategoryTestSuite.xml). If you prefer to run only this Categories related tests only ( if different endpoints are tested later ) you can do so by refering to the below command.

```sh
$ mvn test -Dsurefire.suiteXmlFiles=./src/test/java/com/automation/tests/CategoryTestSuite.xml
```


### Run with IDE
- Right click on any testng.xml file mentioned above and click Run


### Reports

A reports is generated with the Extent framework and can be located in below location.

- target/reports/api-Report.html

### Log File

A simple log file is currently generated but can be enhanced to caputre details in the framework for ease of troubleshooting and information. The current log file can be found at
- <PROJECT_HOME>/logs/automationScript.log

### Adding Tests
You can add new tests under the package com.automation.tests folder in the test package. Make sure to create a new branch from the main branch to obtain a stable version of the framework. 
