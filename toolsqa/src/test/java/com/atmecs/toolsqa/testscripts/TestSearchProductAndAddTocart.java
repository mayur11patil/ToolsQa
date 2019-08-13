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

public class TestSearchProductAndAddTocart extends TestSuiteBase {
	
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
	public void TestSearchProductToAddToCart() {
		//selecting first product
		String productName1 = xlsReader.getCellDataByColumnName("testdata", "ProductName", 1);
		String quantity1 = xlsReader.getCellDataByColumnName("testdata", "Quntity", 1);
		String size1 = xlsReader.getCellDataByColumnName("testdata", "Size", 1);
		String color1 = xlsReader.getCellDataByColumnName("testdata", "Color", 1);
		
		verify.verifyTrue(homepageHelper.verifySearchProduct(driver,productName1), "Verify productname enter to search is appears");
		verify.verifyTrue(homepageHelper.verifyHomePage(driver), "Verify ToolsQa homepage appears");
		verify.verifyTrue(homepageHelper.selectProductByName(driver, productName1),"Verify product is selected");
		homepageHelper.enterProductDetailsAndAddToCart(driver, quantity1, color1, size1);
		
		//selecting second product
		String productName2 = xlsReader.getCellDataByColumnName("testdata", "ProductName", 2);
		String quantity2 = xlsReader.getCellDataByColumnName("testdata", "Quntity", 2);
		String size2 = xlsReader.getCellDataByColumnName("testdata", "Size", 2);
		String color2 = xlsReader.getCellDataByColumnName("testdata", "Color", 2);
		
		homepageHelper.clickOnLogo(driver);
		verify.verifyTrue(homepageHelper.verifySearchProduct(driver,productName2), "Verify productname enter to search is appears");
		verify.verifyTrue(homepageHelper.selectProductByName(driver, productName2),"Verify product is selected");
		homepageHelper.enterProductDetailsAndAddToCart(driver, quantity2, color2, size2);
		
		//verifying both product added to cart or not
		verify.verifyTrue(homepageHelper.verifyProductFromCart(driver,productName1+","+productName2),"Verify Both the product present in the cart");
		
		//Remove one product from cart and then verify total
		verify.verifyTrue(homepageHelper.removeProductAndVerifyTotal(driver),"Verify price of both product is equal to total");
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
}
