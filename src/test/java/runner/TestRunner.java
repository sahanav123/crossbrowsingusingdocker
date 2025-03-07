package runner;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import utilitities.ConfigReader;

@CucumberOptions(features = "src/test/resources/features", glue = { "stepDefinition", "hooks" }, plugin = { "pretty",
		"html:src/test/resources/cucumber-reports.html", "json:src/test/resources/cucumber-reports.json" },

		monochrome = true, tags = "@validlogin")

public class TestRunner extends AbstractTestNGCucumberTests {

	@BeforeTest
	@Parameters("browser")
	public void defineBrowser(@Optional("") String browser) {
		ConfigReader.setBrowserType(browser);
	}

	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}
}
