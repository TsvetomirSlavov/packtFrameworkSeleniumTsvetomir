package com.packtFramework.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.packtFramework.ui.FindBy;
import com.packtFramework.ui.Page;
import com.packtFramework.ui.controls.Edit;

public class SearchResultsPage extends Page {
	
	@FindBy(locator = "ss")
	public Edit editDestination;

	public SearchResultsPage(WebDriver driver) {
		super(driver);
	}

}
