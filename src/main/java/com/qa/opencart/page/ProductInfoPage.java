package com.qa.opencart.page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constant.AppConstant;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	WebDriver driver;
	ElementUtil eleUtil;

	private By productName = By.xpath("//h1[text()='MacBook Pro']");
	private By imgCount = By.xpath("//ul[@class='thumbnails']/li");
	private By productMetaData =By.xpath("(//div[@class='col-sm-4']//ul[@class='list-unstyled'])[1]/li");
	private By productPrice = By.xpath("(//div[@class='col-sm-4']//ul[@class='list-unstyled'])[2]/li");
	
	Map<String,String> productMap = new HashMap<String,String>();
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
	public String getProductName() {
	return eleUtil.waitForVisibilityOfElement(productName, AppConstant.SHORT_DEFAUTT_WAIT).getText();
	}
	
	public int getProductImageCount() {
		return eleUtil.waitForVisibilityOfElements(imgCount, AppConstant.SHORT_DEFAUTT_WAIT).size();
	}
	
	private void getMetaDataOfProduct() {
		List<WebElement> metaDataElement = eleUtil.waitForVisibilityOfElements(productMetaData, AppConstant.MEDIUM_DEFAUTT_WAIT);
	
		for(WebElement e: metaDataElement) {
			String metaData=e.getText();
			String metaKey=metaData.split(":")[0].trim();
			String metaValue=metaData.split(":")[1].trim();
			productMap.put(metaKey, metaValue);	
		}
	}
	
	private void getProductPrice() {
		List<WebElement> metaDataElement = eleUtil.waitForVisibilityOfElements(productPrice, AppConstant.MEDIUM_DEFAUTT_WAIT);
		String productPrice = metaDataElement.get(0).getText();
		String  productExTaxPrice = metaDataElement.get(1).getText().split(":")[1].trim();
		productMap.put("price", productPrice);
		productMap.put("productExtraPrice", productExTaxPrice);
		}
	
	
	public Map<String, String> getproductInfo() {
		productMap.put("productName", getProductName());
		getMetaDataOfProduct();
		getProductPrice();
		return productMap;
	}
	}
	

