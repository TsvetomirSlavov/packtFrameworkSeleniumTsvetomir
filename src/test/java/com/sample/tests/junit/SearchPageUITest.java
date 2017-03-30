package com.sample.tests.junit;

import org.junit.Test;

import junit.framework.Assert;

public class SearchPageUITest extends TestCommon {

	public SearchPageUITest() {
		// TODO Auto-generated constructor stub
	}
	
	@Test
	public void testVerifyUIOnSearchPage() {
		Assert.assertTrue(searchPage.editDestination.exists());
		//Assert.assertTrue(searchPage.checkinDayToday.exists());
		Assert.assertTrue(searchPage.radioBusiness.exists());
		Assert.assertTrue(searchPage.radioLeisure.exists());
		Assert.assertTrue(searchPage.selectAdultsNumber.exists());
		Assert.assertTrue(searchPage.buttonSubmit.exists());
	}
	
	

}
