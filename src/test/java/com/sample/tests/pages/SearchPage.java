package com.sample.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.sample.framework.Configuration;
import com.sample.framework.Platform;
import com.sample.framework.ui.FindBy;
import com.sample.framework.ui.Page;
import com.sample.framework.ui.controls.Control;
import com.sample.framework.ui.controls.Edit;
import com.sample.framework.ui.controls.SelectList;

public class SearchPage extends Page {
	
	@FindBy(locator = "ss")
	@FindBy(locator = "search_searchInput", platform = Platform.ANDROID_NATIVE)
	public Edit editDestination;
	
	//@FindBy(locator = "css=sb-date-field__chevron bicon-downchevron")
	//public Control checkoutDayExpand;
	
	//@FindBy(locator = "xpath=//span[contains(.,'26')]")
	//public Control checkoutDayToday;
	
	@FindBy(locator = "xpath=//*[@id='frm']/div[3]/div/div[1]/div[1]/div[2]/div/div[2]/div[2]/div[3]/div/div/div[1]/table/tbody/tr[5]/td[5]/span")
	@FindBy(locator = "today_msg", platform = Platform.ANDROID_NATIVE)
	public Control checkinDayToday;
	
	@FindBy(locator = "xpath=//input[contains(@class,'leisure-booker')]")
	@FindBy(locator = "business_purpose_leisure", platform = Platform.ANDROID_NATIVE)
	public Control radioLeisure;
	
	@FindBy(locator = "xpath=//input[contains(@value,'business')]")
	@FindBy(locator = "business_purpose_business", platform = Platform.ANDROID_NATIVE)
	public Control radioBusiness;
	
	//@FindBy(locator = "ss")
	//public Control radioHotels;
	
	@FindBy(locator = "group_adults")
	@FindBy(locator = "adult_count_label", platform = Platform.ANDROID_NATIVE)
	public SelectList selectAdultsNumber;
	
	@FindBy(locator = "xpath=//button[@type='submit']")
	@FindBy(locator = "search_search", platform = Platform.ANDROID_NATIVE)
	public Control buttonSubmit;
	
	// works with a selector only if it selects the whole list item not just the text London
	@FindBy(locator = "xpath=//*[@id='frm']/div[2]/div/div[1]/ul[1]/li[1]")
	//this is not yet the correct locator for mobile native app
	@FindBy(locator = "search_searchInput", platform = Platform.ANDROID_NATIVE)
	public Control ajaxAutoSuggestDropListItem;		

	public SearchPage(WebDriver driver) {
		super(driver);
	}
	
	@Override
	public Page navigate() {
		if (Configuration.platform().isWeb()) {
			String baseUrl = Configuration.get("url");
			this.getDriver().get(baseUrl);			
		} 
		return this;
	}
	
	public void checkInToday() {
		checkinDayToday.click();
	}
	
	public void selectTravelFor(boolean isLeisure) {
		if (isLeisure) {
			radioLeisure.click();
		} else {
			radioBusiness.click();
		}
	}

}
