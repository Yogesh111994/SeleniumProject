package com.qa.opencart.page;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constant.AppConstant;
import com.qa.opencart.utils.ElementUtil;

public class AccountPage {

	WebDriver driver;
	ElementUtil eleUtil;

	public AccountPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	private By logout = By.linkText("Logout");
	private By searchField = By.name("search");
	private By accHeader = By.xpath("//div[@id='content']/h2");
	private By searchButton= By.xpath("//span[@class='input-group-btn']");

	public String getAccountPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstant.Account_Page_Title, AppConstant.SHORT_DEFAUTT_WAIT);
		System.out.println("Account page title : " + title);
		return title; 
	}

	public String getAccountPageURL() {
		String url = eleUtil.waitForURLContains(AppConstant.Account_Page_URL_Fraction, AppConstant.SHORT_DEFAUTT_WAIT);
		System.out.println("Account page URL : " + url);
		return url;
	}

	public boolean isLogoutLinkExist() {
		return eleUtil.waitForVisibilityOfElement(logout, AppConstant.SHORT_DEFAUTT_WAIT).isDisplayed();
	}

	public void logout() {
		if (isLogoutLinkExist()) {
			eleUtil.doClick(logout);
		}
	}

	public boolean isSearchFieldExist() {
		return eleUtil.waitForVisibilityOfElement(searchField, AppConstant.SHORT_DEFAUTT_WAIT).isDisplayed();
	}

	public List<String> getAccountHolders() {
		List<WebElement> AccHeadersList = eleUtil.waitForVisibilityOfElements(accHeader, AppConstant.SHORT_DEFAUTT_WAIT);
		List<String> headers = new ArrayList<String>();
		for (WebElement e : AccHeadersList) {
			String header = e.getText();
			headers.add(header);
		}
		return headers;
	}
	
	public SearchResultPage doSearch(String searchKey) {
		eleUtil.waitForVisibilityOfElement(searchField, AppConstant.MEDIUM_DEFAUTT_WAIT).clear();
		eleUtil.waitForVisibilityOfElement(searchField, AppConstant.MEDIUM_DEFAUTT_WAIT).sendKeys(searchKey);
		eleUtil.doClick(searchButton);
		return new SearchResultPage(driver);
	}
}
