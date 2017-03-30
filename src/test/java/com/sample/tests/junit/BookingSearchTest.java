package com.sample.tests.junit;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

import com.sample.framework.Configuration;
import com.sample.framework.Driver;
import com.sample.framework.ui.PageFactory;
import com.sample.framework.ui.controls.Control;
import com.sample.framework.ui.controls.Edit;
import com.sample.framework.ui.controls.SelectList;
import com.sample.tests.pages.SearchPage;
import com.sample.tests.pages.SearchResultsPage;

@RunWith(Parameterized.class)
public class BookingSearchTest extends TestCommon{
	
	private WebDriver driver;	
	private String destination;
	// personal or business travel
	private boolean isLeisure;
	private int numberOfAdults;		
	

	
	public BookingSearchTest(String destination, boolean isLeisure, int numberOfAdults) {
		super();
		this.destination = destination;
		this.isLeisure = isLeisure;
		this.numberOfAdults = numberOfAdults;
	}
	
	@Parameters
	public static Collection<Object[]> getParameters(){
		return Arrays.asList(
			new Object[][] {
				{"London", true, 2},
				{"Manchester", false, 1}
			});
	}	

	@Test
	public void testValidSearch() throws Exception{

		searchPage.editDestination.setText(destination);
		searchPage.ajaxAutoSuggestDropListItem.click();
		searchPage.checkinDayToday.click();
		searchPage.selectTravelFor(true);
		
		searchPage.selectAdultsNumber.selectByText("" + numberOfAdults);
		searchPage.buttonSubmit.click();
		
		searchResultsPage = PageFactory.init(Driver.current(), SearchResultsPage.class);
		searchResultsPage.editDestination.click();
		searchResultsPage.isTextPresent(destination);
		searchResultsPage.captureScreenShot("./build/image01.png");
	}
	
}
