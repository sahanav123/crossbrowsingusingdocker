package hooks;

import java.util.Properties;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import driverFactory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utilitities.ConfigReader;

public class Hooks {

	public WebDriver driver;
	private DriverFactory driverFactory;
	private static Properties prop;

	@Before
	public void beforeScenario() throws Throwable {
		prop = ConfigReader.initializeprop();
		String browser = ConfigReader.getBrowserType();
		driverFactory = new DriverFactory();
		driver = driverFactory.initializeBrowser(browser);
		driver.get(prop.getProperty("URL"));
	}


	@After(order = 0)
	public void quitBrowser() {
		if (DriverFactory.getDriver() != null) {
			DriverFactory.getDriver().quit();
			DriverFactory.removeDriver();
		}
	}

	@After(order = 1)
	public void tearDown(Scenario scenario) {
		if (scenario.isFailed()) {
			byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", scenario.getName());

		}
	}

}
