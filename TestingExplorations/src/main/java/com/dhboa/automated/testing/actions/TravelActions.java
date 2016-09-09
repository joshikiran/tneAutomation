package com.dhboa.automated.testing.actions;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;

public class TravelActions {
	CommonActions commons = null;
	MyInboxActions myInboxActions = null;

	public TravelActions(CommonActions comm) {
		// TODO Auto-generated constructor stub
		this.commons = comm;
		myInboxActions = new MyInboxActions(comm);
	}

	public void clickOnTravel(WebDriver driver) throws InterruptedException {
		commons.clickOnMenuItem(driver, "travel");
	}

	public void clickOnMyRequests(WebDriver driver) throws InterruptedException {
		commons.clickOnMenuItem(driver, "myrequests");
	}

	public void clickOnNewTravelRequest(WebDriver driver) throws InterruptedException {
		commons.clickOnMenuItem(driver, "newtravelrequest");
	}

	public void clickOnMyDrafts(WebDriver driver) throws InterruptedException {
		commons.clickOnMenuItem(driver, "mytraveldrafts");
	}

	public void showApprovers(WebDriver driver) throws InterruptedException {
		// showing approvals
		commons.clickElementByXpath(driver, "//a[@ng-click='getPreview()']");
		Thread.sleep(commons.longest);
		commons.clickElementByXpath(driver, "//div[@id='myPreviewModal']//button[text()[contains(.,'Close')]]");
	}

	public String submitTravelRequest(WebDriver driver) throws InterruptedException {
		commons.clickElementById(driver, "submitPop");
		String toastMessage = commons.getValueByXpath(driver, "//div[@class='toast-message']");
		toastMessage = toastMessage.substring(toastMessage.indexOf(":") + 2);
		toastMessage = toastMessage.trim();
		return toastMessage;
	}

	public void checkMyRequestForATransaction(WebDriver driver, String taskId) throws InterruptedException {
		// Verify if it is saved
		myInboxActions.searchByReference(driver, taskId);
		commons.checkElementPresentByXpath(driver, "//tr[@ng-repeat='request in travelRequests.content']");
		commons.clickOnMenuItem(driver, "travel");
		Thread.sleep(commons.longest);
	}

	public void checkDraftsByTravelDescription(WebDriver driver, String travelDescription) throws InterruptedException {
		// It is assumed that you are on drafts page
		commons.checkElementPresentByXpath(driver,
				".//*[@id='container']//table/tbody/tr[td/text()='" + travelDescription + "']/td[6]/a");
		Thread.sleep(commons.longest);
	}

	public void editDraftsByTravelDescription(WebDriver driver, String travelDescription) throws InterruptedException {
		commons.clickElementByXpath(driver,
				".//*[@id='container']//table/tbody/tr[td/text()='" + travelDescription + "']/td[6]/a");
		Thread.sleep(commons.longest);
	}

	public void discardDraftsByTravelDescription(WebDriver driver, String travelDescription)
			throws InterruptedException {
		commons.clickElementByXpath(driver,
				".//*[@id='container']//table/tbody/tr[td/text()='" + travelDescription + "']/td[7]/a");
		commons.clickElementByXpath(driver, "//button[@ng-click='cancelDiscardDraft()']");
		commons.clickElementByXpath(driver,
				".//*[@id='container']//table/tbody/tr[td/text()='" + travelDescription + "']/td[7]/a");
		commons.clickElementById(driver, "btnDiscardDraft");
		Thread.sleep(commons.longest);
	}

	public void withdrawTravelRequest(WebDriver driver, String taskId) throws InterruptedException {
		myInboxActions.searchByReference(driver, taskId);
		commons.checkElementPresentByXpath(driver, "//tr[@ng-repeat='request in travelRequests.content']");
		commons.clickElementByXpath(driver, ".//*[@id='container']//table/tbody/tr/td[6]/a");
		Thread.sleep(commons.longest);
		commons.clickElementByXpath(driver, "//button[@ng-click='withdraw()']");
		commons.setValueToElementByXpath(driver, "//textarea[@ng-model='postData.comments']",
				"Reservation and pricing done");
		commons.clickElementByXpath(driver, "//button[@ng-click='actOnTask()']");
	}

