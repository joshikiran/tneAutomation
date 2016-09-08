package com.dhboa.automated.testing;

import org.openqa.selenium.WebDriver;

public class PortalActions {

	Commons commons = null;
	ApproverUtilities approver = null;

	public PortalActions(Commons commons) {
		// TODO Auto-generated constructor stub
		this.commons = commons;
		approver = new ApproverUtilities(commons);

	}

	// Clicking on the menu item of the tne home page
	public void clickOnMenuItem(WebDriver driver, String menuItemName) throws InterruptedException {
		if (menuItemName.equalsIgnoreCase("travel"))
			commons.clickElementByXpath(driver, "//div[@data-target='#travel']");
		else if (menuItemName.equalsIgnoreCase("expense"))
			commons.clickElementByXpath(driver, "//div[@data-target='#expense']");
		else if (menuItemName.equalsIgnoreCase("myinbox"))
			commons.clickElementByXpath(driver, "//div[@data-target='#inbox']");
		else if (menuItemName.equalsIgnoreCase("reports"))
			commons.clickElementByXpath(driver, "//div[@data-target='#reports']");
		else if (menuItemName.equalsIgnoreCase("newtravelrequest"))
			commons.clickElementByXpath(driver, "//a[@href='#/travel/new']");
		else if (menuItemName.equalsIgnoreCase("myrequests"))
			commons.clickElementByXpath(driver, "//a[@href='#/travel/myrequests']");
		else if (menuItemName.equalsIgnoreCase("mytraveldrafts"))
			commons.clickElementByXpath(driver, "//a[@href='#/travel/mydrafts']");
		else if (menuItemName.equalsIgnoreCase("newexpenseclaim"))
			commons.clickElementByXpath(driver, "//a[@href='#/expense/new']");
		else if (menuItemName.equalsIgnoreCase("myclaims"))
			commons.clickElementByXpath(driver, "//a[@href='#/expense/myclaims']");
		else if (menuItemName.equalsIgnoreCase("myclaimdrafts"))
			commons.clickElementByXpath(driver, "//a[@href='#/expense/mydrafts']");
		else if (menuItemName.equalsIgnoreCase("personaltask"))
			commons.clickElementByXpath(driver, "//a[@href='#/inbox']");
		else if (menuItemName.equalsIgnoreCase("traveltask"))
			commons.clickElementByXpath(driver, "//a[@href='#/inbox/TRAVEL_DESK']");
		else if (menuItemName.equalsIgnoreCase("viewreports"))
			commons.clickElementByXpath(driver, "#/reports/manage");
		else if (menuItemName.equalsIgnoreCase("viewdashboards"))
			commons.clickElementByXpath(driver, "#/dashboard/view");
	}

	public void clickOnSettings(WebDriver driver, String option) throws InterruptedException {
		commons.clickElementByXpath(driver, "//a[contains(@class,'dropdown-toggle') and @data-toggle='dropdown']");
		commons.waitUntilElementVisibilityByXpath(driver, "//a[@ng-click='logout()']");
		if (option.equalsIgnoreCase("mypolicies"))
			commons.clickElementByXpath(driver, "//a[@ng-click='showMyPolicies()']");
		else if (option.equalsIgnoreCase("myprofile"))
			commons.clickElementByXpath(driver, "//a[@href='#/profile']");
		else if (option.equalsIgnoreCase("logout"))
			commons.clickElementByXpath(driver, "//a[@ng-click='logout()']");
	}

	public void inboxAction(WebDriver driver, String taskId, String option, long toastWaitTime, String userRole,
			boolean toModifyOptions, boolean isPersonal) throws InterruptedException {
		// Login as travel user
		// beforeRaisingRequest();
		if (userRole.equalsIgnoreCase("traveldesk"))
			commons.loginAsTravel(driver);
		if (userRole.equalsIgnoreCase("manager"))
			commons.loginAsUser(driver);
		if (userRole.equalsIgnoreCase("secondmanager"))
			commons.loginAsRakesh(driver);
		if (userRole.equalsIgnoreCase("initiator"))
			commons.loginAsConsultant(driver);
		
		// Now the travel user will have to approve the task
		approver.openInboxTask(driver, taskId, isPersonal);
		if (userRole.equalsIgnoreCase("traveldesk") && toModifyOptions)
			approver.modifyTravelOptions(driver);

		if (option.equalsIgnoreCase("reservationdone"))
			approver.reservationAction(driver);
		else if (option.equalsIgnoreCase("revoke"))
			approver.revokeAction(driver);
		else if (option.equalsIgnoreCase("clarification"))
			approver.sendForClarificationAction(driver);
		else if (option.equalsIgnoreCase("approve"))
			approver.approveAction(driver);
		else if (option.equalsIgnoreCase("bookconfirmation"))
			approver.bookingConfirmation(driver);
		else if(option.equalsIgnoreCase("clarify"))
			approver.clarifyTask(driver);
		approver.collapseMyInbox(driver);
		Thread.sleep(toastWaitTime);
		// Logging out
		commons.logout(driver);
	}

	public void searchByReference(WebDriver driver, String referenceNo) throws InterruptedException {
		commons.clickElementByXpath(driver, "//div[@id='searchContainer']/button[@class='btn dropdown-toggle puller']");
		commons.setValueToElementById(driver, "referenceNo", referenceNo);
		commons.clickElementByXpath(driver, "//button[@ng-click='applyAndSearch()']");
	}
}
