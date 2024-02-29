package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.exception.FrameworkException;

public class DriverFactory {

	protected WebDriver driver;
	protected Properties prop;
	OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public WebDriver initDriver(Properties prop) {

		String browserName = prop.getProperty("browser");
		// If you want pass the browser name via command promp then enable below code
		// String browserName =System.getProperty("browser");
		System.out.println("Browser Name : " + browserName);
		optionsManager = new OptionsManager(prop);
		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				initRemoteDriver(browserName);
			} else {
				// driver = new ChromeDriver(optionsManager.getChromeOptions());
				tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			}
			break;
		case "firefox":
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				initRemoteDriver(browserName);
			}
			else {
			// driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			}
			break;
		case "edge":
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				initRemoteDriver(browserName);
			}
			else {
			// driver = new EdgeDriver(optionsManager.getEdgeOptions());
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			}
			break;
		case "safari":
			// driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
			break;

		default:
			System.out.println("Please pass the right browser name : " + browserName);
			throw new FrameworkException("Browser not found");
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}
	
	/**
	 * Run test on grid
	 * @param browserName
	 */

	private void initRemoteDriver(String browserName) {
		System.out.println("Running test on grid with browser: " + browserName);
		try {
		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getChromeOptions()));
			break;
		case "firefox":
			tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getFirefoxOptions()));
			break;
		case "edge":
			tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getEdgeOptions()));
			break;

		default:
			System.out.println("wrong browser info.... can not run on grid remote machine....");
			break;
		}
		}
		catch(MalformedURLException e) {
			
		}
	}

	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	public Properties initProp() {

		FileInputStream ip = null;
		prop = new Properties();
		String envName = System.getProperty("env");
		System.out.println("envName name is : " + envName);

		try {
			if (envName == null) {
				ip = new FileInputStream("./src/test/resources/config/config.qa.properties");
			} else {
				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/config.qa.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/config.dev.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/config.stage.properties");
					break;
				case "prod":
					ip = new FileInputStream("./src/test/resources/config/config.prod.properties");
					break;

				default:
					System.out.println("Please paas the right enviournment name : " + envName);
					throw new FrameworkException("Wrong Enviournment Name");
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	/**
	 * take screenshot
	 */
	public static String getScreenshot(String methodName) {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);

		String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis()
				+ ".png";

		File destination = new File(path);

		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}
}
