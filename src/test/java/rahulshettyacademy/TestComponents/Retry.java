package rahulshettyacademy.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
	// define this class on which tests you want to tetry on failure
	int count =  0;
	int maxTry = 1;

	@Override
	public boolean retry(ITestResult result) {
		// after the test fails from Listeners, it comes to this block and based on what we have coded
		//here in this block, it will attempt thta many number of reruns
		if (count<maxTry) {
			count ++;
			return true;
			
		}
		return false;
	}

}
