package com.packtFramework.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.packtFramework.ui.controls.Control;



public class Page {
	
	private WebDriver driver;

	public Page(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public WebDriver getDriver() {
		return driver;
	}
	
	public Page navigate() {
		return this;
	}
	
	public boolean isTextPresent(String text) {
		String locator = String.format("//*[text()='%s' or contains(text(), %s)]", text, text);
		Control element = new Control(this, By.xpath(locator));
		return element.exists();
	}
	
	
	
	
	

}
