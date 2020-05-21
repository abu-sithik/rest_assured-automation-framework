# restassuredFramework
This project has a RestAssured based API testing framework. Underlying, it uses RestAssured -API testing library, TestNG - Third-party free library for Running tests, and for Reporting, Extent Reports (library for interactive and detailed reports for tests). This framework can be used for any Restful application to create API tests

**API**
> I have used [Restful-booker](https://restful-booker.herokuapp.com/apidoc/index.html ) API as a basis for the API test framework. We can use Restful-booker API to learn more about API Testing or try out API testing tools against. Restful-booker is a Create Read Update Delete Web API that comes with authentication features and loaded with a bunch of bugs for you to explore. The API comes pre-loaded with 10 records for you to work with and resets itself every 10 minutes back to that default state. Restful-booker also comes with detailed API documentation to help get you started with your API testing straight away.

**Framework - What and Why?**
> For any software requirement, certain common and basic tasks need to be performed. Such tasks would have been already solved and would be available as open-source/free projects. One can use that and build their code on top of it to solve the software requirements specific to them. Such base code is called a framework
      
**Project structure**
> This project uses a standard Maven Java project with standard java folder structure and POM.xml

**Properties**
> `src/main/resources/constants.properties` is a simple constants properties file to store various constants like application URL, DB & SSH details.

# Packages
**Main Package**
> `src/main/java/` is the core package of Framework and it has various sub-packages dedicatedly for various API testing functionalities. All sub-package details are mentioned in the following section.

**Test Package**
> `src/test/java/` is the actual test package and the sub package `com.restfulbooker.apitest.businessLogics` holds all test classes (TestNG) related to restfulbooker application.

**Reports**
> With the Extent library, we can create interactive and detailed reports for our API test results. We can add events, tags, devices, authors or any other relevant information we decide is essential to create an informative and stunning report. Test Reports can be found in `workingDir/ExtentReports/ExtentReportResults.html`

**Main Package**
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

# 1.com.restfulbooker.apitest.actions
**HttpOperation.java**
 > Its a Java Enum type, which is implemented to have a set of different HttpOperation constants. Which can be used to create, read, update, and delete (or CRUD) operations, respectively.

**ValidatorOperation.java**
> Its a Java Enum type, which is implemented to have a set of different ValidatorOperation constants. Which can be used in response json assertions.

# 2.com.restfulbooker.apitest.interfaces
**IApi.java**
> It contains all the methods needed to write API tests and provides basic assertion APIs. It is the blueprint of the `API.java` class.

`void init(String url, com.restfulbooker.apitest.actions.HttpOperation)`
| Name  | Input Parameters | Return Type | Output |  Description | 
| --- | --- | --- | --- | --- |
| init | String url, `com.restfulbooker.apitest.actions.HttpOperation` | void | NA | Used to specify the url of the API (excluding base url). HttpOperation is an enum with following values that specifies the HTTP method the API uses - GET, POST, PUT, PATCH.|

`void setHeader(String header, String value)`  
| Name  | Input Parameters | Return Type | Output |  Description | 
| --- | --- | --- | --- | --- |
| setHeader | String header, String value | void | NA | Used to set header for an API. Overloaded for (String[][]) - can be used to specify multiple headers as an array of  arrays |

`void setBody(String body)`  
| Name  | Input Parameters | Return Type | Output |  Description | 
| --- | --- | --- | --- | --- |
| setBody | String body | void | NA | Used to set the body data for an API. |

`String callIt()`  
| Name  | Input Parameters | Return Type | Output |  Description | 
| --- | --- | --- | --- | --- |
| callIt | NA | void | `String` | This method makes the API call and returns the response as String. |

`void setQueryParam(String key, String val)`  
| Name  | Input Parameters | Return Type | Output |  Description | 
| --- | --- | --- | --- | --- |
| setQueryParam | String key, String val | void | NA | Used to set query parameters for API call |

`void assertIt(int code)`  
| Name  | Input Parameters | Return Type | Output |  Description | 
| --- | --- | --- | --- | --- |
| assertIt | int code | void | NA | Used to assert the status code for the request |

`void assertIt(String key, Object value, ValidatorOperation operation)`  
| Name  | Input Parameters | Return Type | Output |  Description | 
| --- | --- | --- | --- | --- |
| assertIt | String key, Object value, ValidatorOperation operation | void | NA | Used to assert key and value based on the ValidatorOperation operation which is an enum with values like `KEY_PRESENTS, EQUALS, HAS_ALL` |

`void assertIt(List<List<Object>> data)`  
| Name  | Input Parameters | Return Type | Output |  Description | 
| --- | --- | --- | --- | --- |
| assertIt | List<List<Object>> data | void | NA | Used to assert multiple key and value pairs based on Validator Operation operation. |

`String getResponseString()`  
| Name  | Input Parameters | Return Type | Output |  Description | 
| --- | --- | --- | --- | --- |
| getResponseString | NA | String | ResponseString is returned as a String | Returns response String as String |

`void void printResp()`  
| Name  | Input Parameters | Return Type | Output |  Description | 
| --- | --- | --- | --- | --- |
| printResp | NA | void | NA | Prints the response obtained from the API. |

`Object extractIt(String path)`  
| Name  | Input Parameters | Return Type | Output |  Description | 
| --- | --- | --- | --- | --- |
| extractIt | String path | Object | Returns data to be extracted. | Extracts and returns the Object at the mentioned path from the response string. |

`String extractString(String path)`  
| Name  | Input Parameters | Return Type | Output |  Description | 
| --- | --- | --- | --- | --- |
| extractString | String path | String | Returns the data to be extracted as a String. | Extracts and returns the data at the mentioned path from the response string as a String. |

`int extractInt(String path)`  
| Name  | Input Parameters | Return Type | Output |  Description | 
| --- | --- | --- | --- | --- |
| extractInt | String path | int | Returns the data to be extracted as an int. | Extracts and returns the data at the mentioned path from the response string as an int |

`List<> extractString(String path)`  
| Name  | Input Parameters | Return Type | Output |  Description | 
| --- | --- | --- | --- | --- |
| extractList| String path | List<> | Returns the data to be extracted as a List<> | Extracts and returns the data at the mentioned path from the response string as a List<> |

`void failTest(String Expected, String Present)`  
| Name  | Input Parameters | Return Type | Output |  Description | 
| --- | --- | --- | --- | --- |
| failTest | String Expected, String Present | void | NA | Displays an Assertion Error message |

`fileUpload()`  
| Name  | Input Parameters | Return Type | Output |  Description | 
| --- | --- | --- | --- | --- |
| fileUpload() | NA | NA | NA | NA |

`String extractHeader(String header_name)`  
| Name  | Input Parameters | Return Type | Output |  Description | 
| --- | --- | --- | --- | --- |
| extractHeader | String header_name | String | Returns value of header as a String | Extracts value of the header present in header_name. |

# 3.com.restfulbooker.apitest.listeners

**ExtentManager Class:**
> In this class, we created an `ExtentReports` object and it can be reachable via `getReporter()` method. Also, we need to set ExtentReports report HTML file location.

**ExtentTestManager Class:**
> An extentTestMap map is created. It holds the information of thread ids and ExtentTest instances.
ExtentReports instance is created by calling getReporter() method from ExtentManager.
At `startTest()` method, an instance of ExtentTest created and put into extentTestMap with current thread id.
At `endTest()` method, test ends and ExtentTest instance got from extentTestMap via current thread id.
At `getTest()` method, return ExtentTest instance in extentTestMap by using current thread id.

**AnnotationTransformer**
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
 
**Retry**
> Retry class implements `IRetryAnalyzer`. `IRetryAnalyzer` reruns the TestNG tests when they are failed. At the end of the execution, if we need to analyze failed test cases and try to figure out if there’s any false positive/flaky situation caused by network glitch, time-out or some other problem. In order to overcome this issue, we can use `IRetryAnalyzer` interface.

**TestStatusListener**
> In order to listen to test events such as passed, failed, skipped, etc. we have TestListener class which implements ITestListener.

**ITestListener**
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

# 4.com.restfulbooker.apitest.restassuredFunctions
**API.java**
> The `API.java` class which implements the IApi interface defines complete functionality for the methods declared in the `IApi interface`.

# 5.com.restfulbooker.apitest.utilities
**DBConnection.java**
> A Singleton Java class for MySQL DB connection. It contains insert, update, truncate, and query methods, apart from that it has a singleton pattern to connect to MySQL database. All database constants(URL,dbname, username, etc) can be found in Constants.properties file.

**Helper.java**
> Its a Java class for SSH remote server connection. It contains methods to create/close new SSH sessions and methods to execute SSH commands.

**SshConnectionManager.java**
> Its a custom Java helper class, which contains some reusable functions for reading CSV / TSV files, common JSON path assertions, etc.

# 6.com.restfulbooker.apitest.baseAPI
`com.restfulbooker.apitest.baseAPI` represents application’s each API entities with web service’s request data properties like request headers & request body data. For each API endpoint, one java file should be there with different functions/methods for each HTTP action. (eg:- for /users API, one java file (users.java) would be created. If /users support GET, POST&PUT HTTP methods then 3 unique functions/methods should be there in users.java file to invoke different HTTP actions of /users API).  In order for the tests to work properly, names of the fields(request data(headers, body)) must match the application's API structure convention.
