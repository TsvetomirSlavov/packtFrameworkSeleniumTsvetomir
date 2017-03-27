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
		driver.get(baseUrl);
	}
	
	@After
	public void tearDown(){
		driver.quit();
	}

	@Test
	public void testValidSearch(){
		
		Edit editDestination = new Edit(driver, By.id("ss"));
		Control checkoutDayExpand = new Control(driver, By.cssSelector("sb-date-field__chevron bicon-downchevron"));
		Control checkoutDayToday = new Control(driver, By.xpath("//span[contains(.,'26')]"));
		Control checkinDayToday = new Control(driver, By.xpath("//*[@id='frm']/div[3]/div/div[1]/div[1]/div[2]/div/div[2]/div[2]/div[3]/div/div/div[1]/table/tbody/tr[5]/td[1]/span"));
		Control radioLeisure = new Control(driver, By.xpath("//input[contains(@class,'leisure-booker')]"));
		Control radioBusiness = new Control(driver, By.xpath("//input[contains(@value,'business')]"));
		SelectList selectAdultsNumber = new SelectList(driver, By.id("group_adults"));
		Control buttonSubmit = new Control(driver, By.xpath("//button[@type='submit']"));
		// works with a selector only if it selects the whole list item not just the text London
		Control ajaxAutoSuggestDropListItem = new Control(driver, By.xpath("//*[@id='frm']/div[2]/div/div[1]/ul[1]/li[1]"));		
		
		editDestination.setText(this.destination);
		ajaxAutoSuggestDropListItem.click();
		checkinDayToday.click();
		if (this.isLeisure) {
			radioLeisure.click();
		} else {
			radioBusiness.click();
		}
		selectAdultsNumber.selectByText("" + this.numberOfAdults);
		buttonSubmit.click();
		editDestination.click();
	}
	
}
