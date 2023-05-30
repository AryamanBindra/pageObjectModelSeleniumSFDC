package sfdc.com.pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserMenuPage {
	public UserMenuPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "username")
	public WebElement username;
	
	@FindBy(id = "password")
	public WebElement password;
	
	@FindBy(id = "Login")
	public WebElement loginButton;
	
	@FindBy(xpath = "//span[@id='userNavLabel' and @class='menuButtonLabel']")
	public WebElement menuButton;
	
	@FindBy(xpath = "//div[@id='userNav-menuItems']/a[1]")
	public WebElement navFirstOption;
	
	@FindBy(xpath = "/div[@class='editPen']/a[@class='contactInfoLaunch editLink']/img")
	public WebElement editPen;
	
	@FindBy(xpath = "//li[@id='aboutTab']/a")
	public WebElement aboutTab;
	
	@FindBy(xpath = "//input[@id='lastName']")
	public WebElement lastName;
	
	@FindBy(xpath = "//input[@value='Save All']")
	public WebElement saveAll;
	
	@FindBy(xpath = "//div[@id='userNav-menuItems']/a[2]")
	public WebElement navSecondOption;
	
	@FindBy(css = "#PersonalInfo")
	public WebElement personalInfo;
	
	@FindBy(xpath = "//a[@id='LoginHistory_font']")
	public WebElement loginHistory;
	
	@FindBy(xpath = "//div[@class='pShowMore']/a")
	public WebElement showMore;
	
	@FindBy(css = "#DisplayAndLayout_font")
	public WebElement displayLayout;
	
	@FindBy(xpath = "//a[@id='CustomizeTabs_font']")
	public WebElement customizeTabs;
	
	@FindBy(xpath = "//select[@id='p4']")
	public WebElement tabsDropSelect;
	
	@FindBy(xpath = "(//select[@id='p4']/option)[9]")
	public WebElement tabsDropSelectOption9;
	
	@FindBy(xpath = "//option[@value='report']")
	public WebElement report;
	
	@FindBy(xpath = "//img[@alt='Add']//parent::a")
	public WebElement add;
	
	@FindBy(xpath = "//input[@value=' Save ']")
	public WebElement save;
	
	@FindBy(xpath = "//div[@id='userNav-menuItems']/a[3]")
	public WebElement navThirdOption;
	
	@FindBy(xpath = "//div[@id='userNav-menuItems']/a[5]")
	public WebElement navFifthOption;
	
	public void login(WebDriver driver, String uname, String pass) {
		username.sendKeys(uname);
		password.sendKeys(pass);
		loginButton.click();
	}
	
	public boolean checkWebArrayXpath(String[] expeactedValues, WebDriver driver, String xpath) {
		List<WebElement> listOfItms = driver.findElements(By.xpath(xpath));
		for(int i=0; i<expeactedValues.length; i++) {
			String actualValue = listOfItms.get(i).getText();
			if(actualValue.equals(expeactedValues[i])) {
				System.out.println(expeactedValues[i]+" is verified successfully!");
			}
			else return false;
		}
		
		return true;
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
