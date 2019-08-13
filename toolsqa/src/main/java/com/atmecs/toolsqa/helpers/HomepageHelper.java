package com.atmecs.toolsqa.helpers;


import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;


import com.atmecs.toolsqa.pages.Homepage;
import com.atmecs.toolsqa.utils.VerifyResult;


public class HomepageHelper {
	
	Homepage homePage = new Homepage();
	VerifyResult verify = new VerifyResult();
	
	public boolean verifyHomePage(WebDriver driver) {
		return homePage.isToolsQaLogoDisplay(driver);	
	}
	
	public boolean selectProductByName(WebDriver driver,String productName) {
		
		homePage.clickOnDressLink(driver, productName);
		verify.verifyTrue(homePage.getProductHeading(driver).equalsIgnoreCase(productName),"Verify Navigate to product details page");
	
		return true;
	}
	
	public boolean enterProductDetailsAndAddToCart(WebDriver driver, String quantity, String color, String size) {
		homePage.selectColorOfProduct(driver, color);
		homePage.selectSizeOfProduct(driver, size);
		homePage.enterQuantityOfProduct(driver, quantity);
		homePage.clickOnAddToCartButton(driver);
		System.out.println("All the product details are enter");
		return true;
	}
	
	public boolean verifyProductFromCart(WebDriver driver,String productNames) {
		boolean isProductPresent = false;
		homePage.clickOnGoToCartLogo(driver);
		
		String[] products = productNames.split(",");
		List<String> listOfProducts = Arrays.asList(products);

		for(String productName:listOfProducts) {
			isProductPresent =homePage.isProductPresentInCart(driver, productName);	
		}
		
		return isProductPresent;
	}
	
	public boolean verifyTotal(WebDriver driver) {
		if(homePage.getTotalOfProductFromcart(driver).equals(homePage.getTotalFromCart(driver)))
			return true;
		
		return false;
	}
	
	public boolean clickOnLogo(WebDriver driver) {
		return homePage.clickOnToolsQaLogoLink(driver);
	}
	
	public boolean verifySearchProduct(WebDriver driver, String productName) {
		homePage.clickOnSearchBar(driver);
		homePage.enterProductNameToSearch(driver, productName);
		if(homePage.getProductHeading(driver).equalsIgnoreCase(productName))
			return true;
		return false;
	}
	
	public boolean removeProductAndVerifyTotal(WebDriver driver) {
		homePage.clickOnRemoveProductIcon(driver);
		return verifyTotal(driver);
	}
}
