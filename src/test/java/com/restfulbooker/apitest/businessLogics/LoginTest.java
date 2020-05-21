package com.restfulbooker.apitest.businessLogics;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.restfulbooker.apitest.actions.ValidatorOperation;
import com.restfulbooker.apitest.baseAPI.Auth;
import com.restfulbooker.apitest.listeners.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;
import java.lang.reflect.Method;



public class LoginTest {
	
  String response;
  
 
  
  // API Doc: https://restful-booker.herokuapp.com/apidoc/index.html
  @Test
  public void validLoginTest(Method method) {
	  
	  ExtentTestManager.startTest(method.getName(), "Description: Valid Login Scenario with username and password.");
	  Auth response = new Auth();
	  response.getLoginToken("admin", "password123");
	  
	  //ExtentTestManager.getTest().log(LogStatus.INFO, "URL is: " +response.url);
	  ExtentTestManager.getTest().log(LogStatus.INFO, "Asserting response code");
	  response.assertIt(200);
	  ExtentTestManager.getTest().log(LogStatus.INFO, "Asserting response value not empty case");
	  response.assertIt("token",null,ValidatorOperation.NOT_EMPTY);
	  ExtentTestManager.getTest().log(LogStatus.INFO, "Asserting response value not null case");
	  response.assertIt("token",null,ValidatorOperation.NOT_NULL);
	  
	 }
  
  @Test
  public void invalidLoginTest(Method method) {
	  
	  ExtentTestManager.startTest(method.getName(), "Description: InValid Login Scenario with username and password.");
	  Auth response = new Auth();
	  response.getLoginToken("dummy", "dummypassword123");
	  
	  //ExtentTestManager.getTest().log(LogStatus.INFO, "URL is: " +response.url);
	  ExtentTestManager.getTest().log(LogStatus.INFO, "Asserting response code");
	  response.assertIt(200);
	  ExtentTestManager.getTest().log(LogStatus.INFO, "Asserting response value == Bad credentials");
	  response.assertIt("reason","Bad credentials",ValidatorOperation.EQUALS);
	 
	  
	 }

}
