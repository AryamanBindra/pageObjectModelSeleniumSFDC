package listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import tests.TestBase;
import utils.Utilities;

public class SfdcListeners extends TestBase implements ITestListener{

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("onTestStart called");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		TestBase.test.pass(result.getName()+" passed");
		logger.debug("SfdcListeners: onTestSuccess: Test passed  "+result.getName());
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		 System.out.println("onTestFailure called");
		    try {
		    	String screenShotPath = Utilities.captureScreenshot(TestBase.threadLocalDriver.get());
			    logger.debug("SfdcListeners: onTestFailure: screen shot captured for test "+result.getName());
			    TestBase.test.addScreenCaptureFromPath(screenShotPath);
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
		  }
		
	

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("onTestSkipped called");
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("onTestFailedButWithinSuccessPercentage called");
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println("Test Suite started!");
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println(("Test Suite is ending!"));
	    extent.flush();
	}

}
