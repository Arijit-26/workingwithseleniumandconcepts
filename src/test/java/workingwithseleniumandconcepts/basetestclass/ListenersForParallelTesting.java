package workingwithseleniumandconcepts.basetestclass;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import workingwithseleniumandconcepts.globalproperty.ExtentReportConfiguration;

public class ListenersForParallelTesting extends BaseTest implements ITestListener {

	ExtentTest test;
	ExtentReports extent = ExtentReportConfiguration.configureEx();   
	ThreadLocal<ExtentTest> et = new ThreadLocal();	 //Using this ThreadLocal class, we can make the tests thread safe, i.e each test will be assigned its own unique thread
	@Override  
	public void onTestStart(ITestResult result) {                           
		
		test=extent.createTest(result.getMethod().getMethodName()); 
		et.set(test);	                           //For every test a thread will be created and an unique test id will be assigned to it. This is done by the set() method. The set method takes the 'test' object of the concerned test method.The test and its corrsponding thread id will be stored as a map in the ThreadLocal object(et)	
	}
	@Override  
	public void onTestSuccess(ITestResult result) {  
	// TODO Auto-generated method stub                                   
																		
	}  
	  
	@Override  
	public void onTestFailure(ITestResult result)               
	{  
	// TODO Auto-generated method stub  
	
	et.get().fail(result.getThrowable());              //When a test method fails or passes, the et.get() method gets the thread id and the associated test. And in the extent report the entry of this test method will have the correct pass/fail status. There will be no mixing up that happens without ThreadLocal class , for parallely run test methods
	String destinationPath = null; 
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
	try {
		 destinationPath = getScreenShot(result.getMethod().getMethodName(),driver); 
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	et.get().addScreenCaptureFromPath(destinationPath,result.getMethod().getMethodName() ); 
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
		extent.flush();									
	}  
}
