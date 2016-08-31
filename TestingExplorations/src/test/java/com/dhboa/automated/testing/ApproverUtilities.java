package com.dhboa.automated.testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ApproverUtilities {
	Commons commons = null;
	
	public ApproverUtilities(Commons commons) {
		// TODO Auto-generated constructor stub
		this.commons = commons;
	}

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
	
	public void revokeAction(WebDriver driver) throws InterruptedException{
		commons.clickElementByXpath(driver, "//button[text()[contains(.,'Revoke')]]");
		commons.setValueToElementByXpath(driver, "//textarea[@ng-model='postData.comments']", "Revoking the task by the travel agen");
		commons.clickElementByXpath(driver, "//button[@ng-click='actOnTask()']");
	}
	public void sendForClarificationAction(WebDriver driver) throws InterruptedException{
		commons.clickElementByXpath(driver, ".//div[@id='container']//button[text()[contains(.,'Clarification')]]");		
		commons.setValueToElementByXpath(driver, "//textarea[@ng-model='postData.comments']", "Sending for clarification by the manager");
		commons.clickElementByXpath(driver, "//button[@ng-click='actOnTask()']");
	}	
	public void bookingConfirmation(WebDriver driver) throws InterruptedException{
		commons.clickElementByXpath(driver, "//button[text()[contains(.,'Booking Done')]]");
		commons.setValueToElementByXpath(driver, "//textarea[@ng-model='postData.comments']", "Booking by the travel desk");
		commons.clickElementByXpath(driver, "//button[@ng-click='actOnTask()']");
	}
	public void clarifyTask(WebDriver driver) throws InterruptedException{
		commons.clickElementByXpath(driver, "//button[text()[contains(.,'Clarify')]]");
		commons.setValueToElementByXpath(driver, "//textarea[@ng-model='postData.comments']", "Clarification provided");
		commons.clickElementByXpath(driver, "//button[@ng-click='actOnTask()']");
	}
	
	public void collapseMyInbox(WebDriver driver) throws InterruptedException{
		boolean isMyInboxCollapsed = true;
		isMyInboxCollapsed = driver.findElement(By.xpath("//div[@id='inbox' and contains(@class,'collapse in')]"))!=null?false:true;
		if(!isMyInboxCollapsed)
			commons.clickElementByXpath(driver, "//div[@data-target='#inbox']");
	}
}
