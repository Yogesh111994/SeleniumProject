package com.qa.opencart.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constant.AppConstant;
import com.qa.opencart.utils.ExcelUtil;

public class ProductResultPageTest extends BaseTest {

	@BeforeClass
	public void accSetUp() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] productData(){
		return new Object[][] {
			{"MacBook", "MacBook Pro", 4},
			{"MacBook", "MacBook Air", 4},
			{"iMac", "iMac", 3},
			{"Samsung", "Samsung SyncMaster 941BW", 1}
		};
	}
	
	@DataProvider
	public Object[][] excelSheetProductData() {
		 Object[][] productData = ExcelUtil.getTestData(AppConstant.PRODUCT_DATA_SHEET_NAME);
		 return productData;
	}
	
	
	@Test(dataProvider="productData")
	public void getProductImageCountTest(String searchKey, String productName, int imageCount) {
		searchResultPage = accPage.doSearch(searchKey);
		productInfoPage = searchResultPage.selectProduct(productName);
		int imgCount=productInfoPage.getProductImageCount();
		Assert.assertEquals(imgCount, imageCount);
		
	}
	@Test
	public void productInfoTest() {
		searchResultPage = accPage.doSearch("MacBook");
		productInfoPage = searchResultPage.selectProduct("MacBook Pro");
		Map<String, String> productInfoMap = productInfoPage.getproductInfo();
		softAssert.assertEquals(productInfoMap.get("productName"), "MacBook Pro");
		softAssert.assertEquals(productInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(productInfoMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(productInfoMap.get("Reward Points"), "800");
		softAssert.assertEquals(productInfoMap.get("Availability"), "In Stock");

		softAssert.assertEquals(productInfoMap.get("price"), "$2,000.00");
		softAssert.assertEquals(productInfoMap.get("productExtraPrice"), "$2,000.00");
		softAssert.assertAll();
	}
}
