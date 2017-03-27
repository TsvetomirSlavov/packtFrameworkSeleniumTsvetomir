package com.packtFramework.ui.controls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Edit extends Control {

	public Edit(WebDriver driver, By locator) {
		super(driver, locator);
		// TODO Auto-generated constructor stub
	}
	
	public void setText(String value) {
		this.click();
		this.getElement().clear();
		this.getElement().sendKeys(value);
	}

}
