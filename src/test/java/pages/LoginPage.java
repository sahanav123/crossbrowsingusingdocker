package pages;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilitities.ConfigReader;

public class LoginPage {

	WebDriver driver;
	Properties prop;
	WebDriverWait wait;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.prop = ConfigReader.initializeprop();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@title='My Account']")
	private WebElement myaccount;
	@FindBy(linkText = "Login")
	private WebElement loginstart;
	@FindBy(id = "input-email")
	private WebElement username;
	@FindBy(id="input-password")
	private WebElement password;
	@FindBy(xpath="//input[@value='Login']")
	private WebElement login;
	@FindBy(xpath="//h2[text()='My Account']")
	private WebElement myaccountdisplaytext;
	@FindBy(xpath="//div[contains(text(),'Warning: No match for E-Mail')]")
	private WebElement errormessage;

	public void clickMyAccount() {
		myaccount.click();
	}

	public void clickLoginStart() {
		loginstart.click();
	}

	public void enterValidUserName() {
		username.sendKeys(prop.getProperty("username"));
	}
	
	public void enterValidPassword() {
		password.sendKeys(prop.getProperty("password"));
	}
	public void clicklogin() {
		login.click();
	}
	public boolean isMyAccountDisplayed() {
		return myaccountdisplaytext.isDisplayed();
	}
	public void enterInvalidUserName() {
		username.sendKeys(prop.getProperty("invalidusername"));
	}
	public void enterInvalidPassword() {
		password.sendKeys(prop.getProperty("invalidpassword"));
	}
	public boolean isErrorMessageDisplayed() {
		wait.until(ExpectedConditions.visibilityOf(errormessage));
		return errormessage.isDisplayed();
	}
	

}
