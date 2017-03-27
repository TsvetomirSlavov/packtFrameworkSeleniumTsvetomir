package com.packtFramework.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.packtFramework.Configuration;
import com.packtFramework.ui.FindBy;
import com.packtFramework.ui.Page;
import com.packtFramework.ui.controls.Control;
import com.packtFramework.ui.controls.Edit;
import com.packtFramework.ui.controls.SelectList;

public class SearchPage extends Page {
	
	@FindBy(locator = "ss")
	public Edit editDestination;
	//@FindBy(locator = "css=sb-date-field__chevron bicon-downchevron")
	//public Control checkoutDayExpand;
	//@FindBy(locator = "xpath=//span[contains(.,'26')]")
	//public Control checkoutDayToday;
	@FindBy(locator = "xpath=//*[@id='frm']/div[3]/div/div[1]/div[1]/div[2]/div/div[2]/div[2]/div[3]/div/div/div[1]/table/tbody/tr[5]/td[2]/span")
	public Control checkinDayToday;
	@FindBy(locator = "xpath=//input[contains(@class,'leisure-booker')]")
	public Control radioLeisure;
	@FindBy(locator = "xpath=//input[contains(@value,'business')]")
	public Control radioBusiness;
	//@FindBy(locator = "ss")
	//public Control radioHotels;
	@FindBy(locator = "group_adults")
	public SelectList selectAdultsNumber;
	@FindBy(locator = "xpath=//button[@type='submit']")
	public Control buttonSubmit;
	// works with a selector only if it selects the whole list item not just the text London
	@FindBy(locator = "xpath=//*[@id='frm']/div[2]/div/div[1]/ul[1]/li[1]")
	public Control ajaxAutoSuggestDropListItem;		

	public SearchPage(WebDriver driver) {
		super(driver);
	}
	
	@Override
	public Page navigate() {
		String baseUrl = Configuration.get("url");
		this.getDriver().get(baseUrl);
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
