package driverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;

public class DriverFactory {
	private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

	public WebDriver initializeBrowser(String browserName) {
		if (browserName == null || browserName.isEmpty()) {
			throw new IllegalArgumentException("Browser name must not be null or empty.");
		}

		try {
			// Selenium Grid URL
			URL gridUrl = URI.create("http://localhost:4444/wd/hub").toURL();

			switch (browserName.toLowerCase()) {
			case "chrome":
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.setCapability("browserName", "chrome");
				tlDriver.set(new RemoteWebDriver(gridUrl, chromeOptions));
				break;

			case "firefox":
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				firefoxOptions.setCapability("browserName", "firefox");
				tlDriver.set(new RemoteWebDriver(gridUrl, firefoxOptions));
				break;

			case "edge":
				EdgeOptions edgeOptions = new EdgeOptions();
				edgeOptions.setCapability("browserName", "MicrosoftEdge"); // Set correct browser name for Edge
				tlDriver.set(new RemoteWebDriver(gridUrl, edgeOptions));
				break;

			default:
				throw new IllegalArgumentException("Unsupported browser: " + browserName);
			}

		} catch (MalformedURLException e) {
			throw new RuntimeException("Invalid Selenium Grid URL: " + e.getMessage());
		}

		// Set Browser Configurations
		WebDriver driver = getDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}

	public static synchronized WebDriver getDriver() {
		WebDriver driver = tlDriver.get();
		if (driver == null) {
			throw new IllegalStateException("WebDriver is not initialized. Did you call initializeBrowser?");
		}
		return driver;
	}

	public static void removeDriver() {
		WebDriver driver = tlDriver.get();
		if (driver != null) {
			driver.quit();
			tlDriver.remove();
		}
	}
}

//package driverFactory;
//
//import java.time.Duration;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//
//public class DriverFactory {
//
//	private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
//
//	public WebDriver initializeBrowser(String browser) {
//		if (browser == null || browser.isEmpty()) {
//			throw new IllegalArgumentException("Browser name must not be null or empty.");
//		}
//
//		try {
//			switch (browser.toLowerCase()) {
//			case "chrome":
//				// ChromeOptions chromeOptions = new ChromeOptions();
//				// chromeOptions.addArguments("headless");
//				tlDriver.set(new ChromeDriver());
//				break;
//			case "firefox":
//				// FirefoxOptions firefoxOptions = new FirefoxOptions();
//				// firefoxOptions.addArguments("headless");
//				tlDriver.set(new FirefoxDriver());
//				break;
//			case "edge":
//				// EdgeOptions edgeOptions = new EdgeOptions();
//				// edgeOptions.addArguments("headless");
//				tlDriver.set(new EdgeDriver());
//				break;
//			default:
//				throw new IllegalArgumentException("Unsupported browser: " + browser);
//			}
//		} catch (Exception e) {
//			System.err.println("Error initializing browser: " + e.getMessage());
//			throw e;
//		}
//
//		WebDriver driver = getDriver();
//		driver.manage().deleteAllCookies();
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//
//		return driver;
//	}
//
//	public static WebDriver getDriver() {
//		WebDriver driver = tlDriver.get();
//		if (driver == null) {
//			throw new IllegalStateException("WebDriver is not initialized. Did you call initializeBrowser?");
//		}
//		return driver;
//	}
//
//	public static void removeDriver() {
//		WebDriver driver = tlDriver.get();
//		if (driver != null) {
//			driver.quit();
//			tlDriver.remove();
//		}
//	}
//}
