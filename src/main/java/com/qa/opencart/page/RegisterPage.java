package com.qa.opencart.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constant.AppConstant;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {
WebDriver driver;
ElementUtil eleUtil;

private By firstname = By.name("firstname");
private By lastname = By.name("lastname");
private By email = By.name("email");
private By telephone = By.name("telephone");
private By password = By.name("password");
private By passwordConfirm = By.name("confirm");
private By subscribeYes = By.xpath("(//div[@class='form-group']/div//label)[1]/input");
private By subscribeNo = By.xpath("(//div[@class='form-group']/div//label)[2]/input");
private By conditionCheckbox = By.name("agree");
private By submitButton = By.cssSelector("input.btn.btn-primary");
private By successMessg = By.cssSelector("div#content h1");
private By logoutLink = By.linkText("Logout");
private By registerLink = By.linkText("Register");
	public RegisterPage(WebDriver driver) {
		this.driver=driver;
		eleUtil= new ElementUtil(driver);
	}

	public boolean registrationForm(String firstname,String lastname,String email,String telephone, String password,
			String passwordConfirm,String subscribe) {
		eleUtil.waitForVisibilityOfElement(this.firstname, AppConstant.MEDIUM_DEFAUTT_WAIT).sendKeys(firstname);
		eleUtil.doSendKeys(this.lastname, lastname);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.passwordConfirm, passwordConfirm);
		
		if(subscribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(subscribeYes);
		}
		else {
			eleUtil.doClick(subscribeNo);
		}
		
		eleUtil.doClick(conditionCheckbox);
		eleUtil.doClick(submitButton);
		
		String successMesg = eleUtil.waitForVisibilityOfElement(successMessg, AppConstant.MEDIUM_DEFAUTT_WAIT)
				.getText();
		System.out.println(successMesg);

		if (successMesg.contains(AppConstant.USER_REGISTER_SUCCESS_MESSG)) {
				eleUtil.doClick(logoutLink);
				eleUtil.doClick(registerLink);
			return true;
		} else {
			return false;
		}
	}
}
