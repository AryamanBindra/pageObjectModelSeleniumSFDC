package tests;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import pages.UserMenuPage;
import testdata.LoginData;
import utils.*;

public class UserMenuTest extends TestBase{

	@Test(dataProvider = "LoginData", dataProviderClass = LoginData.class)
	public void TC05(String username, String pass) throws IOException {
		WebDriver driver = TestBase.threadLocalDriver.get();
		UserMenuPage um = new UserMenuPage(driver);
		Assert.assertTrue(um.launchApp(driver, "https://login.salesforce.com/"), "Wrong URL");
		um.login(driver, username, pass);
		test = extent.createTest("TC05");
		test.log(Status.INFO, "App Launched");
		System.out.println("Test case 5");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30).toSeconds());
		wait.until(ExpectedConditions.visibilityOf(um.menuButton));
		Assert.assertTrue(Utilities.click(driver, um.menuButton), "Button not clicked");
		String[] expectedUserMenuValues = {"My Profile", "My Settings", "Developer Console","Switch to Lightning Experience", "Logout"};
		Assert.assertTrue(um.checkWebArrayXpath(expectedUserMenuValues, driver, "//div[@id='userNav-menuItems']/a"), "Incorrect values");
		test.pass("TC05 PASSED");
	}
	
	@Test(dataProvider = "LoginData", dataProviderClass = LoginData.class)
	public void TC06(String username, String pass) throws InterruptedException, IOException {
		WebDriver driver = TestBase.threadLocalDriver.get();
		UserMenuPage um = new UserMenuPage(driver);
		Assert.assertTrue(um.launchApp(driver, "https://login.salesforce.com/"), "Wrong URL");
		um.login(driver, username, pass);
		test = extent.createTest("TC06");
		test.log(Status.INFO, "App Launched");
		System.out.println("Test case 6");
		Assert.assertTrue(Utilities.click(driver,um.menuButton), "Button not clicked");
		String[] expectedUserMenuValues = {"My Profile", "My Settings", "Developer Console","Switch to Lightning Experience", "Logout"};
		Assert.assertTrue(um.checkWebArrayXpath(expectedUserMenuValues, driver, "//div[@id='userNav-menuItems']/a"), "Incorrect values");
		Assert.assertTrue(Utilities.click(driver, um.navFirstOption), "Button not clicked");
		Thread.sleep(2000);
		Assert.assertTrue(Utilities.click(driver,um.editPen), "Button not clicked");
		//Switch to iframe
		Assert.assertTrue(Utilities.switchToIframe(driver, "contactInfoContentId"), "Didn't switch to iframe");
		Assert.assertTrue(Utilities.click(driver, um.aboutTab), "Button not clicked");
		Assert.assertTrue(Utilities.clear(driver, um.lastName), "Not emptied");
		Assert.assertTrue(Utilities.enterText(driver, um.lastName, "bindra"), "Text Not Entered");
		Assert.assertTrue(Utilities.click(driver, um.saveAll), "Button not clicked");
		driver.switchTo().defaultContent();
		test.pass("TC06 PASSED");
		
	}
	@Test(enabled = false, dataProvider = "LoginData", dataProviderClass = LoginData.class)
	// Test Case complete - Downloads XML
	public void TC07(String username, String pass) throws IOException {
		WebDriver driver = TestBase.threadLocalDriver.get();
		UserMenuPage um = new UserMenuPage(driver);
		Assert.assertTrue(um.launchApp(driver, "https://login.salesforce.com/"), "Wrong URL");
		um.login(driver, username, pass);
		test = extent.createTest("TC07");
		test.log(Status.INFO, "App Launched");
		System.out.println("Test case 7");
		Assert.assertTrue(Utilities.click(driver, um.menuButton), "Button not clicked");
		String[] expectedUserMenuValues = {"My Profile", "My Settings", "Developer Console","Switch to Lightning Experience", "Logout"};
		Assert.assertTrue(um.checkWebArrayXpath(expectedUserMenuValues, driver, "//div[@id='userNav-menuItems']/a"), "Incorrect values");
		Assert.assertTrue(Utilities.click(driver, um.navSecondOption), "Button not clicked");
		Assert.assertTrue(Utilities.click(driver, um.personalInfo), "Button not clicked");
		Assert.assertTrue(Utilities.click(driver, um.loginHistory), "Button not clicked");
		Assert.assertTrue(Utilities.click(driver, um.showMore), "Button not clicked");
		Assert.assertTrue(Utilities.click(driver, um.displayLayout), "Button not clicked");
		Assert.assertTrue(Utilities.click(driver, um.customizeTabs), "Button not clicked");
		Assert.assertTrue(Utilities.click(driver, um.tabsDropSelect), "Button not clicked");
		Assert.assertTrue(Utilities.click(driver, um.tabsDropSelectOption9), "Button not clicked");
		Assert.assertTrue(Utilities.click(driver, um.report), "Button not clicked");
		Assert.assertTrue(Utilities.click(driver, um.add), "Button not clicked");
		Assert.assertTrue(Utilities.click(driver, um.save), "Button not clicked");
		test.pass("TC07 PASSED");
	}
	
	@Test(dataProvider = "LoginData", dataProviderClass = LoginData.class)
	public void TC08(String username, String pass) throws IOException {
		WebDriver driver = TestBase.threadLocalDriver.get();
		UserMenuPage um = new UserMenuPage(driver);
		Assert.assertTrue(um.launchApp(driver, "https://login.salesforce.com/"), "Wrong URL");
		um.login(driver, username, pass);
		test = extent.createTest("TC08");
		test.log(Status.INFO, "App Launched");
		System.out.println("Test case 8");
		Assert.assertTrue(Utilities.click(driver,um.menuButton), "Button not clicked");
		String[] expectedUserMenuValues = {"My Profile", "My Settings", "Developer Console","Switch to Lightning Experience", "Logout"};
		Assert.assertTrue(um.checkWebArrayXpath(expectedUserMenuValues, driver, "//div[@id='userNav-menuItems']/a"), "Incorrect values");
		Assert.assertTrue(Utilities.click(driver, um.navThirdOption), "Button not clicked");
		Assert.assertTrue(Utilities.isWindowDisplayed(driver, "Developer Console"),"Window not Displayed");
		test.pass("TC08 PASSED");
	}
	
	@Test(dataProvider = "LoginData", dataProviderClass = LoginData.class)
	public void TC09(String username, String pass) throws IOException {
		WebDriver driver = TestBase.threadLocalDriver.get();
		UserMenuPage um = new UserMenuPage(driver);
		Assert.assertTrue(um.launchApp(driver, "https://login.salesforce.com/"), "Wrong URL");
		um.login(driver, username, pass);
		test = extent.createTest("TC09");
		test.log(Status.INFO, "App Launched");
		System.out.println("Test case 9");
		Assert.assertTrue(Utilities.click(driver, um.menuButton), "Button not clicked");
		String[] expectedUserMenuValues = {"My Profile", "My Settings", "Developer Console","Switch to Lightning Experience", "Logout"};
		Assert.assertTrue(um.checkWebArrayXpath(expectedUserMenuValues, driver, "//div[@id='userNav-menuItems']/a"), "Incorrect values");
		Assert.assertTrue(Utilities.click(driver, um.navFifthOption), "Button not clicked");
		test.pass("TC09 PASSED");
	}
}
