package workingwithseleniumandconcepts.basetestclass;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

	int count = 0;
	int maxTry = 1;                          //This variable says how many times we want to rerun the test when it fails flakily
	//If the below method returns true , then the test will rerun
	@Override
	public boolean retry(ITestResult result) {
		/*if the test fails,the control will come to this block
		 *for first time, count=0 which is less than maxTry(=1), so this method will return true and the test method wil be rerun.
		 *for the second time, count=1 which is not less than maxTry(=1) , so this method will return false, and hence the test will not rerun.Now the status of the reran test method will be failed.
		 *So if the test fails and reruns and fails again, then the number of tests run will be two, first test's status will be skipped and the second test's status will be fail
		 * */
		
		if(count<maxTry) {
			count++;
			return true;				
		}
		return false;
	}

}
