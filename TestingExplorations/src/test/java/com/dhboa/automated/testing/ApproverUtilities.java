package com.dhboa.automated.testing;

import org.openqa.selenium.WebDriver;

public class ApproverUtilities {
	Commons commons = new Commons();

	public void openInboxTask(WebDriver driver, String taskId, boolean isPersonal) throws InterruptedException {
		commons.clickElementByXpath(driver, "//div[@data-target='#inbox']");
		if(isPersonal)
			commons.clickElementByXpath(driver, "//a[@href='#/inbox']");
		else
			commons.clickElementByXpath(driver, "//a[@href='#/inbox/TRAVEL_DESK']");
		commons.clickElementByXpath(driver, "//div[@id='searchContainer']/button[@class='btn dropdown-toggle puller']");
		commons.setValueToElementById(driver, "referenceNo", taskId);
		commons.clickElementByXpath(driver, "//button[@ng-click='applyAndSearch()']");
		commons.clickElementByXpath(driver,
				"//tr[@ng-repeat='task in tasks.content']/td[@ng-if='!isExpenseTask(task)']/a");
	}

	public void modifyTravelOptions(WebDriver driver) throws InterruptedException {
		commons.clickElementByXpath(driver, "//a[@data-target='#flightModal']");
		commons.selectByVisibleTextForEleXpath(driver, "//select[@ng-model='modalRequest.provider']", "JetAirways");
		commons.setValueToElementByXpath(driver, "//input[@ng-model='modalRequest.bookingCost']", "40000");
		commons.setValueToElementByXpath(driver, "//textarea[@ng-model='modalRequest.travelDeskComments']",
				"Please approve it urgently as the cost is varying");
		commons.clickElementByXpath(driver, "//button[text()='Save']");
	}
	
	public void reservationAction(WebDriver driver) throws InterruptedException{
		commons.clickElementByXpath(driver, "//button[text()[contains(.,'Reservation Done')]]");
		commons.setValueToElementByXpath(driver, "//textarea[@ng-model='postData.comments']", "Reservation and pricing done");
		commons.clickElementByXpath(driver, "//button[@ng-click='actOnTask()']");
	}
	
	public void approveAction(WebDriver driver) throws InterruptedException{
		commons.clickElementByXpath(driver, "//button[text()[contains(.,'Approve')]]");
		commons.setValueToElementByXpath(driver, "//textarea[@ng-model='postData.comments']", "Approving the task by the manager");
		commons.clickElementByXpath(driver, "//button[@ng-click='actOnTask()']");
	}
	
	public void bookingConfirmation(WebDriver driver) throws InterruptedException{
		commons.clickElementByXpath(driver, "//button[text()[contains(.,'Booking Done')]]");
		commons.setValueToElementByXpath(driver, "//textarea[@ng-model='postData.comments']", "Booking by the travel desk");
		commons.clickElementByXpath(driver, "//button[@ng-click='actOnTask()']");
	}
}
