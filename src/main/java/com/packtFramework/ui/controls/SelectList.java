package com.packtFramework.ui.controls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import com.packtFramework.ui.Page;

public class SelectList extends Control {

	public SelectList(Page parentValue, By locator) {
		super(parentValue, locator);
		// TODO Auto-generated constructor stub
	}
	
	public Select getSelect() {
		return new Select(super.element());
	}
	
	public void selectByText(String value) {
		this.exists();
		this.getSelect().selectByVisibleText(value);
	}

}
