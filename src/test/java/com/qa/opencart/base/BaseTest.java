package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.page.AccountPage;
import com.qa.opencart.page.LoginPage;
import com.qa.opencart.page.ProductInfoPage;
import com.qa.opencart.page.RegisterPage;
import com.qa.opencart.page.SearchResultPage;
import com.qa.opencart.utils.ExcelUtil;

public class BaseTest {

	protected WebDriver driver;
	DriverFactory df;
	protected LoginPage loginPage;
	protected AccountPage accPage;
	protected Properties prop;
	protected SearchResultPage searchResultPage;
	protected ProductInfoPage productInfoPage;
	protected SoftAssert softAssert;
	protected RegisterPage registerPage;
	
	@Parameters({"browser","browserversion","testname"})
	@BeforeTest
	public void setup(String browserName, String browserVersion,String testName) {
		df = new DriverFactory();
		prop=df.initProp();
		if(browserName !=null) {
			prop.setProperty("browser", browserName);
			prop.setProperty("browserversion", browserVersion);
			prop.setProperty("testname", testName);
			}
		driver=df.initDriver(prop);
		loginPage = new LoginPage(driver);
		softAssert=new SoftAssert();
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