	public void checkTravelDraft(WebDriver driver, Properties props, Logger logger, String fileName)
			throws InterruptedException {
		boolean toBeExecuted = false;
		String travelDescription = null;
		try {
			toBeExecuted = Boolean.valueOf(props.getProperty("travel.test.checkTravelDraft"));
		} catch (Exception e) {
			toBeExecuted = false;
		}
		if (toBeExecuted) {
			logger.info("DH Automation test case : TravelActions {}: checkTravelDraft : Starting the test case ",
					fileName);
			// Go to drafts page and then check the drafts assuming you have
			// already
			// clicked travel
			clickOnMyDrafts(driver);
			travelDescription = props.getProperty("travel.flight.travelDescription", travelDescription);
			checkDraftsByTravelDescription(driver, travelDescription);
			logger.info(
					"DH Automation test case : TravelActions {}: checkTravelDraft : This test case is successfully executed",
					fileName);
		} else {
			logger.info(
					"DH Automation test case : TravelActions {} : checkTravelDraft : This test case is not enabled hence it is being skipped",
					fileName);
		}
	}

	public void editTravelDraft(WebDriver driver, Properties props, Logger logger, String fileName)
			throws InterruptedException {
		boolean toBeExecuted = false;
		String travelDescription = null;
		try {
			toBeExecuted = Boolean.valueOf(props.getProperty("travel.test.editTravelDraft"));
		} catch (Exception e) {
			toBeExecuted = false;
		}
		if (toBeExecuted) {
			logger.info("DH Automation test case : TravelActions {}: editTravelDraft : Starting the test case ",
					fileName);
			// Go to drafts page and then check the drafts assuming you have
			// already
			// clicked travel
			clickOnMyDrafts(driver);
			travelDescription = props.getProperty("travel.flight.travelDescription", travelDescription);
			editDraftsByTravelDescription(driver, travelDescription);
			logger.info(
					"DH Automation test case : TravelActions {}: editTravelDraft : This test case is successfully executed",
					fileName);
		} else {
			logger.info(
					"DH Automation test case : TravelActions {}: editTravelDraft : This test case is not enabled hence it is being skipped",
					fileName);
		}
	}

	public void discardTravelDraft(WebDriver driver, Properties props, Logger logger, String fileName)
			throws InterruptedException {
		boolean toBeExecuted = false;
		String travelDescription = null;
		try {
			toBeExecuted = Boolean.valueOf(props.getProperty("travel.test.discardTravelDraft"));
		} catch (Exception e) {
			toBeExecuted = false;
		}
		if (toBeExecuted) {
			logger.info("DH Automation test case : TravelActions {}: discardTravelDraft : Starting the test case ",
					fileName);
			// Go to drafts page and then check the drafts assuming you have
			// already
			// clicked travel
			clickOnMyDrafts(driver);
			travelDescription = props.getProperty("travel.flight.travelDescription", travelDescription);
			discardDraftsByTravelDescription(driver, travelDescription);
			logger.info(
					"DH Automation test case : TravelActions {}: discardTravelDraft : This test case is successfully executed",
					fileName);
		} else {
			logger.info(
					"DH Automation test case : TravelActions {} : discardTravelDraft : This test case is not enabled hence it is being skipped",
					fileName);
		}
	}

	public String submitTravelRequest(WebDriver driver, Properties props, String defaultFilePath, Logger logger,
			String fileName) throws InterruptedException {
		// Assuming you are in travel request page and submit button is enabled
		boolean toBeExecuted = false;
		String travelReferenceNo = null;
		try {
			toBeExecuted = Boolean.valueOf(props.getProperty("travel.test.submitTravelRequest"));
		} catch (Exception e) {
			toBeExecuted = false;
		}
		if (toBeExecuted) {
			logger.info("DH Automation test case : TravelActions {}: submitTravelRequest : Starting the test case ",
					fileName);
			travelReferenceNo = submitTravelRequest(driver);
			props.setProperty("travel.flight.referenceNo", travelReferenceNo);
			commons.modifyProperties(defaultFilePath + "Travel\\travelRequest.properties", "travel.flight.referenceNo",
					travelReferenceNo);
			clickOnTravel(driver);
			logger.info(
					"DH Automation test case : TravelActions {}: submitTravelRequest : This test case is successfully executed",
					fileName);
		} else {
			logger.info(
					"DH Automation test case : TravelActions {}: submitTravelRequest : This test case is not enabled hence it is being skipped",
					fileName);
		}
		return travelReferenceNo;
	}

	public void showApprovals(WebDriver driver, Properties props, Logger logger, String fileName)
			throws InterruptedException {
		boolean toBeExecuted = false;
		try {
			toBeExecuted = Boolean.valueOf(props.getProperty("travel.test.showApprovals"));
		} catch (Exception e) {
			toBeExecuted = false;
		}
		if (toBeExecuted) {
			logger.info("DH Automation test case : TravelActions {}: showApprovals : Starting the test case ",
					fileName);
			// Assuming you are in travel request page and show approvals is
			// enabled
			showApprovers(driver);
			logger.info(
					"DH Automation test case : TravelActions {}: showApprovals : This test case is successfully executed",
					fileName);
		} else {
			logger.info(
					"DH Automation test case : TravelActions {}: showApprovals : This test case is not enabled hence it is being skipped",
					fileName);
		}
	}

