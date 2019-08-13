package com.atmecs.toolsqa.pages;



import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.atmecs.toolsqa.constants.ProjectConstants;
import com.atmecs.toolsqa.utils.CommanUtils;
import com.atmecs.toolsqa.utils.PropertiesUtil;

public class Homepage {
	
	CommanUtils commanUtils = new CommanUtils();
	
	static Properties homePage = PropertiesUtil.loadProperty(ProjectConstants.HOMEPAGE_LOCATORS);
	
	//all getters to get xpath
	public String getLogoLinkXpath() {
		return homePage.getProperty("logo_link");
	}
	
	public String getDressLinkXpath(String dressName) {
		return homePage.getProperty("product_dress_link").replace("xxxxx", dressName);
	}
	
	public String getProductHeadingXpath() {
		return homePage.getProperty("selected_product_heading");
	}
	
	public String getColorOfProductXpath() {
		return homePage.getProperty("color_dropdown");
	}
	
	public String getSizeOfProductXpath() {
		return homePage.getProperty("size_dropdown");
	}
	
	public String getQuantityOfProductXpath() {
		return homePage.getProperty("color_dropdown");
	}
	public String getAddToCartButtonXpath() {
		return homePage.getProperty("add_to_cart_button");
	}
	public String getGoToCartLogoXpath() {
		return homePage.getProperty("go_to_cart_logo");
	}
	
	public String getProductNameFromCartXpath(String productName) {
		return homePage.getProperty("product_name_in_cartlist").replace("xxxxx", productName);
	}
	
	public String getAllProductPriceFromCartXpath() {
		return homePage.getProperty("individual_product_price_fromcart");
	}
	
	public String getTotalPriceFromCartXpath() {
		return homePage.getProperty("total_price_cart");
	}
	
	public String getSearchBarXpath() {
		return homePage.getProperty("search_bar");
	}
	public String getSearchTextBoxXpath() {
		return homePage.getProperty("search_textbox");
	}
	public String getRemoveFirstProductIcon() {
		return homePage.getProperty("remove_firstproduct_icon");
	}
	
	//all actions related homepage
	
	//click action
	public boolean clickOnDressLink(WebDriver driver, String dressName) {
		boolean isClicked = false;
		if(commanUtils.isElementPresentAndClickable(driver, getDressLinkXpath(dressName))) {
			driver.findElement(By.xpath(getDressLinkXpath(dressName))).click();
			isClicked = true;
		}
		System.out.println("Clicked on Product :"+isClicked);
		return true;
	}
	
	public boolean clickOnAddToCartButton(WebDriver driver) {
		boolean isClicked = false;
		if(commanUtils.isElementPresentAndClickable(driver, getAddToCartButtonXpath())) {
			driver.findElement(By.xpath(getAddToCartButtonXpath())).click();
			isClicked = true;
		}
		System.out.println("Clicked on add to cart :"+isClicked);
		return true;
	}
	
	public boolean clickOnGoToCartLogo(WebDriver driver) {
		boolean isClicked = false;
		if(commanUtils.isElementPresentAndClickable(driver, getGoToCartLogoXpath())) {
			driver.findElement(By.xpath(getGoToCartLogoXpath())).click();
			isClicked = true;
		}
		System.out.println("Clicked on Go to cart :"+isClicked);
		return true;
	}
	
	public boolean clickOnToolsQaLogoLink(WebDriver driver) {
		boolean isClicked = false;
		if(commanUtils.isElementPresentAndClickable(driver, getLogoLinkXpath())) {
			driver.findElement(By.xpath(getLogoLinkXpath())).click();
			isClicked = true;
		}
		System.out.println("Clicked on Tools Qa Logo :"+isClicked);
		return true;
	}
	
	public boolean clickOnSearchBar(WebDriver driver) {
		boolean isClicked = false;
		if(commanUtils.isElementPresentAndClickable(driver, getSearchBarXpath())) {
			driver.findElement(By.xpath(getSearchBarXpath())).click();
			isClicked = true;
		}
		System.out.println("Clicked on searchbox :"+isClicked);
		return true;
	}
	public boolean clickOnRemoveProductIcon(WebDriver driver) {
		boolean isClicked = false;
		if(commanUtils.isElementPresentAndClickable(driver, getRemoveFirstProductIcon())) {
			driver.findElement(By.xpath(getRemoveFirstProductIcon())).click();
			isClicked = true;
		}
		System.out.println("Clicked on remove icon :"+isClicked);
		return true;
	}
	
	
	
	//get text action
	public String getProductHeading(WebDriver driver) {
		String heading="";
		if(commanUtils.isElementVisible(driver, getProductHeadingXpath())) {
			heading=driver.findElement(By.xpath(getProductHeadingXpath())).getText();
			System.out.println("selected product heading is :"+heading);
		}
		return heading;
	}
	
	
	
	//visibilty
	public boolean isToolsQaLogoDisplay(WebDriver driver) {
		boolean isDisaplay = false;
		isDisaplay =CommanUtils.isDisplayed(driver, getLogoLinkXpath());
	
		System.out.println("Toolsqa Logo page isDisplay :"+isDisaplay);
		return isDisaplay;
	}
	
	public boolean isProductPresentInCart(WebDriver driver, String productName) {
		return commanUtils.isElementPresentAndClickable(driver, getProductNameFromCartXpath(productName));
	}
	
	//select dropdown action
	public boolean selectColorOfProduct(WebDriver driver, String colorName) {
		boolean isSelected = false;
		commanUtils.scrollIntoView(driver, false, driver.findElement(By.xpath(getColorOfProductXpath())));
		Select dropdown = new Select(driver.findElement(By.xpath(getColorOfProductXpath())));  
		dropdown.selectByVisibleText(colorName);
		System.out.println("selected color of product :"+colorName);
		return isSelected;	
	}
	public boolean selectSizeOfProduct(WebDriver driver, String size) {
		boolean isSelected = false;
		Select dropdown = new Select(driver.findElement(By.xpath(getSizeOfProductXpath())));  
		dropdown.selectByVisibleText(size);
		System.out.println("selected size of product :"+size);
		return isSelected;	
	}
	
	//enter to textbox action
	public boolean enterQuantityOfProduct(WebDriver driver,String quantity) {
		driver.findElement(By.xpath(getQuantityOfProductXpath())).sendKeys(quantity);
		System.out.println("Quntity of product enter "+quantity);
		return true;
	}
	public boolean enterProductNameToSearch(WebDriver driver,String productName) {
		driver.findElement(By.xpath(getSearchTextBoxXpath())).sendKeys(productName+Keys.ENTER);
		System.out.println("Producctname is enter :"+productName);
		return true;
	}
	
	
	//get
	public Double getTotalOfProductFromcart(WebDriver driver) {
		double total=0;
		String text="";
		List<WebElement> elements;
		
		elements = driver.findElements(By.xpath(getAllProductPriceFromCartXpath()));
		for(WebElement element:elements) {
			text = element.getText();
			total = total + Double.parseDouble(text.substring(1,text.length()));
		}
		
		return total;
	}
	public Double getTotalFromCart(WebDriver driver) {
		String text="";
		double total=0;
		text = driver.findElement(By.xpath(getTotalPriceFromCartXpath())).getText();
		total = total + Double.parseDouble(text.substring(1,text.length()));
		
		return total;
	}
	
	

}
