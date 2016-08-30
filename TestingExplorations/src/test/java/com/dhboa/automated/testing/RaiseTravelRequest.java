package com.dhboa.automated.testing;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class RaiseTravelRequest {
	
	WebDriver driver;
	
	Commons commons = new Commons();
	
	@BeforeTest
	public void beforeRaisingRequest() throws InterruptedException{
		driver = commons.getWebDriver("firefox");
		commons.maximizeWindow(driver);
		commons.loginAsConsultant(driver);
	}
	
	@Test
	public void raiseFlightTravelRequest() throws InterruptedException{
		commons.clickElementByXpath(driver, "//div[@data-target='#travel']");
		commons.clickElementByXpath(driver, "//a[@href='#/travel/new']");
		commons.waitUntilElementVisibilityById(driver, "myId2");
		commons.clickElementById(driver, "myId2");
		commons.waitUntilElementVisibilityByXpath(driver, "//a[@data-target='#flight']");
		commons.clickElementByXpath(driver, "//a[@data-target='#flight']");
		commons.selectByVisibleTextForEleXpath(driver, "//select[@name='flightTripType']", "One Way");
		commons.selectByVisibleTextForEleXpath(driver, "//select[@name='flightTravelType']", "Domestic");
		commons.selectByVisibleTextForEleXpath(driver, "//select[@name='flightTravelClass']", "First Class");
		commons.setValueToElementById(driver, "flightFromLocation_value", "Hyderabad");
		commons.setValueToElementById(driver, "flightToLocation_value", "Delhi");
		commons.setValueToElementByXpath(driver, "//input[@ng-model='formattedDates.flightFromDate']", "30-08-2016 08:00:00");
		commons.setValueToElementById(driver, "otherComments", "Raising a travel request");
		commons.clickElementByXpath(driver, "//button[@class='btn btn-primary']");
		commons.selectByVisibleTextForEleXpath(driver, "//select[@name='travelReason']", "Company Purpose");
		commons.setValueToElementByXpath(driver, "//input[@name='tripDescription']", "Going simply to visit the place");
		commons.clickElementById(driver, "submitPop");
		String toastMessage = commons.getValueByXpath(driver, "//div[@class='toast-message']");
		toastMessage = toastMessage.substring(toastMessage.indexOf(":")+2);
		toastMessage = toastMessage.trim();
		
		//Verify if it is saved
		commons.clickElementByXpath(driver, "//a[@href='#/travel/myrequests']");
		commons.clickElementByXpath(driver, "//div[@id='searchContainer']/button[@class='btn dropdown-toggle puller']");
		commons.setValueToElementById(driver, "referenceNo", toastMessage);		
		commons.clickElementByXpath(driver, "//button[@ng-click='applyAndSearch()']");
		
		commons.isElementPresentByXpath(driver, "//tr[@ng-repeat='request in travelRequests.content']");
		
		Thread.sleep(5000);
	}
	
	@AfterTest
	public void afterRaisingRequest() throws InterruptedException{
		commons.logout(driver);
		driver.quit();
	}
}
