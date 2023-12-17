package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constant.AppConstant;
import com.qa.opencart.page.ProductInfoPage;
import com.qa.opencart.page.SearchResultPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

 
@Epic("Epic 100: Design opencard login page")
@Story("US 101: Login page feature")
public class LoginPageTest extends BaseTest {

	@Severity(SeverityLevel.NORMAL)
	@Description("Login page title test")
	@Test(priority=1)
	public void loginPageTitleTest() {
		String actTitle =loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle, AppConstant.Login_Page_Title);
	} 
	@Test(priority=2)
	public void loginPageURLTest() {
		String actURL =loginPage.getLoginPageURL();
		Assert.assertTrue(actURL.contains(AppConstant.Login_Page_URL_Fraction));
	}
	@Test(priority=3)
	public void ForgetPasswordLinkExistTest() {
		Assert.assertTrue(loginPage.isForgetPasswordLinkExist());
	}
	@Test(priority=4)
	public void appLogoExistTest() {
		Assert.assertTrue(loginPage.isLogoExist());
	}
	@Test(priority=5)
	public void loginTest() {
		accPage =loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accPage.getAccountPageTitle(),"My Account");
	}

	

}
