package pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "username")
	public WebElement username;
	
	@FindBy(id = "password")
	public WebElement password;
	
	@FindBy(id = "Login")
	public WebElement loginButton;
	
	@FindBy(xpath = "//div[@id='error']")
	public WebElement enterPassErrorMsg;
	
	@FindBy(xpath = "//input[@id='rememberUn']")
	public WebElement rememberMe;
	
	@FindBy(xpath = "//span[@id='idcard-identity']")
	public WebElement savedUsername;
	
	@FindBy(xpath = "//a[@id='forgot_password_link']")
	public WebElement forgotPassword;
	
	@FindBy(xpath = "//input[@id='continue']")
	public WebElement forgotPassContinue;
	
	@FindBy(xpath = "//div[@id='forgotPassForm']")
	public WebElement forgotPassForm;
	
	@FindBy(css = "#error")
	public WebElement wrongCredErrorMsg;
	
	@FindBy(xpath = "//div[@id='userNavButton']")
	public WebElement userNavButton;
	
	@FindBy(xpath = "//a[@title='Logout']")
	public WebElement Logout;
	
	
	public boolean enterUsername(WebDriver driver, String userName) {

		if (username.isDisplayed()) {
			username.sendKeys(userName);
		} else {
			System.out.println("User name element is not visible");
		}
		
		if(username.getAttribute("value").equals(userName)) {
			System.out.println("Username correct");
			return true;
		}
		else
			System.out.println("username not correct");
			return false;
	}
	
	public String getUsername(WebDriver driver) {
		return username.getText();
	}

	public boolean enterPassword(WebDriver driver, String pass) {
		if (password.isDisplayed()) {
			password.sendKeys(pass);
		} else {
			System.out.println("password element is not visible");
		}
		if(password.getAttribute("value").equals(pass)) {
			return true;
		}
		else
			return false;
	}
	
	public String getSavedUserName(WebDriver driver) {
		return savedUsername.getText();
	}
	
	public void clickLoginButton(WebDriver driver) {
		if (loginButton.isDisplayed()) {
			loginButton.click();
		} else {
			System.out.println("login button is not visible");
		}
	}
	
	public boolean clickRememberMe() {
		boolean selected = false; 
		if(rememberMe.isSelected()) {
			selected = true;
		} else {
			rememberMe.click();
			selected = true;
		}
		return selected;
	}
	
	public boolean launchApp(WebDriver driver, String sURL) throws IOException {
		boolean isAppLaunched = false;
		driver.get(sURL);
		String actualURL = driver.getCurrentUrl();
		if(actualURL.equals(sURL)) {
			isAppLaunched = true;
		} else {
			System.out.println("App Launch Failiure");
		}
		return isAppLaunched;
	}
}
