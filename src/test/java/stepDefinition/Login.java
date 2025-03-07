package stepDefinition;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import driverFactory.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;

public class Login {

	WebDriver driver = DriverFactory.getDriver(); // Fetch WebDriver instance
	LoginPage loginPage = new LoginPage(driver);

	@Given("User enters the valid username and valid password")
	public void User_enters_the_valid_username_and_valid_password() {
		loginPage.clickMyAccount();
		loginPage.clickLoginStart();
		loginPage.enterValidUserName();
		loginPage.enterValidPassword();
	}

	@When("User clicks on the Login button")
	public void User_clicks_on_the_Login_button() {
		loginPage.clicklogin();
	}

	@Then("User Should navigate myaccount page")
	public void User_Should_navigate_myaccount_page() {
		Assert.assertTrue(loginPage.isMyAccountDisplayed());
	}

	@Given("User enters the Invalid username and Invalid password")
	public void User_enters_the_Invalid_username_and_Invalid_password() {
		loginPage.clickMyAccount();
		loginPage.clickLoginStart();
		loginPage.enterInvalidUserName();
		loginPage.enterInvalidPassword();
	}

	@Then("User should see warning message")
	public void User_should_see_warning_message() {
		Assert.assertTrue(loginPage.isErrorMessageDisplayed());
	}

}
