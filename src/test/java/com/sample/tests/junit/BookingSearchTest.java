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

		//if (!Configuration.platform().isWeb()) {
		//	if ((recource-id com.android.packageinstaller:id/permission_allow_button).exists()) {
		//		com.android.packageinstaller:id/permission_allow_button.tap();
		//	}
		//}
		
		searchPage.editDestination.setText(destination);
		
/*		if (Configuration.platform().isWeb()) {
 * 			// I have another method to do that in LocationLookupEdit
			searchPage.ajaxAutoSuggestDropListItem.click();
		}*/
				//Part 13 again from the beginnning 
		//ToDo implement here the same different behavior with the Calendar popup
		
		searchPage.checkinDayToday.click();
		searchPage.selectTravelFor(isLeisure);
		
		//Continue on my own to add a functionality for this action to choose between Web and Mobile
		searchPage.selectAdultsNumber.selectByText("" + numberOfAdults);
		searchPage.buttonSubmit.click();
		
		searchResultsPage = PageFactory.init(Driver.current(), SearchResultsPage.class);
		//searchResultsPage.editDestination.click();
		searchResultsPage.isTextPresent(destination);
		searchResultsPage.captureScreenShot("./build/image01.png");
	}
	
}
