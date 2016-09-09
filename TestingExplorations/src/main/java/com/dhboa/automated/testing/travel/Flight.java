package com.dhboa.automated.testing.travel;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;

import com.dhboa.automated.testing.actions.CommonActions;
import com.dhboa.automated.testing.actions.MyInboxActions;
import com.dhboa.automated.testing.actions.TravelActions;

public class Flight {

	CommonActions commonActions = null;
	TravelActions travelActions = null;
	MyInboxActions myInboxActions = null;

	public Flight(CommonActions com) {
		commonActions = new CommonActions();
		travelActions = new TravelActions(com);
		myInboxActions = new MyInboxActions(com);
	}

	public Flight(TravelActions travelActions, CommonActions com) {
		commonActions = com;
		this.travelActions = travelActions;
		myInboxActions = new MyInboxActions(com);
	}

	public Flight(CommonActions commonActions, CommonActions com) {

	}

	public String createTravelDraft(WebDriver driver, Properties props, String defaultFilePath, Logger logger,
			String fileName) throws InterruptedException {
		boolean toBeExecuted = false;
		try {
			toBeExecuted = Boolean.valueOf(props.getProperty("travel.test.flight.createTravelDraft"));
		} catch (Exception e) {
			toBeExecuted = false;
		}
		String description = null;
		if (toBeExecuted) {
			logger.info("DH Automation test case : Travel {}: createTravelDraft : Starting the test case ", fileName);
			// go to new travel request and click on it. Assuming you have
			// already
			// clicked travel
			travelActions.clickOnNewTravelRequest(driver);
			description = travelActions.fillFlightDetails(driver, props, logger);
			props.setProperty("travel.flight.travelDescription", description);
			commonActions.modifyProperties(defaultFilePath + "Travel\\travelRequest.properties",
					"travel.flight.travelDescription", description);
			logger.info(
					"DH Automation test case : Travel {}: createTravelDraft : This test case is successfully executed",
					fileName);
		} else {
			logger.info(
					"DH Automation test case : Travel {}: createTravelDraft : This test case is not enabled hence it is being skipped",
					fileName);
		}
		return description;
	}

	public void checkTravelDraft(WebDriver driver, Properties props, Logger logger, String fileName)
			throws InterruptedException {
		boolean toBeExecuted = false;
		String travelDescription = null;
		try {
			toBeExecuted = Boolean.valueOf(props.getProperty("travel.test.flight.checkTravelDraft"));
		} catch (Exception e) {
			toBeExecuted = false;
		}
		if (toBeExecuted) {
			logger.info("DH Automation test case : Travel {}: checkTravelDraft : Starting the test case ", fileName);
			// Go to drafts page and then check the drafts assuming you have
			// already
			// clicked travel
			travelActions.clickOnMyDrafts(driver);
			travelDescription = props.getProperty("travel.flight.travelDescription", travelDescription);
			travelActions.checkDraftsByTravelDescription(driver, travelDescription);
			logger.info(
					"DH Automation test case : Travel {}: checkTravelDraft : This test case is successfully executed",
					fileName);
		} else {
			logger.info(
					"DH Automation test case : Travel {} : checkTravelDraft : This test case is not enabled hence it is being skipped",
					fileName);
		}
	}

	public void editTravelDraft(WebDriver driver, Properties props, Logger logger, String fileName)
			throws InterruptedException {
		boolean toBeExecuted = false;
		String travelDescription = null;
		try {
			toBeExecuted = Boolean.valueOf(props.getProperty("travel.test.flight.editTravelDraft"));
		} catch (Exception e) {
			toBeExecuted = false;
		}
		if (toBeExecuted) {
			logger.info("DH Automation test case : Travel {}: editTravelDraft : Starting the test case ", fileName);
			// Go to drafts page and then check the drafts assuming you have
			// already
			// clicked travel
			travelActions.clickOnMyDrafts(driver);
			travelDescription = props.getProperty("travel.flight.travelDescription", travelDescription);
			travelActions.editDraftsByTravelDescription(driver, travelDescription);
			logger.info(
					"DH Automation test case : Travel {}: editTravelDraft : This test case is successfully executed",
					fileName);
		} else {
			logger.info(
					"DH Automation test case : Travel {}: editTravelDraft : This test case is not enabled hence it is being skipped",
					fileName);
		}
	}

