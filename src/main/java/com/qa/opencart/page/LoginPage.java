package com.qa.opencart.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constant.AppConstant;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;

public class LoginPage {

	WebDriver driver;
	ElementUtil eleUtil;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	// By locator

	private By userName = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgetPwdLink = By.linkText("Forgotten Password");
	private By logo = By.cssSelector("img[title='naveenopencart']");
	private By registerLink = By.linkText("Register");

	// Page action method
	@Step("Getting login page title ")
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstant.Login_Page_Title, AppConstant.SHORT_DEFAUTT_WAIT);
		// String title =driver.getTitle();
		System.out.println("login page title : " + title);
		return title;
	}

	@Severity(SeverityLevel.MINOR)
	@Step("Getting login page url")
	public String getLoginPageURL() {
		String url = eleUtil.waitForURLContains(AppConstant.Login_Page_URL_Fraction, AppConstant.SHORT_DEFAUTT_WAIT);
		System.out.println("login page URL : " + url);
		return url;
	}

	@Severity(SeverityLevel.CRITICAL)
	@Step("Check the forget password link is exist")
	public boolean isForgetPasswordLinkExist() {
		return eleUtil.waitForVisibilityOfElement(forgetPwdLink, AppConstant.SHORT_DEFAUTT_WAIT).isDisplayed();
	}

	@Step("Verify the logo")
	public boolean isLogoExist() {
		return eleUtil.waitForVisibilityOfElement(logo, AppConstant.SHORT_DEFAUTT_WAIT).isDisplayed();
	}

	@Severity(SeverityLevel.BLOCKER)
	@Step("User will get login with username: {0} and password: {1}")
	public AccountPage doLogin(String username, String pwd) {
		System.out.println("user credential : " + username + " : " + pwd);
		eleUtil.waitForVisibilityOfElement(userName, AppConstant.SHORT_DEFAUTT_WAIT).sendKeys(username);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		System.out.println("user logged in");
		return new AccountPage(driver);
	}

	@Step("the method navigate back to register page")
	public RegisterPage navigateToRegisterPage() {
		eleUtil.waitForVisibilityOfElement(registerLink, AppConstant.MEDIUM_DEFAUTT_WAIT).click();
		return new RegisterPage(driver);
	}

}
