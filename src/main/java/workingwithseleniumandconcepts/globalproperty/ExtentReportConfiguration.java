package workingwithseleniumandconcepts.globalproperty;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportConfiguration {

	//This method contains the code for configuratiion of the html file using ExtentSparkReporter and creating an object of ExtentReports. This html file will store the extent report for our tests.
	//This method is made static so that it can be called in the 'Listeners.java' class without creating an object of this class
	//This method returns the 'ExtentReports' object 'eReport'
	//This method will be called in the 'Listeners.java' class, so that these codes are executed before we use the 'ExtentReports' object to create test for each of our test methods
	public static ExtentReports configureEx() {
		String path = System.getProperty("User.dir") + "\\reports\\index.html";    
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);  
		reporter.config().setReportName("Automation result");          
		reporter.config().setDocumentTitle("Results@ExtentReport");	   
		ExtentReports eReport = new ExtentReports();								   
		eReport.attachReporter(reporter);							   
		eReport.setSystemInfo("Tester", "Arijit Choudhury");
		return eReport;
		
	}
}
