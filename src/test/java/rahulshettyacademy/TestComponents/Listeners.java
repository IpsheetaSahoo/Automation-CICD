package rahulshettyacademy.TestComponents;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahulshettyacademy.resources.ExtentReporterNG;

import org.openqa.selenium.WebDriver;

public class Listeners extends BaseTest implements ITestListener {
	
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ThreadLocal <ExtentTest> extentTest = new ThreadLocal();//thread safe
	
	@Override
    public void onTestStart(ITestResult result) {
		
		ExtentTest test;
    	test = extent.createTest(result.getMethod().getMethodName());
    	extentTest.set(test);//unique thread id to avoid concurrencies
    }

    @Override
    public void onTestSuccess(ITestResult result) {
      
		test.log(Status.PASS, "Test Passed");
        
    }

    @Override
    public void onTestFailure(ITestResult result) {
        
     extentTest.get().fail(result.getThrowable());
     
     try {
		driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
	} catch (IllegalArgumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (NoSuchFieldException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SecurityException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
        
        // take screenshot of failure and attach to report 
        
        String filePath = null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    	//
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("Test failed but within success percentage: " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test context started: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    } 

}
