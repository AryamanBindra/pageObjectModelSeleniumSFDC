package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
;

public class Utilities {

	public static boolean enterText(WebDriver driver, WebElement element, String inputText) {
		try {
			element.sendKeys(inputText);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		if (element.getAttribute("value").equals(inputText)) {
			return true;
		}
		return false;

	}

	public static boolean clear(WebDriver driver,  WebElement element) {
		try {
			element.clear();
			return true;
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public static boolean isDisplayed(WebDriver driver, WebElement element) {
		try {
			if (element.isDisplayed())
				return true;
			else
				return false;
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public static boolean assertEquals(String actual, String expected) {
		try {
			Assert.assertEquals(actual, expected);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public static boolean click(WebDriver driver, WebElement element) {
		try {
			element.click();
			return true;
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public String getAttributeValue(WebDriver driver, String path, String locaterType) {
		try {
			return getElement(driver, path, locaterType).getAttribute("value");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public static String getText(WebDriver driver, WebElement element) {
		try {
			return element.getText();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}
	
	
	
	public static boolean switchToIframe(WebDriver driver, String path) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30).toSeconds());
		try {
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(path));
			return true;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public static boolean isWindowDisplayed(WebDriver driver, String windowTitle) {
		try {
			String winParent = driver.getWindowHandle();
			Set<String> s = driver.getWindowHandles();
			for(String i:s) {
				String title = driver.switchTo().window(i).getTitle();
				if(title.equals("Developer Console")) {
					driver.close();
					driver.switchTo().window(winParent);
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public static boolean alertAccept(WebDriver driver) {
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public static WebElement getElement(WebDriver driver, String path, String locaterType) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30).toSeconds());
		try {
			switch (locaterType) {
			case "xpath":
				return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(path)));

			case "css":
				return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(path)));

			case "name":
				return wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(path)));

			case "id":
				return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(path)));

			case "linkText":
				return wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(path)));

			default:
				return null;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}
	
	
	public static String captureScreenshot(WebDriver driver) throws IOException{
		String dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH:mm:ss").format(new Date());
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		String destinationpath = System.getProperty("user.dir") + "\\src\\main\\resources\\screenshots\\" + dateFormat + "_sfdc.PNG";
		File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
		System.out.println(destinationpath);
		File destFile = new File(destinationpath);
		FileUtils.copyFile(sourceFile, destFile);
		System.out.println("Screenshot Captured");
		return destinationpath;
	}

}
