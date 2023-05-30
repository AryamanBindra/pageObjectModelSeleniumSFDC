package tests;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	public static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<WebDriver>();
	public static Logger logger = LogManager.getLogger("");
	protected static ExtentReports extent = new ExtentReports();
	protected static ExtentHtmlReporter report = null;
	
	public static ExtentTest test = null;
	
	@BeforeSuite
	public void initializeReport() {
		logger.debug("BaseTest: setup: configuration started ");
		String dateFormat = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String reportPath = System.getProperty("user.dir") + "\\src\\main\\resources\\reports\\" + dateFormat
				+ "_sfdc.html";
		extent = new ExtentReports();
		report = new ExtentHtmlReporter(reportPath);
		
		extent.attachReporter(report);
		logger.debug("BaseTest: setup: configuration complete");
	}
	
	@AfterSuite
	public void flushReport() {
		extent.flush();
		logger.debug("BaseTest: tearDown: completed ");
	}
	
	@BeforeMethod()
	public void driverSetup() {
		System.out.println("\n----Driver Setup----\n");
		ChromeOptions co = new ChromeOptions();
		co.addArguments("--headless", "--disable-gpu", "--window-size=1080,720", "--ignore-cerficate-errors");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(co);
		threadLocalDriver.set(driver);
		logger.debug("BaseTest: setDriver: driver saved on to a threadlocal object");
	}
	
	@AfterMethod
	public void removeDriver() {
		TestBase.threadLocalDriver.get().close();
		threadLocalDriver.remove();
		logger.debug("BaseTest: removeDriver: driver configuration removed from the thread");
	}
}