	public void discardTravelDraft(WebDriver driver, Properties props, Logger logger, String fileName)
			throws InterruptedException {
		boolean toBeExecuted = false;
		String travelDescription = null;
		try {
			toBeExecuted = Boolean.valueOf(props.getProperty("travel.test.flight.discardTravelDraft"));
		} catch (Exception e) {
			toBeExecuted = false;
		}
		if (toBeExecuted) {
			logger.info("DH Automation test case : Travel {}: discardTravelDraft : Starting the test case ", fileName);
			// Go to drafts page and then check the drafts assuming you have
			// already
			// clicked travel
			travelActions.clickOnMyDrafts(driver);
			travelDescription = props.getProperty("travel.flight.travelDescription", travelDescription);
			travelActions.discardDraftsByTravelDescription(driver, travelDescription);
			logger.info(
					"DH Automation test case : Travel {}: discardTravelDraft : This test case is successfully executed",
					fileName);
		} else {
			logger.info(
					"DH Automation test case : Travel {} : discardTravelDraft : This test case is not enabled hence it is being skipped",
					fileName);
		}
	}

	public String submitTravelRequest(WebDriver driver, Properties props, String defaultFilePath, Logger logger,
			String fileName) throws InterruptedException {
		// Assuming you are in travel request page and submit button is enabled
		boolean toBeExecuted = false;
		String travelReferenceNo = null;
		try {
			toBeExecuted = Boolean.valueOf(props.getProperty("travel.test.flight.submitTravelRequest"));
		} catch (Exception e) {
			toBeExecuted = false;
		}
		if (toBeExecuted) {
			logger.info("DH Automation test case : Travel {}: submitTravelRequest : Starting the test case ", fileName);
			travelReferenceNo = travelActions.submitTravelRequest(driver);
			props.setProperty("travel.flight.referenceNo", travelReferenceNo);
			commonActions.modifyProperties(defaultFilePath + "Travel\\travelRequest.properties",
					"travel.flight.referenceNo", travelReferenceNo);
			travelActions.clickOnTravel(driver);
			logger.info(
					"DH Automation test case : Travel {}: submitTravelRequest : This test case is successfully executed",
					fileName);
		} else {
			logger.info(
					"DH Automation test case : Travel {}: submitTravelRequest : This test case is not enabled hence it is being skipped",
					fileName);
		}
		return travelReferenceNo;
	}

	public void showApprovals(WebDriver driver, Properties props, Logger logger, String fileName)
			throws InterruptedException {
		boolean toBeExecuted = false;
		try {
			toBeExecuted = Boolean.valueOf(props.getProperty("travel.test.flight.showApprovals"));
		} catch (Exception e) {
			toBeExecuted = false;
		}
		if (toBeExecuted) {
			logger.info("DH Automation test case : Travel {}: showApprovals : Starting the test case ", fileName);
			// Assuming you are in travel request page and show approvals is
			// enabled
			travelActions.showApprovers(driver);
			logger.info("DH Automation test case : Travel {}: showApprovals : This test case is successfully executed",
					fileName);
		} else {
			logger.info(
					"DH Automation test case : Travel {}: showApprovals : This test case is not enabled hence it is being skipped",
					fileName);
		}
	}

	public void withdrawTravelRequest(WebDriver driver, Properties props, Logger logger, String fileName)
			throws InterruptedException {
		boolean toBeExecuted = false;
		String travelReferenceNo = null;
		try {
			toBeExecuted = Boolean.valueOf(props.getProperty("travel.test.flight.withdrawTravelRequest"));
			travelReferenceNo = props.getProperty("travel.flight.referenceNo");
		} catch (Exception e) {
			toBeExecuted = false;
		}
		if (toBeExecuted) {
			logger.info("DH Automation test case : Travel {}: withdrawTravelRequest : Starting the test case ",
					fileName);
			// Assuming you are on the home page
			travelActions.clickOnMyRequests(driver);
			travelActions.withdrawTravelRequest(driver, travelReferenceNo);
			logger.info(
					"DH Automation test case : Travel {}: withdrawTravelRequest : This test case is successfully executed",
					fileName);
		} else {
			logger.info(
					"DH Automation test case : Travel {}: withdrawTravelRequest : This test case is not enabled hence it is being skipped",
					fileName);
		}
	}

