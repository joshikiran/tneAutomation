package com.dhboa.automated.testing.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * 
 * Any functionality on inbox page should be written here
 * 
 * @author Darkhorse
 *
 */
public class MyInboxActions {

	CommonActions commons = null;

	public MyInboxActions(CommonActions comm) {
		// TODO Auto-generated constructor stub
		commons = comm;
	}

	public void inboxAction(WebDriver driver, String taskId, String option, long toastWaitTime, String user,
			boolean toModifyOptions, boolean isPersonal) throws InterruptedException {
		// Login as travel user
		commons.login(driver, user);

		// Now the travel user will have to approve the task
		openInboxTask(driver, taskId, isPersonal);
		if (user.equalsIgnoreCase("traveldesk") && toModifyOptions)
			modifyTravelOptions(driver);

		if (option.equalsIgnoreCase("reservationdone"))
			reservationAction(driver);
		else if (option.equalsIgnoreCase("revoke"))
			revokeAction(driver);
		else if (option.equalsIgnoreCase("clarification"))
			sendForClarificationAction(driver);
		else if (option.equalsIgnoreCase("approve"))
			approveAction(driver);
		else if (option.equalsIgnoreCase("bookconfirmation"))
			bookingConfirmation(driver);
		else if (option.equalsIgnoreCase("clarify"))
			clarifyTask(driver);
		Thread.sleep(toastWaitTime);
		collapseMyInbox(driver);
		// Logging out
		commons.logout(driver);
	}

	public void searchByReference(WebDriver driver, String referenceNo) throws InterruptedException {
		commons.clickElementByXpath(driver, "//div[@id='searchContainer']/button[@class='btn dropdown-toggle puller']");
		commons.setValueToElementById(driver, "referenceNo", referenceNo);
		commons.clickElementByXpath(driver, "//button[@ng-click='applyAndSearch()']");
	}
	
	public void openPersonalTask(WebDriver driver, String taskId) throws InterruptedException{
		openInboxTask(driver, taskId, true);
	}
	public void openTravelDeskTask(WebDriver driver, String taskId) throws InterruptedException{
		openInboxTask(driver, taskId, false);
	}

	public void openInboxTask(WebDriver driver, String taskId, boolean isPersonal) throws InterruptedException {
		commons.clickElementByXpath(driver, "//div[@data-target='#inbox']");
		if (isPersonal)
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

	public void reservationAction(WebDriver driver) throws InterruptedException {
		commons.clickElementByXpath(driver, "//button[text()[contains(.,'Reservation Done')]]");
		commons.setValueToElementByXpath(driver, "//textarea[@ng-model='postData.comments']",
				"Reservation and pricing done");
		commons.clickElementByXpath(driver, "//button[@ng-click='actOnTask()']");
	}

	public void approveAction(WebDriver driver) throws InterruptedException {
		commons.clickElementByXpath(driver, "//button[text()[contains(.,'Approve')]]");
		commons.setValueToElementByXpath(driver, "//textarea[@ng-model='postData.comments']",
				"Approving the task by the manager");
		commons.clickElementByXpath(driver, "//button[@ng-click='actOnTask()']");
	}

	public void revokeAction(WebDriver driver) throws InterruptedException {
		commons.clickElementByXpath(driver, "//button[text()[contains(.,'Revoke')]]");
		commons.setValueToElementByXpath(driver, "//textarea[@ng-model='postData.comments']",
				"Revoking the task by the travel agen");
		commons.clickElementByXpath(driver, "//button[@ng-click='actOnTask()']");
	}

	public void sendForClarificationAction(WebDriver driver) throws InterruptedException {
		commons.clickElementByXpath(driver, ".//div[@id='container']//button[text()[contains(.,'Clarification')]]");
		commons.setValueToElementByXpath(driver, "//textarea[@ng-model='postData.comments']",
				"Sending for clarification by the manager");
		commons.clickElementByXpath(driver, "//button[@ng-click='actOnTask()']");
	}

	public void bookingConfirmation(WebDriver driver) throws InterruptedException {
		commons.clickElementByXpath(driver, "//button[text()[contains(.,'Booking Done')]]");
		commons.setValueToElementByXpath(driver, "//textarea[@ng-model='postData.comments']",
				"Booking by the travel desk");
		commons.clickElementByXpath(driver, "//button[@ng-click='actOnTask()']");
	}

	public void clarifyTask(WebDriver driver) throws InterruptedException {
		commons.clickElementByXpath(driver, "//button[text()[contains(.,'Clarify')]]");
		commons.setValueToElementByXpath(driver, "//textarea[@ng-model='postData.comments']", "Clarification provided");
		commons.clickElementByXpath(driver, "//button[@ng-click='actOnTask()']");
	}

	public void collapseMyInbox(WebDriver driver) throws InterruptedException {
		boolean isMyInboxCollapsed = true;
		isMyInboxCollapsed = driver
				.findElement(By.xpath("//div[@id='inbox' and contains(@class,'collapse in')]")) != null ? false : true;
		if (!isMyInboxCollapsed)
			commons.clickElementByXpath(driver, "//div[@data-target='#inbox']");
	}
}
