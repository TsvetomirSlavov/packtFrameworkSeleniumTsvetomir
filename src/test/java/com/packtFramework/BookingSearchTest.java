package com.packtFramework;

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

import com.packtFramework.Configuration;
import com.packtFramework.Driver;
import com.packtFramework.tests.pages.SearchPage;
import com.packtFramework.tests.pages.SearchResultsPage;
import com.packtFramework.ui.PageFactory;
import com.packtFramework.ui.controls.Control;
import com.packtFramework.ui.controls.Edit;

import com.packtFramework.ui.controls.SelectList;

@RunWith(Parameterized.class)
public class BookingSearchTest {
	
	private WebDriver driver;	
	private String destination;
	// personal or business travel
	private boolean isLeisure;
	private int numberOfAdults;		
	
	public SearchPage searchPage;
	public SearchResultsPage searchResultsPage;
	
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

	@Before
	public void setUp() throws Exception{
		Configuration.load();
		Configuration.print();
		String baseUrl = Configuration.get("url");
		// geckodriver.exe to work properly instead of just geckodriver
		System.setProperty("webdriver.gecko.driver", new File("drivers/geckodriver.exe").getAbsolutePath());
		System.setProperty("webdriver.chrome.driver", new File("drivers/chromedriver.exe").getAbsolutePath());
		
		DesiredCapabilities cap = new DesiredCapabilities();
		Driver.add(Configuration.get("browser"), cap);
		driver = Driver.current();
		searchPage = PageFactory.init(SearchPage.class);
		searchPage.navigate();
		
	}
	
	@After
	public void tearDown(){
		driver.quit();
	}

	@Test
	public void testValidSearch() throws Exception{

		searchPage.editDestination.setText(destination);
		searchPage.ajaxAutoSuggestDropListItem.click();
		searchPage.checkinDayToday.click();
		searchPage.selectTravelFor(true);
		
		searchPage.selectAdultsNumber.selectByText("" + numberOfAdults);
		searchPage.buttonSubmit.click();
		
		searchResultsPage = PageFactory.init(SearchResultsPage.class);
		searchResultsPage.editDestination.click();
		searchResultsPage.isTextPresent(destination);
	}
	
}
