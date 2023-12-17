package com.qa.opencart.constant;

import java.util.Arrays;
import java.util.List;

public class AppConstant {

	public static final String Login_Page_Title = "Account Login";
	public static final String Account_Page_Title = "My Account";
	public static final String Login_Page_URL_Fraction = "route=account/login";
	public static final String Account_Page_URL_Fraction = "route=account/account";

	public static final int SHORT_DEFAUTT_WAIT = 5;
	public static final int MEDIUM_DEFAUTT_WAIT = 10;
	public static final int LONG_DEFAUTT_WAIT = 20;

	public static final int POLLING_DEFAUTT_WAIT = 2;

	public static final int ACCOUNTS_PAGE_HEADERS_COUNT = 4;

	public static final List<String> ACCOUNTS_PAGE_HEADERS_LIST = Arrays.asList("My Account", "My Orders",
			"My Affiliate Account", "Newsletter");
	public static final String USER_REGISTER_SUCCESS_MESSG = "Your Account Has Been Created!";
	
	public static final String REGISTER_DATA_SHEET_NAME = "register";
	public static final String  PRODUCT_DATA_SHEET_NAME = "Sheet1";
}