	public void withdrawTravelRequest(WebDriver driver, Properties props, Logger logger, String fileName)
			throws InterruptedException {
		boolean toBeExecuted = false;
		String travelReferenceNo = null;
		try {
			toBeExecuted = Boolean.valueOf(props.getProperty("travel.test.withdrawTravelRequest"));
			travelReferenceNo = props.getProperty("travel.flight.referenceNo");
		} catch (Exception e) {
			toBeExecuted = false;
		}
		if (toBeExecuted) {
			logger.info("DH Automation test case : TravelActions {}: withdrawTravelRequest : Starting the test case ",
					fileName);
			// Assuming you are on the home page
			clickOnMyRequests(driver);
			withdrawTravelRequest(driver, travelReferenceNo);
			logger.info(
					"DH Automation test case : TravelActions {}: withdrawTravelRequest : This test case is successfully executed",
					fileName);
		} else {
			logger.info(
					"DH Automation test case : TravelActions {}: withdrawTravelRequest : This test case is not enabled hence it is being skipped",
					fileName);
		}
	}

	public void claimTask(WebDriver driver, Properties travelProps, Logger logger, String fileName)
			throws InterruptedException {
		boolean toBeExecuted = false;
		String travelRefNo = null;
		String travelDesk = null;
		try {
			toBeExecuted = Boolean.valueOf(travelProps.getProperty("travel.test.claimTask"));
		} catch (Exception e) {
			toBeExecuted = false;
		}
		if (toBeExecuted) {
			logger.info("DH Automation test case : TravelActions {}: claimTask : Starting the test case ", fileName);
			// login as the travel desk and claim the task
			travelDesk = travelProps.getProperty("travel.credentials.user.traveldesk");
			commons.login(driver, travelDesk);
			commons.clickOnMenuItem(driver, "traveltask");
			travelRefNo = travelProps.getProperty("travel.flight.referenceNo");
			Thread.sleep(commons.longest);
			myInboxActions.searchByReference(driver, travelRefNo);
			commons.checkElementPresentByXpath(driver, ".//*[@id='inboxTaskContainer']//table/tbody/tr/td[4]/a");
			commons.clickElementByXpath(driver, ".//*[@id='inboxTaskContainer']//table/tbody/tr/td[4]/a");
			Thread.sleep(commons.longest);
			commons.logout(driver);
			logger.info(
					"DH Automation test case : TravelActions {}: claimTask : This test case is successfully executed ",
					fileName);
		} else {
			logger.info(
					"DH Automation test case : TravelActions {} : claimTask : This test case is not enabled hence it is being skipped",
					fileName);
		}
	}

	public void revokeTask(WebDriver driver, Properties travelProps, Logger logger, String fileName)
			throws InterruptedException {
		boolean toBeExecuted = false;
		String travelRefNo = null;
		String travelDesk = null;
		try {
			toBeExecuted = Boolean.valueOf(travelProps.getProperty("travel.test.revokeTask"));
		} catch (Exception e) {
			toBeExecuted = false;
		}
		if (toBeExecuted) {
			logger.info("DH Automation test case : Travel {}: revokeTask : Starting the test case ", fileName);
			// login as the travel desk and claim the task
			travelDesk = travelProps.getProperty("travel.credentials.user.traveldesk");
			commons.login(driver, travelDesk);
			commons.clickOnMenuItem(driver, "personaltask");
			travelRefNo = travelProps.getProperty("travel.flight.referenceNo");
			Thread.sleep(commons.longest);
			myInboxActions.searchByReference(driver, travelRefNo);
			commons.checkElementPresentByXpath(driver, ".//*[@id='inboxTaskContainer']//table/tbody/tr/td[4]/a");
			commons.clickElementByXpath(driver, ".//*[@id='inboxTaskContainer']//table/tbody/tr/td[4]/a");
			Thread.sleep(commons.normal);
			myInboxActions.revokeAction(driver);
			Thread.sleep(commons.longest);
			commons.logout(driver);
			logger.info(
					"DH Automation test case : TravelActions {}: revokeTask : This test case is successfully executed ",
					fileName);
		} else {
			logger.info(
					"DH Automation test case : TravelActions {} : revokeTask : This test case is not enabled hence it is being skipped",
					fileName);
		}
	}

