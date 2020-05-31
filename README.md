# RestAssured API Testing Automation Framework
This project has a RestAssured based API testing framework. Underlying, it uses RestAssured -API testing library, TestNG - Third-party free library for Running tests, and for Reporting, Extent Reports (library for interactive and detailed reports for tests). This framework can be used for any Restful application to create API tests.

# Contents
* [Framework Details](#FrameworkDetails)
	* [Base API](#api)
	* [Framework - What and Why?](#ww)
	* [Project structure](#structure)
	* [Properties](#properties)
* [Packages](#package)
	* [Main Package](#main)
	* [Test Package](#test)
	* [Reports](#reports)
* [Installation](#install)
	* [Steps to follow to setup API automation in local system](#steps)
* [Simple Example Test](#example)
	* [1) Create a base API](#1)
	* [2) Write an Actual Test](#2)
	* [3) Extent HTML report](#3)

# Framework Details<a name="FrameworkDetails"></a>
#### Base API<a name="api"></a>
> I have used [Restful-booker](https://restful-booker.herokuapp.com/apidoc/index.html ) API as a basis for the API test framework. We can use Restful-booker API to learn more about API Testing or try out API testing tools against. Restful-booker is a Create Read Update Delete Web API that comes with authentication features and loaded with a bunch of bugs for you to explore. The API comes pre-loaded with 10 records for you to work with and resets itself every 10 minutes back to that default state. Restful-booker also comes with detailed API documentation to help get you started with your API testing straight away.

#### Framework - What and Why?<a name="ww"></a>
> For any software requirement, certain common and basic tasks need to be performed. Such tasks would have been already solved and would be available as open-source/free projects. One can use that and build their code on top of it to solve the software requirements specific to them. Such base code is called a framework
      
#### Project structure<a name="structure"></a>
> This project uses a standard Maven Java project with standard java folder structure and POM.xml

#### Properties<a name="properties"></a>
> `src/main/resources/constants.properties` is a simple constants properties file to store various constants like application URL, DB & SSH details.

# Packages<a name="package"></a>
#### Main Package
> `src/main/java/` is the core package of Framework and it has various sub-packages dedicatedly for various API testing functionalities. All sub-package details are mentioned in the following section. Added necessary documentation to all classes. The Javadoc can be found in the paths `<working-dir>/doc/index.html`

#### Test Package<a name="test"></a>
> `src/test/java/` is the actual test package and the sub package `com.restfulbooker.apitest.businessLogics` holds all test classes (TestNG) related to restfulbooker application. The Javadoc can be found in the paths `<working-dir>/doc/com/restfulbooker/apitest/businessLogics/package-summary.html`

#### Reports<a name="reports"></a>
> With the Extent library, we can create interactive and detailed reports for our API test results. We can add events, tags, devices, authors or any other relevant information we decide is essential to create an informative and stunning report. Test Reports can be found in `workingDir/ExtentReports/ExtentReportResults.html`

## Main Package<a name="main"></a>
```
- com.restfulbooker.apitest.actions
	 - HttpOperation.java
	 - ValidatorOperation.java
- com.restfulbooker.apitest.interfaces
	- APIs.Java
- com.restfulbooker.apitest.listeners
	- AnnotationTransformer.java
	- ExtentBase.java
	- ExtentManager.java
	- ExtentTestManager.java
	- Retry.java
	- TestStatusListener.java
- com.restfulbooker.apitest.restassuredFunctions
	- API.java
- com.restfulbooker.apitest.utilities
	- DBConnection.Java
	- Helper.java
	- SshConnectionManager.java
- com.restfulbooker.apitest.baseAPI
	- Application API End points
```

### 1.com.restfulbooker.apitest.actions
#### HttpOperation.java
 > Its a Java Enum type, which is implemented to have a set of different HttpOperation constants. Which can be used to create, read, update, and delete (or CRUD) operations, respectively.

#### ValidatorOperation.java
> Its a Java Enum type, which is implemented to have a set of different ValidatorOperation constants. Which can be used in response json assertions.

### 2.com.restfulbooker.apitest.interfaces
#### IApi.java
> It contains all the methods needed to write API tests and provides basic assertion APIs. It is the blueprint of the `API.java` class.

### 3.com.restfulbooker.apitest.listeners

#### ExtentManager Class:
> In this class, we created an `ExtentReports` object and it can be reachable via `getReporter()` method. Also, we need to set ExtentReports report HTML file location.

#### ExtentTestManager Class:
> An extentTestMap map is created. It holds the information of thread ids and ExtentTest instances.
ExtentReports instance is created by calling getReporter() method from ExtentManager.
At `startTest()` method, an instance of ExtentTest created and put into extentTestMap with current thread id.
At `endTest()` method, test ends and ExtentTest instance got from extentTestMap via current thread id.
At `getTest()` method, return ExtentTest instance in extentTestMap by using current thread id.
#### AnnotationTransformer
```
`void transform(ITestAnnotation annotation,
               java.lang.Class testClass,
               java.lang.reflect.Constructor testConstructor,
               java.lang.reflect.Method testMethod)`
               
This method will be invoked by TestNG to give you a chance to modify a TestNG annotation read from your test classes. You can change the values you need by calling any of the setters on the ITest interface. Note that only one of the three parameters testClass, testConstructor and testMethod will be non-null.

`annotation` - The annotation that was read from your test class.
`testClass` - If the annotation was found on a class, this parameter represents this class (null otherwise).
`testConstructor` - If the annotation was found on a constructor, this parameter represents this constructor (null otherwise).
`testMethod` - If the annotation was found on a method, this parameter represents this method (null otherwise).
```
 
#### Retry
> Retry class implements `IRetryAnalyzer`. `IRetryAnalyzer` reruns the TestNG tests when they are failed. At the end of the execution, if we need to analyze failed test cases and try to figure out if there’s any false positive/flaky situation caused by network glitch, time-out or some other problem. In order to overcome this issue, we can use `IRetryAnalyzer` interface.

#### TestStatusListener
> In order to listen to test events such as passed, failed, skipped, etc. we have TestListener class which implements ITestListener.

#### ITestListener
```
By using ITestListener, we can change the default behaviour of your test by adding different events to the methods. It also defines a new way of logging or reporting.
The following are some methods provided by this interface:
**onStart:** This method is invoked before any test method gets executed. This can be used to get the directory from where the tests are running.

**onFinish:** This method is invoked after all tests methods gets executed. This can be used to store information of all the tests that were run.

**onTestStart:** This method is invoked before any test methods are invoked. This can be used to indicate that the particular test method has been started.

**onTestSkipped:** This method is invoked when each test method is skipped. This can be used to indicate that the particular test method has been skipped.

**onTestSuccess:** This method is invoked when any test method succeeds. This can be used to indicate that the particular test method has successfully finished its execution.

**onTestFailure:** This method is invoked when any test method fails. This can be used to indicate that the particular test method has failed. You can create an event for taking a screenshot which will show where the test has been failed.

**onTestFailedButWithinSuccessPercentage:** This method is invoked each time the test method fails but is within the success percentage mentioned. To implement this method, we use two attributes as a parameter of test annotation in TestNG, i.e. successPercentage and invocationCount. The successPercentage takes the value of percentage of successful tests and invocationCount denotes the number of times that a particular test method executes. For example:  @Test(successPercentage=60, invocationCount=5). In this annotation, the success percentage is 60% and the invocation count is 5, which means that out of 5 times, if the test method gets passed at least 3 times ((⅗)*100= 60), it will be considered as passed.
```

### 4.com.restfulbooker.apitest.restassuredFunctions
#### API.java
> The `API.java` class which implements the IApi interface defines complete functionality for the methods declared in the `IApi interface`.

### 5.com.restfulbooker.apitest.utilities
#### DBConnection.java
> A Singleton Java class for MySQL DB connection. It contains insert, update, truncate, and query methods, apart from that it has a singleton pattern to connect to MySQL database. All database constants(URL,dbname, username, etc) can be found in Constants.properties file.

#### Helper.java
> Its a Java class for SSH remote server connection. It contains methods to create/close new SSH sessions and methods to execute SSH commands.

#### SshConnectionManager.java
> Its a custom Java helper class, which contains some reusable functions for reading CSV / TSV files, common JSON path assertions, etc.

### 6.com.restfulbooker.apitest.baseAPI
`com.restfulbooker.apitest.baseAPI` represents application’s each API entities with web service’s request data properties like request headers & request body data. For each API endpoint, one java file should be there with different functions/methods for each HTTP action. (eg:- for /users API, one java file (users.java) would be created. If /users support GET, POST&PUT HTTP methods then 3 unique functions/methods should be there in users.java file to invoke different HTTP actions of /users API).  In order for the tests to work properly, names of the fields(request data(headers, body)) must match the application's API structure convention.

# Installation<a name="install"></a>
#### Steps to follow to setup API automation in local system:<a name="steps"></a>

**Install Java: [Skip this step if already installed]**
- Check if Java is installed.
  - In terminal enter java -version to check if java is installed in the system.
  - In terminal enter javac -version to check if java compiler is installed in the system.
  - Any version of java greater than 1.8 is supported.
  - Install java development kit if not available.

**Install Eclipse / Any other latest IDE [Skip this step if already installed]:** 
- Install eclipse photon if not available.
  - Download eclipse installer.
  - Run eclipse installer.
  - Select install eclipse for java developers.
  - Open workbench.

**Get Code base:**
  - Clone restassuredFramework
    - url : https://github.com/abu-sithik/restassuredFramework.git

**Setup project in Eclipse:** 
  - File -> Open Project from File System -> Browse the folder and open the cloned project.
  - Open -> Help -> Eclipse Marketplace -> Search testng -> Install Testng for eclipse plugin -> Restart eclipse.
  - Maven will be available by default, with eclipse. To check, right click on project -> should have an option called maven.
```
Possible issues:
In case of error in pom.xml file ->Cannot read lifecycle mapping metadata for artifact org.apache.maven.plugins:mav
  - In terminal open Users/<profile_name>/.m2
  - Run rm -r repository
  - Right click on project -> Update project
In case of error in all import statements
  - Click on src/main/java folder -> build path -> remove from build path
  - Click on src -> main -> java ->right click -> build path -> use as source folder
  - Refresh the project
````
**Set up verification**
  - In business logic package -> Right click on any java file -> run as testng test.



# Simple Example Test<a name="example"></a>
Now, let's get started with the simple example – a basic booking sites login page:

## API Details
> `Post` https://www.mwtestconsultancy.co.uk/auth

**Header**
| Field  | Type | Description | 
| --- | --- | --- |
| Content-Type | String  | Sets the format of payload you are sending Default value: `application/json` |

**Request body**
| Field  | Type | Description | 
| --- | --- | --- |
| username	| String | Username for authentication, Default value: `admin` |
| password	| String | Password for authentication, Default value: `password123` |
		
**Success 200**
| Field  | Type | Description |
| --- | --- | --- |
| token	| String | Token to use in future requests |

**Response** 
```
HTTP/1.1 200 OK

{
    "token": "abc123"
}		
```

## 1) Create a base API<a name="1"></a>
Let's now use RestAssured framework to automatically verify/test this login API.

> In `com.restfulbooker.apitest.baseAPI` package create a `Auth.java` file, which will contain all `auth` API's request parameters.
```
package com.restfulbooker.apitest.baseAPI;

import com.restfulbooker.apitest.actions.HttpOperation;
import com.restfulbooker.apitest.restassuredFuntions.API;

public class Auth extends API{
	
    public Auth(){}
      
    /**
     * Creates a new auth token to use for access to the PUT and DELETE /booking
     * 
     * */
    private void createToken(String userName, String passWord) {
		initBase("Host");
		init("/auth", HttpOperation.POST);
		setHeader("Content-Type","application/json");
		setBody("{ \"username\" : \""+userName+"\", \"password\" : \""+passWord+"\"}");
	}
	

	/**
	 * @param	userName (Username string value for the restful bokker application) 			
	 * 			passWord (password string value for the restful bokker application)
	 * 
	 * @return 	returns login token
	 * */
	public String getLoginToken(String userName, String passWord) {
		createToken(userName, passWord);
		String response = callIt();
		//System.out.println(response);
		return response;
	}	
	
}	
```
So, what we did here is – we created a java `private` method `createToken` which defines `auth` API's properties like `url,http method, request header & body`. Through `getLoginToken` method we can call & access the login request. 


> All application constants are configured in `src/main/resources/constants.properties` file.
```
Host=https://restful-booker.herokuapp.com
HostName=127.0.0.1
UserName=SSHUserName
PrivateKey=src/main/resources/test_data/ssh_id_rsa
DBUrl=jdbc:mysql://127.0.0.1:3306/
DBName=automation_db
DBUserName=dbUserName
DBPassWord=dbpassword
```

## 2) Write an Actual Test<a name="2"></a>
> Create a `LoginTest.java` file under `com.restfulbooker.apitest.businessLogics` package. (`src/test/java/` package holds all test classes (TestNG) related to application.)
```
package com.restfulbooker.apitest.businessLogics;

import org.testng.annotations.Test;
import com.restfulbooker.apitest.actions.ValidatorOperation;
import com.restfulbooker.apitest.baseAPI.Auth;
import com.restfulbooker.apitest.listeners.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;
import java.lang.reflect.Method;


public class LoginTest {
	
  String response;

  /**
   * reference API Doc: https://restful-booker.herokuapp.com/apidoc/index.html
   * 
   * */

  @Test
  public void validLoginTest(Method method) {
	  
	  ExtentTestManager.startTest(method.getName(), "Description: Valid Login Scenario with username and password.");
	  Auth response = new Auth();
	  response.getLoginToken("admin", "password123");
	  
	  try {
		  //ExtentTestManager.getTest().log(LogStatus.INFO, "URL is: " +response.url)
		  response.assertIt(200);
		  ExtentTestManager.getTest().log(LogStatus.INFO, "Asserting response code");
		    
		  response.assertIt("token",null,ValidatorOperation.NOT_EMPTY);
		  ExtentTestManager.getTest().log(LogStatus.INFO, "Asserting response value not empty case");
		  
		  response.assertIt("token",null,ValidatorOperation.NOT_NULL);
		  ExtentTestManager.getTest().log(LogStatus.INFO, "Asserting response value not null case");
	  }
	  catch(AssertionError e){
		  ExtentTestManager.getTest().log(LogStatus.FAIL,"Assertion Failure: " +e.getMessage());
	  }
	  
	  
	 }
  
  @Test
  public void invalidLoginTest(Method method) {
	  
	  ExtentTestManager.startTest(method.getName(), "Description: InValid Login Scenario with username and password.");
	  Auth response = new Auth();
	  response.getLoginToken("dummy", "dummypassword123");
	  
	  try {
		//ExtentTestManager.getTest().log(LogStatus.INFO, "URL is: " +response.url);		 
		  response.assertIt(200);
		  ExtentTestManager.getTest().log(LogStatus.INFO, "Asserting response code");
		  
		  response.assertIt("reason","Bad credentials",ValidatorOperation.EQUALS);
		  ExtentTestManager.getTest().log(LogStatus.INFO, "Asserting response value == Bad credentials");

	  }
	  catch(AssertionError e){
		  ExtentTestManager.getTest().log(LogStatus.FAIL,"Assertion Failure: " +e.getMessage());
	  }	 
	  
	 }

}
```
> In test method `validLoginTest`, what we did is – we are making call to the endpoint `/auth` with valid reqeust body data & application responds with a response data, that data stored in `response` String. In subsequent lines we are validating response code value `200` and response body values(here the condition is reponse token should return valid string, not a null/empty value).

#### Asserting Response code
```
ExtentTestManager.getTest().log(LogStatus.INFO, "Asserting response code");
response.assertIt(200);
```
#### Asserting Response body
```
ExtentTestManager.getTest().log(LogStatus.INFO, "Asserting response value not empty case");
response.assertIt("token",null,ValidatorOperation.NOT_EMPTY);
	  
ExtentTestManager.getTest().log(LogStatus.INFO, "Asserting response value not null case");
response.assertIt("token",null,ValidatorOperation.NOT_NULL);
```

> Similarly in `invalidLoginTest` method, we are asserting whether the response body data `reason` returns as `Bad credentials` or not.

> Note that, `assertIt` is a custom method I created for response assertion. For more implementation details refer `com.restfulbooker.apitest.restassuredFunctions.API`.

## 3) Extent HTML report<a name="3"></a>
> Test Reports can be found in `workingDir/ExtentReports/ExtentReportResults.html`
**Test Summary**
![alt text](https://github.com/sithik1994/rest_assured-automation-framework/blob/master/ExtentReports/reports%20sample%20img1.png?raw=true)

**Specific Test details**
![alt text](https://github.com/sithik1994/rest_assured-automation-framework/blob/master/ExtentReports/reports%20sample%20img2.png?raw=true)
![alt text](https://github.com/sithik1994/rest_assured-automation-framework/blob/master/ExtentReports/reports%20sample%20img3.png?raw=true)


> So far, we have explored the use case of this custom RestAssured framework and looked at its most important features which we can use to test our RESTful services and validate their responses.
If you want to make a change in this, start **forking**. If you find this project somehow useful to you, **star** it.
