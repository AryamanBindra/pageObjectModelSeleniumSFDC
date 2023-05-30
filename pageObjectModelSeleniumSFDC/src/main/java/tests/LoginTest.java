package tests;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import pages.LoginPage;
import testdata.LoginData;
import utils.Utilities;


public class LoginTest extends TestBase{
	
	
		@Test(dataProvider = "LoginData", dataProviderClass = LoginData.class)
		public void TC01(String username, String pass) throws IOException {
			WebDriver driver = TestBase.threadLocalDriver.get();
			LoginPage lp = new LoginPage(driver);
			Assert.assertTrue(lp.launchApp(driver, "https://login.salesforce.com/"), "Wrong URL");
			test = extent.createTest("TC01");
			
			test.log(Status.INFO, "App Launched");
			System.out.println("Test case 1");
			// Username Input
			Assert.assertTrue(lp.enterUsername(driver, username),"Username not Entered");
			test.log(Status.INFO, "Username Entered");
			// Clear Password
			lp.password.clear();
			test.info("Password Cleared");
			// Click Login
			lp.clickLoginButton(driver);
			test.info("Login Pressed");
			// check error message displayed
			Assert.assertTrue(Utilities.assertEquals(lp.enterPassErrorMsg.getText(),
					"Please enter your password."), "Error Message not displayed");
			test.info("Error Message Checked");
			test.pass("TC01 PASSED");
		}
		
		@Test(dataProvider = "LoginData", dataProviderClass = LoginData.class)
		public void TC02(String username, String pass) throws IOException {
			WebDriver driver = TestBase.threadLocalDriver.get();
			LoginPage lp = new LoginPage(driver);
			Assert.assertTrue(lp.launchApp(driver, "https://login.salesforce.com/"), "Wrong URL");
			test = extent.createTest("TC02");
			test.log(Status.INFO, "App Launched");
			System.out.println("Test case 2");
			Assert.assertTrue(lp.enterUsername(driver, username), "Username not Entered");
			test.info("Username Entered");
			Assert.assertTrue(lp.enterPassword(driver, pass), "Password Not Entered");
			test.info("Password Entered");
			Assert.assertTrue(Utilities.click(driver, lp.loginButton), "Login button is not clicked");
			test.info("Pressed LogIn");
			test.info("Logged in");
			test.error("Test case fails at Step 2, since the 'Welcome to your free trial' message doesn't exist.");
//			test.addScreenCaptureFromPath(Utilities.captureScreenshot(driver));
			test.fail("TC02 FAILED");
		}
		
		@Test(dataProvider = "LoginData", dataProviderClass = LoginData.class)
		public void TC03(String username, String pass) throws IOException {
			WebDriver driver = TestBase.threadLocalDriver.get();
			LoginPage lp = new LoginPage(driver);
			Assert.assertTrue(lp.launchApp(driver, "https://login.salesforce.com/"), "Wrong URL");
			
			test = extent.createTest("TC03");
			test.log(Status.INFO, "App Launched");
			System.out.println("Test case 3");
			Assert.assertTrue(lp.enterUsername(driver, username), "Username not Entered");
			test.info("Username Entered");
			Assert.assertTrue(lp.enterPassword(driver, pass), "Password Not Entered");
			test.info("Password Entered");
			Assert.assertTrue(Utilities.click(driver,lp.rememberMe), "Button not clicked");
			test.info("Remember Me pressed");
			Assert.assertTrue(Utilities.click(driver, lp.loginButton), "Login button is not clicked");
			test.info("Logged In");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30).toSeconds());
			wait.until(ExpectedConditions.visibilityOf(lp.userNavButton));
			// Click Username, then logout
			Assert.assertTrue(Utilities.click(driver, lp.userNavButton), "Button not clicked");
			test.info("Username clicked");
			
			Assert.assertTrue(Utilities.click(driver, lp.Logout), "Logout button is not clicked");
			test.info("Logged Out");
			Assert.assertTrue(Utilities.assertEquals(Utilities.getText(driver, lp.savedUsername),
					"jul22.aryaman@ta.com"), "Username not Saved");
			test.info("Username in saved");
			test.pass("TC03 PASSED");
		}
		
		@Test(enabled = false, dataProvider = "LoginData", dataProviderClass = LoginData.class)
		// Test Case complete - sends reset email each run
		public void TC04A(String username, String pass) throws IOException {
			WebDriver driver = TestBase.threadLocalDriver.get();
			LoginPage lp = new LoginPage(driver);
			Assert.assertTrue(lp.launchApp(driver, "https://login.salesforce.com/"), "Wrong URL");
			
			test = extent.createTest("TC04A");
			test.log(Status.INFO, "App Launched");
			Assert.assertTrue(Utilities.click(driver, lp.forgotPassword), "Button not clicked");
			test.info("Forgot Password Clicked");
			Assert.assertTrue(lp.enterUsername(driver, username), "Username not Entered");
			test.info("Username Entered");
			Assert.assertTrue(Utilities.click(driver, lp.forgotPassContinue), "Button not clicked");
			test.info("Continue Pressed");
			// Check if Forgot Pass page loads
			Assert.assertTrue(Utilities.isDisplayed(driver, lp.forgotPassForm), "Page didn't load");
			test.info("Forgot Password Page Loaded");
			test.pass("TC04A PASSED");
		}

		@Test(dataProvider = "LoginData", dataProviderClass = LoginData.class)
		public void TC04B(String username, String pass) throws IOException {
			WebDriver driver = TestBase.threadLocalDriver.get();
			LoginPage lp = new LoginPage(driver);
			Assert.assertTrue(lp.launchApp(driver, "https://login.salesforce.com/"), "Wrong URL");
			
			test = extent.createTest("TC04B");
			test.log(Status.INFO, "App Launched");
			Assert.assertTrue(lp.enterUsername(driver, username), "Username not Entered");
			test.info("Username Entered");
			Assert.assertTrue(Utilities.enterText(driver, lp.password, "wrongpass"), "Password Not Entered");
			test.info("Password Entered");
			Assert.assertTrue(Utilities.click(driver, lp.loginButton), "Login button is not clicked");
			test.info("Logged In");
			// Check For error message
			Assert.assertTrue(Utilities.assertEquals(Utilities.getText(driver, lp.wrongCredErrorMsg),
					"Please check your username and password. If you still can't log in,"
							+ " contact your Salesforce administrator."),
					"Error Message not displayed");
			test.info("Error Message Displayed");
			test.pass("TC04B PASSED");
		}
	

}