	public void approvalFlow(WebDriver driver, Properties travelProps, Logger logger, String fileName)
			throws InterruptedException {
		boolean toBeExecuted = false;
		String travelRefNo = null;
		try {
			toBeExecuted = Boolean.valueOf(travelProps.getProperty("travel.test.approvalFlow"));
		} catch (Exception e) {
			toBeExecuted = false;
		}
		if (toBeExecuted) {
			String approvalString = travelProps.getProperty("travel.test.approvalMatrix");
			travelRefNo = travelProps.getProperty("travel.flight.referenceNo");
			String approverOptions[] = approvalString.split(",");
			String options[] = null;
			String currentApprover = null;
			boolean isPersonal = false;
			for (String option : approverOptions) {
				// Approving the task option will be of the format
				// user$$$option$$$toModifyOptions$$$toBeExecuted
				options = option.split("\\$\\$\\$");
				if (Boolean.valueOf(options[3])) {
					logger.info(
							"DH Automation test case : TravelActions {}: approvalFlow : Starting the test case for the user {}",
							fileName, options[0]);
					isPersonal = (options[0].equalsIgnoreCase("traveldesk")) ? false : true;
					currentApprover = travelProps.getProperty("travel.credentials.user." + options[0]);
					myInboxActions.inboxAction(driver, travelRefNo, options[1], commons.longest, currentApprover,
							Boolean.valueOf(options[2]), isPersonal);
					logger.info(
							"DH Automation test case : TravelActions {}: approvalFlow : This test case is successfully executed for the user {}",
							fileName, options[0]);
				} else {
					logger.info(
							"DH Automation test case : TravelActions {}: approvalFlow : This test case is not enabled for this particular user {}, hence it is being skipped",
							fileName, options[0]);
				}
			}
		} else {
			logger.info(
					"DH Automation test case : TravelActions {} : approvalFlow : This test case is not enabled hence it is being skipped",
					fileName);
		}
	}

	public void deletingTravelDrafts(WebDriver driver, Properties travelProps, Logger logger, String fileName)
			throws InterruptedException {
		/*
		 * To check this functionality please maintain there are at-least one
		 * drafts
		 */
		boolean toBeExecuted = false;
		int totalDrafts = 0;
		int afterDrafts = 0;
		try {
			toBeExecuted = Boolean.valueOf(travelProps.getProperty("travel.test.deleteTravelDrafts"));
		} catch (Exception e) {
			toBeExecuted = false;
		}
		if (toBeExecuted) {
			logger.info("DH Automation test case : TravelActions {} : deletingTravelDrafts : Starting the test case",
					fileName);
			clickOnMyDrafts(driver);
			Thread.sleep(commons.normal);
			totalDrafts = Integer.parseInt(
					commons.getValueByXpath(driver, ".//*[@id='travel']/ul/li/a[contains(text(),'Drafts')]/span"));
			deleteAll(driver, "clear");
			Thread.sleep(commons.shortest);
			deleteAll(driver, "deleteallcancel");
			Thread.sleep(commons.normal);
			deleteAll(driver, "deleteallconfirm");
			Thread.sleep(commons.normal);
			afterDrafts = Integer.parseInt(
					commons.getValueByXpath(driver, ".//*[@id='travel']/ul/li/a[contains(text(),'Drafts')]/span"));
			if (totalDrafts == afterDrafts + 1)
				logger.info(
						"DH Automation test case : TravelActions {} : deletingTravelDrafts : This test case is successful",
						fileName);
			else
				logger.info(
						"DH Automation test case : TravelActions {} : deletingTravelDrafts : This test case is not successful",
						fileName);
		} else {
			logger.info(
					"DH Automation test case : TravelActions {} : deletingTravelDrafts : This test case is not enabled hence it is being skipped",
					fileName);
		}
	}

	public void deleteAll(WebDriver driver, String option) throws InterruptedException {
		commons.clickElementByXpath(driver, ".//*[@id='container']//table/tbody/tr[1]/td[1]/span");
		if ("clear".equalsIgnoreCase(option))
			commons.clickElementByXpath(driver, ".//*[@id='container']//button[contains(text(),'Clear')]");
		if ("deleteallcancel".equalsIgnoreCase(option)) {
			commons.clickElementByXpath(driver, ".//*[@id='container']//button[contains(text(),'Delete All')]");
			Thread.sleep(commons.normal);
			commons.clickElementByXpath(driver,
					".//*[@id='discardAllDraftModal']//button[@ng-click='cancelDiscardDraft()']");
		}
		if ("deleteallconfirm".equalsIgnoreCase(option)) {
			commons.clickElementByXpath(driver, ".//*[@id='container']//button[contains(text(),'Delete All')]");
			Thread.sleep(commons.normal);
			commons.clickElementByXpath(driver,
					".//*[@id='discardAllDraftModal']//button[@ng-click='removeAllSelectedDrafts()']");
		}
	}
}
