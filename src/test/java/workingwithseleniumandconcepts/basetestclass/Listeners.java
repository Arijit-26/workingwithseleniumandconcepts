package workingwithseleniumandconcepts.basetestclass;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import workingwithseleniumandconcepts.globalproperty.ExtentReportConfiguration;

public class Listeners  extends BaseTest implements ITestListener{
	ExtentTest test;
	//How to call a static method of a class :- className.methodName().
	ExtentReports extent = ExtentReportConfiguration.configureEx();   //Calling the configureEx() method . This method returns an 'ExtentReports' object.
	
	//Before start of each test method , the control will come to this block and see that the code to create test is present in this block. So before executing any test method, a test will be created which will provide the reports for that particular test method, to the extent report and a test entry will be created for the test in the extent report.This prevents us from writing the test creation code in each and every test method
	@Override  
	public void onTestStart(ITestResult result) {                           
		
		test=extent.createTest(result.getMethod().getMethodName()); //'result' contains all the metadata of the test method. So we use 'result.getMethod().getMethodName()' to get the name of the tet method, which is then passed as the testname argument to the method 'createTest()'  
	}
	@Override  
	public void onTestSuccess(ITestResult result) {  
	// TODO Auto-generated method stub                                   //If a test method passes, then the control will come to this block.Here we do not have to explicitly tell the extent report that this test has passed.
																		//If we want we can provide the code test.log(status.PASS, "Test Passed"). This will tell the extent report that the test has passed and the message 'Test Passed' will be displayed for this test in the extent report
	}  
	  
	@Override  
	public void onTestFailure(ITestResult result)               
	{  
	// TODO Auto-generated method stub  
	//If a test method fails, then the control will come to this block.Here we have to explicitly tell the extent report that this test has failed.
	//We can use the following code 'test.log(status.FAIL, "Test Failed")'.This will explicitly tell the extent report that the test has failed and the message "Test Failed" will be displayed for the test in the extent report
	test.fail(result.getThrowable());           //'result' has all the metadata , including the reason why the test failed. 'result.getThrowable()' fetches this error message. 'test.fail(result.getThrowable())' displays the error message in the extent report
	String destinationPath = null; 
	try {
		driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());  //result has all information about the test that failed.So it can fetch the driver that is controlling the test method. This is the way to get the driver which is currently at the step where the test failed.
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
	try {
		 destinationPath = getScreenShot(String.valueOf(result.getParameters()),driver); //Taking the screenshot, storing it in a local directory and returning and storing the path to the screenshot in a string variable 'destinationPath'
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	test.addScreenCaptureFromPath(destinationPath,result.getMethod().getMethodName() ); //Method to take the screenshot using its path and attaching it to the extent report. The first argument is the path to the screenshot and the second argument is the name to be given to the screenshot while attaching it to the extent report
	}  
	  
	@Override  
	public void onTestSkipped(ITestResult result) {  
	// TODO Auto-generated method stub
	}  
	  
	@Override  
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {  
		
	}  
	  
	@Override  
	public void onStart(ITestContext context) {  
	// TODO Auto-generated method stub  
	}  
	  
	@Override  
	public void onFinish(ITestContext context) {  
	// TODO Auto-generated method stub  
		extent.flush();									//This step is necessary after the test execution is done, otherwise the report will be made but it will not be saved
	}  

}