	public void approvalFlow(WebDriver driver, Properties travelProps, Logger logger, String fileName)
			throws InterruptedException {
		boolean toBeExecuted = false;
		String travelRefNo = null;
		try {
			toBeExecuted = Boolean.valueOf(travelProps.getProperty("travel.test.flight.approvalFlow"));
		} catch (Exception e) {
			toBeExecuted = false;
		}
		if (toBeExecuted) {
			String approvalString = travelProps.getProperty("travel.test.flight.approvalMatrix");
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
							"DH Automation test case : Travel {}: approvalFlow : Starting the test case for the user {}",
							fileName, options[0]);
					isPersonal = (options[0].equalsIgnoreCase("traveldesk")) ? false : true;
					currentApprover = travelProps.getProperty("travel.credentials.user." + options[0]);
					myInboxActions.inboxAction(driver, travelRefNo, options[1], commonActions.longest, currentApprover,
							Boolean.valueOf(options[2]), isPersonal);
					logger.info(
							"DH Automation test case : Travel {}: approvalFlow : This test case is successfully executed for the user {}",
							fileName, options[0]);
				} else {
					logger.info(
							"DH Automation test case : Travel {}: approvalFlow : This test case is not enabled for this particular user {}, hence it is being skipped",
							fileName, options[0]);
				}
			}
		} else {
			logger.info(
					"DH Automation test case : Travel {} : approvalFlow : This test case is not enabled hence it is being skipped",
					fileName);
		}
	}

	public void claimTask(WebDriver driver, Properties travelProps, Logger logger, String fileName)
			throws InterruptedException {
		boolean toBeExecuted = false;
		String travelRefNo = null;
		String travelDesk = null;
		try {
			toBeExecuted = Boolean.valueOf(travelProps.getProperty("travel.test.flight.claimTask"));
		} catch (Exception e) {
			toBeExecuted = false;
		}
		if (toBeExecuted) {
			logger.info("DH Automation test case : Travel {}: claimTask : Starting the test case ", fileName);
			// login as the travel desk and claim the task
			travelDesk = travelProps.getProperty("travel.credentials.user.traveldesk");
			commonActions.login(driver, travelDesk);
			commonActions.clickOnMenuItem(driver, "traveltask");
			travelRefNo = travelProps.getProperty("travel.flight.referenceNo");
			Thread.sleep(commonActions.longest);
			myInboxActions.searchByReference(driver, travelRefNo);
			commonActions.checkElementPresentByXpath(driver, ".//*[@id='inboxTaskContainer']//table/tbody/tr/td[4]/a");
			commonActions.clickElementByXpath(driver, ".//*[@id='inboxTaskContainer']//table/tbody/tr/td[4]/a");
			Thread.sleep(commonActions.longest);
			commonActions.logout(driver);			
			logger.info("DH Automation test case : Travel {}: claimTask : This test case is successfully executed ",
					fileName);
		} else {
			logger.info(
					"DH Automation test case : Travel {} : claimTask : This test case is not enabled hence it is being skipped",
					fileName);
		}
	}

	public void revokeTask(WebDriver driver, Properties travelProps, Logger logger, String fileName)
			throws InterruptedException {
		boolean toBeExecuted = false;
		String travelRefNo = null;
		String travelDesk = null;
		try {
			toBeExecuted = Boolean.valueOf(travelProps.getProperty("travel.test.flight.revokeTask"));
		} catch (Exception e) {
			toBeExecuted = false;
		}
		if (toBeExecuted) {
			logger.info("DH Automation test case : Travel {}: revokeTask : Starting the test case ", fileName);
			// login as the travel desk and claim the task
			travelDesk = travelProps.getProperty("travel.credentials.user.traveldesk");
			commonActions.login(driver, travelDesk);
			commonActions.clickOnMenuItem(driver, "personaltask");
			travelRefNo = travelProps.getProperty("travel.flight.referenceNo");
			Thread.sleep(commonActions.longest);
			myInboxActions.searchByReference(driver, travelRefNo);
			commonActions.checkElementPresentByXpath(driver, ".//*[@id='inboxTaskContainer']//table/tbody/tr/td[4]/a");
			commonActions.clickElementByXpath(driver, ".//*[@id='inboxTaskContainer']//table/tbody/tr/td[4]/a");
			Thread.sleep(commonActions.normal);
			myInboxActions.revokeAction(driver);
			Thread.sleep(commonActions.longest);
			commonActions.logout(driver);
			logger.info("DH Automation test case : Travel {}: revokeTask : This test case is successfully executed ",
					fileName);
		} else {
			logger.info(
					"DH Automation test case : Travel {} : revokeTask : This test case is not enabled hence it is being skipped",
					fileName);
		}
	}
}
