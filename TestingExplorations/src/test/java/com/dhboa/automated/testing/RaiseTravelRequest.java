package com.dhboa.automated.testing;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class RaiseTravelRequest {

	WebDriver driver;
	private long toastWaitTime = 5000;

	Commons commons = new Commons();
	ApproverUtilities approver = new ApproverUtilities(commons);
	PortalActions portalActions = new PortalActions(commons);

	@BeforeTest
	public void beforeRaisingRequest() throws InterruptedException {
		driver = commons.getWebDriver("chrome");
		commons.maximizeWindow(driver);
	}

	/**
	 * Raising a travel request which is in policy
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void justRaisingFlightOneWayRequest() throws InterruptedException {
		String dateOfTravel = "09-09-2016 08:00:00";
		raiseFlightOneWayTravelRequest(dateOfTravel);
	}

	/**
	 * Flow Explained: Raising a travel request. Travel agent asks for
	 * clarification. Initiator will provide clarification. Travel agent will
	 * book the reservation and send it for approval. Manager will approve the
	 * task. Travel agent will confirm the task
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void travelFlow() throws InterruptedException {
		String dateOfTravel = "09-09-2016 08:00:00";
		String taskId = raiseFlightOneWayTravelRequest(dateOfTravel);
		portalActions.inboxAction(driver, taskId, "clarification", toastWaitTime, "traveldesk", false, false);
		portalActions.inboxAction(driver, taskId, "clarify", toastWaitTime, "initiator", false, true);
		portalActions.inboxAction(driver, taskId, "reservationdone", toastWaitTime, "traveldesk", true, false);
		portalActions.inboxAction(driver, taskId, "approve", toastWaitTime, "manager", false, true);
		portalActions.inboxAction(driver, taskId, "bookconfirmation", toastWaitTime, "traveldesk", false, false);
	}

	/**
	 * Flow Explained Raising a travel request which is in policy i.e., there
	 * will only be one approval. Travel agent will book the reservation and
	 * send it for approval. Manager will approve the task Travel agent will
	 * confirm the task
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void approvalFlowInPolicy() throws InterruptedException {
		String dateOfTravel = "09-09-2016 08:00:00";
		String toastMessage = raiseFlightOneWayTravelRequest(dateOfTravel);
		// Login as travel user and reserve a booking
		portalActions.inboxAction(driver, toastMessage, "reservationdone", toastWaitTime, "traveldesk", true, false);
		// Login as user who is manager and approve the action
		portalActions.inboxAction(driver, toastMessage, "approve", toastWaitTime, "manager", false, true);
		// book the ticket since manager approved
		portalActions.inboxAction(driver, toastMessage, "bookconfirmation", toastWaitTime, "traveldesk", false, false);
	}

	/**
	 * Flow Explained : Raise a travel request which is out of policy i.e.,
	 * there will be 2 level approvals. Travel desk will do the reservation.
	 * First level manager will approve the task. Second level manager will
	 * approve the task. Travel desk once confirmed by two managers will book
	 * the ticket and close the task.
	 * 
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void approvalFlowOutPolicy() throws InterruptedException {
		String dateOfTravel = "31-08-2016 08:00:00";
		String toastMessage = raiseFlightOneWayTravelRequest(dateOfTravel);
		// Login as travel user and reserve a booking
		portalActions.inboxAction(driver, toastMessage, "reservationdone", toastWaitTime, "traveldesk", true, false);
		// Login as user who is manager and approve the action
		portalActions.inboxAction(driver, toastMessage, "approve", toastWaitTime, "manager", false, true);
		// Login as user who is manager and approve the action
		portalActions.inboxAction(driver, toastMessage, "approve", toastWaitTime, "secondmanager", false, true);
		// book the ticket since manager approved
		portalActions.inboxAction(driver, toastMessage, "bookconfirmation", toastWaitTime, "traveldesk", false, false);
	}

	/**
	 * 
	 * This method will raise a flight travel request which is only one way.
	 * This method will take dateOfTravel as parameter which would be used just
	 * to differentiate an in policy with an out policy.
	 * 
	 * @param dateOfTravel
	 * @return
	 * @throws InterruptedException
	 */
	public String raiseFlightOneWayTravelRequest(String dateOfTravel) throws InterruptedException {
		commons.loginAsConsultant(driver);
		portalActions.clickOnMenuItem(driver, "travel");
		portalActions.clickOnMenuItem(driver, "newtravelrequest");
		commons.waitUntilElementVisibilityById(driver, "myId2");
		commons.clickElementById(driver, "myId2");
		commons.waitUntilElementVisibilityByXpath(driver, "//a[@data-target='#flight']");
		commons.clickElementByXpath(driver, "//a[@data-target='#flight']");
		commons.selectByVisibleTextForEleXpath(driver, "//select[@name='flightTripType']", "One Way");
		commons.selectByVisibleTextForEleXpath(driver, "//select[@name='flightTravelType']", "Domestic");
		commons.selectByVisibleTextForEleXpath(driver, "//select[@name='flightTravelClass']", "First Class");
		commons.setValueToElementById(driver, "flightFromLocation_value", "Hyderabad");
		commons.setValueToElementById(driver, "flightToLocation_value", "Delhi");
		commons.setValueToElementByXpath(driver, "//input[@ng-model='formattedDates.flightFromDate']", dateOfTravel);
		commons.setValueToElementById(driver, "otherComments", "Raising a travel request");
		commons.clickElementByXpath(driver, "//button[@class='btn btn-primary']");
		commons.selectByVisibleTextForEleXpath(driver, "//select[@name='travelReason']", "Company Purpose");
		commons.setValueToElementByXpath(driver, "//input[@name='tripDescription']", "Going simply to visit the place");

		// showing approvals
		commons.clickElementByXpath(driver, "//a[@ng-click='getPreview()']");
		Thread.sleep(toastWaitTime);
		commons.clickElementByXpath(driver, "//div[@id='myPreviewModal']//button[text()[contains(.,'Close')]]");
		commons.clickElementById(driver, "submitPop");
		String toastMessage = commons.getValueByXpath(driver, "//div[@class='toast-message']");
		toastMessage = toastMessage.substring(toastMessage.indexOf(":") + 2);
		toastMessage = toastMessage.trim();
		// Verify if it is saved
		portalActions.clickOnMenuItem(driver, "myrequests");
		portalActions.searchByReference(driver, toastMessage);
		commons.isElementPresentByXpath(driver, "//tr[@ng-repeat='request in travelRequests.content']");
		Thread.sleep(toastWaitTime);
		// Now logging out from this user
		commons.logout(driver);
		return toastMessage;
	}

	@AfterTest
	public void afterRaisingRequest() throws InterruptedException {
		// commons.logout(driver);
		driver.quit();
	}
}
