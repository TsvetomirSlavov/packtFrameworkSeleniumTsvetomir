package com.packtFramework.ui.controls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.packtFramework.ui.Page;

public class Edit extends Control {

	public Edit(Page parentValue, By locator) {
		super(parentValue, locator);
		// TODO Auto-generated constructor stub
	}
	
	public void setText(String value) {
		this.click();
		this.element().clear();
		this.element().sendKeys(value);
	}

}
