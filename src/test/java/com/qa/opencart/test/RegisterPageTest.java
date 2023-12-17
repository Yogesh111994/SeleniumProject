package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constant.AppConstant;
import com.qa.opencart.utils.ExcelUtil;
public class RegisterPageTest extends BaseTest {

	@BeforeClass
	public void setUp() {
		registerPage = loginPage.navigateToRegisterPage();
	}
	
	public String getdifferentEmaiId() {
		return "yashraj"+System.currentTimeMillis()+"@gmail.com";
	}
	
	@DataProvider
	public Object[][] userData() {
		return new Object [] [] {
			{"yash","raj","35243654526","abc@gmail.com","abc@gmail.com","yes"},
			{"abc","deeg","35254526","deg@gmail.com","deg@gmail.com","No"},
			{"rohit","sharma","9535254526","rohit@gmail.com","rohit@gmail.com","yes"}
		};
	}
	
	@DataProvider
	public Object[][] getRegesterDataUsingExcelSheet() {
	Object regData[][]=ExcelUtil.getTestData(AppConstant.REGISTER_DATA_SHEET_NAME);
	return regData;
	}
	
	@Test(dataProvider="userData")
	public void registerationOfUser(String firstname,String lastname,String telephone, String password,
			String passwordConfirm,String subscribe) {
		boolean resDone=registerPage.registrationForm(firstname,lastname, getdifferentEmaiId(), telephone,password,
				passwordConfirm, subscribe);
		Assert.assertTrue(resDone);
	}	
}
