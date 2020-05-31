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
