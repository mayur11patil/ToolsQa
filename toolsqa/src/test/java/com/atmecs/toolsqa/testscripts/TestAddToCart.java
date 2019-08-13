package com.atmecs.toolsqa.testscripts;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.atmecs.toolsqa.constants.ProjectConstants;
import com.atmecs.toolsqa.helpers.HomepageHelper;
import com.atmecs.toolsqa.testsuite.Browser;
import com.atmecs.toolsqa.testsuite.TestSuiteBase;
import com.atmecs.toolsqa.utils.CommanUtils;
import com.atmecs.toolsqa.utils.PropertyParser;
import com.atmecs.toolsqa.utils.VerifyResult;
import com.atmecs.toolsqa.utils.XlsReader;


public class TestAddToCart extends TestSuiteBase {
	
	Browser browser = new Browser();
	HomepageHelper homepageHelper = new HomepageHelper();
	VerifyResult verify = new VerifyResult();
	XlsReader xlsReader =null;
	
	@BeforeTest
	public void preSetup() {
		xlsReader = CommanUtils.getXlsReader(ProjectConstants.TEST_DATA_FILE_PATH);
		String webURL=PropertyParser.readEnvOrConfigProperty("application_url");
		System.out.println(webURL);
		driver = browser.launchURL(webURL, browserName);		
	}
	
	@Test
	public void testAddToCart() {
		//selecting first product
		String productName1 = xlsReader.getCellDataByColumnName("testdata", "ProductName", 3);
		String quantity1 = xlsReader.getCellDataByColumnName("testdata", "Quntity", 3);
		String size1 = xlsReader.getCellDataByColumnName("testdata", "Size", 3);
		String color1 = xlsReader.getCellDataByColumnName("testdata", "Color", 3);
		
		verify.verifyTrue(homepageHelper.verifyHomePage(driver), "Verify ToolsQa homepage appears");
		verify.verifyTrue(homepageHelper.selectProductByName(driver, productName1),"Verify product is selected");
		homepageHelper.enterProductDetailsAndAddToCart(driver, quantity1, color1, size1);
		
		//selcting second product
		String productName2 = xlsReader.getCellDataByColumnName("testdata", "ProductName", 4);
		String quantity2 = xlsReader.getCellDataByColumnName("testdata", "Quntity", 4);
		String size2 = xlsReader.getCellDataByColumnName("testdata", "Size", 4);
		String color2 = xlsReader.getCellDataByColumnName("testdata", "Color", 4);
		
		homepageHelper.clickOnLogo(driver);
		verify.verifyTrue(homepageHelper.selectProductByName(driver, productName2),"Verify product is selected");
		homepageHelper.enterProductDetailsAndAddToCart(driver, quantity2, color2, size2);
		
		//verifying both product added to cart or not
		verify.verifyTrue(homepageHelper.verifyProductFromCart(driver,productName1+","+productName2),"Verify Both the product present in the cart");
		
		//verifying total of both product
		verify.verifyTrue(homepageHelper.verifyTotal(driver),"Verify price of both product is equal to total");
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
