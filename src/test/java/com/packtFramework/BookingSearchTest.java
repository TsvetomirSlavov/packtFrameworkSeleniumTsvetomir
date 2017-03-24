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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

import com.packtFramework.Configuration;
import com.packtFramework.Driver;

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

		driver.findElement(By.id("ss")).click();
		driver.findElement(By.id("ss")).clear();
		driver.findElement(By.id("ss")).sendKeys(this.destination);
		driver.findElement(By.xpath("//div[@class='sb-date-field__display']")).click();
		driver.findElement(By.xpath("//span[contains(.,'23')]")).click();
		new Select(driver.findElement(By.id("group_adults"))).selectByVisibleText("1");
		driver.findElement(By.cssSelector("#group_adults > option[value=\"1\"]")).click();
		driver.findElement(By.xpath("//button[@class='sb-searchbox__button  ']")).click();
		driver.findElement(By.id("ss")).click();
	}
	
}
