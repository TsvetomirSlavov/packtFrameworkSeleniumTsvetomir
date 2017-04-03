package com.sample.tests.controls;

import org.openqa.selenium.By;

import com.sample.framework.Configuration;
import com.sample.framework.Driver;
import com.sample.framework.ui.Page;
import com.sample.framework.ui.PageFactory;
import com.sample.framework.ui.controls.Control;
import com.sample.framework.ui.controls.Edit;
import com.sample.tests.pages.android.AndroidLocationSearchPage;

public class LocationLookupEdit extends Edit {

	public LocationLookupEdit(Page parentValue, By locator) {
		super(parentValue, locator);
		
	}
	
	@Override
	public void setText(String value) throws Exception {
		this.click();
		if (Configuration.platform().isWeb()) {
			this.element().clear();
			this.element().sendKeys(value);
			Control lookupItem = new Control(this.getParent(), 
					By.xpath("(//li[contains(@class,'autocomplete__item')])[1]"));
			//click suggestion list item
			lookupItem.click();
		} 
		// handle mobile application
		else {
			AndroidLocationSearchPage search = PageFactory.init(Driver.current(), AndroidLocationSearchPage.class);
			search.editSearch.setText(value);
			// tap suugested list item
			search.itemTopMostResult.click();
		}
		
	}
	
	

}
