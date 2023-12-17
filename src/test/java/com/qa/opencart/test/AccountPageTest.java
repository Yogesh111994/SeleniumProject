package com.qa.opencart.test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constant.AppConstant;
import com.qa.opencart.page.AccountPage;

public class AccountPageTest extends BaseTest {

	@BeforeClass
	public void accSetUp() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void accPageTitleTest() {
		Assert.assertEquals(accPage.getAccountPageTitle(), AppConstant.Account_Page_Title);
	}

	@Test
	public void accPageURLTest() {
		Assert.assertTrue(accPage.getAccountPageURL().contains(AppConstant.Account_Page_URL_Fraction));
	}

	@Test
	public void isSearchFieldExistTest() {
		Assert.assertTrue(accPage.isSearchFieldExist());
	}

	@Test
	public void isLogoutLinkExistTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}

	@Test
	public void AccountPageHeaderList() {
		List<String> ActualAccountPageHeadersList = accPage.getAccountHolders();
		System.out.println(ActualAccountPageHeadersList);
		Assert.assertEquals(ActualAccountPageHeadersList, AppConstant.ACCOUNTS_PAGE_HEADERS_LIST);
	}

	@Test
	public void searchTest() {
		searchResultPage = accPage.doSearch("MacBook");
		productInfoPage = searchResultPage.selectProduct("MacBook Pro");
		String actualName = productInfoPage.getProductName();
		Assert.assertEquals(actualName, "MacBook Pro");
	}
	
	
}
