package com.restfulbooker.apitest.listeners;

import com.relevantcodes.extentreports.LogStatus;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.restfulbooker.apitest.listeners.ExtentManager;
import com.restfulbooker.apitest.listeners.ExtentTestManager;
 
public class TestStatusListener implements ITestListener {
 
    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }
 
    
    public void onStart(ITestContext iTestContext) {
        System.out.println("Initializing TestClass - " + iTestContext.getName());
    }
 
    
    public void onFinish(ITestContext iTestContext) {
        System.out.println("Finishing TestClass - " + iTestContext.getName());
        //Do tier down operations for extentreports reporting!
        ExtentTestManager.endTest();
        ExtentManager.getReporter().flush();
    }
 
    
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("Initializing test method " + getTestMethodName(iTestResult) + " start");
    }
 
    
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("Test method " + getTestMethodName(iTestResult) + " succeed");
        //ExtentReports log operation for passed tests.
        ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed");
    }
 
    
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("Test method  " + getTestMethodName(iTestResult) + " failed");
 
        //Get driver from BaseTest and assign to local webDriver variable.
        Object testClass = iTestResult.getInstance();
        
        //ExtentReports log and screenshot operations for failed tests.
        ExtentTestManager.getTest().log(LogStatus.FAIL, "Test Failed");
    }
 
    
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("Test method " + getTestMethodName(iTestResult) + " skipped");
        //ExtentReports log operation for skipped tests.
        ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
    }
 
    
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }
}