package com.dhboa.automated.testing.actions;

import java.util.Date;
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
	public String fillFlightDetails(WebDriver driver, Properties props, Logger logger) throws InterruptedException {
		String flightTripType = null;
		String flightTravelType = null;
		String flightTravelClass = null;
		String flightFromLocation = null;
		String flightToLocation = null;
		String flightFromDate = null;
		String flightToDate = null;
		String otherComments = null;
		String travelReason = null;
		String travelDescription = null;
		boolean isCallingCardRequired = false;
		boolean isInsuranceRequired = false;
		boolean isVisaRequired = false;
		try {
			flightTripType = props.getProperty("travel.flight.flightTripType");
			flightTravelType = props.getProperty("travel.flight.flightTravelType");
			flightTravelClass = props.getProperty("travel.flight.flightTravelClass");
			flightFromLocation = props.getProperty("travel.flight.flightFromLocation");
			flightToLocation = props.getProperty("travel.flight.flightToLocation");
			flightFromDate = props.getProperty("travel.flight.flightFromDate");
			flightToDate = props.getProperty("travel.flight.flightToDate");
			otherComments = props.getProperty("travel.flight.otherComments");
			travelReason = props.getProperty("travel.flight.travelReason");
			isCallingCardRequired = Boolean.valueOf(props.getProperty("travel.flight.isCallingCardRequired"));
			isInsuranceRequired = Boolean.valueOf(props.getProperty("travel.flight.isInsuranceRequired"));
			isVisaRequired = Boolean.valueOf(props.getProperty("travel.flight.isVisaRequired"));
			travelDescription = String.valueOf(new Date().getTime());
		} catch (Exception e) {
			logger.error("Exception while reading some of the properties", e);
		}
		commons.waitUntilElementVisibilityById(driver, "myId2");
		commons.clickElementById(driver, "myId2");
		commons.waitUntilElementVisibilityByXpath(driver, "//a[@data-target='#flight']");
		commons.clickElementByXpath(driver, "//a[@data-target='#flight']");
		Thread.sleep(commons.shortest);
		commons.clickElementByXpath(driver, ".//*[@id='flight']//button[text()='Close']");
		
		//Click on flight again
		commons.waitUntilElementVisibilityById(driver, "myId2");
		commons.clickElementById(driver, "myId2");
		commons.waitUntilElementVisibilityByXpath(driver, "//a[@data-target='#flight']");
		commons.clickElementByXpath(driver, "//a[@data-target='#flight']");
		Thread.sleep(commons.shortest);
		
		if (flightTripType != null && !"".equalsIgnoreCase(flightTripType))
			commons.selectByVisibleTextForEleXpath(driver, "//select[@name='flightTripType']", flightTripType);
		if (flightTravelType != null && !"".equalsIgnoreCase(flightTravelType))
			commons.selectByVisibleTextForEleXpath(driver, "//select[@name='flightTravelType']", flightTravelType);
		if (flightTravelClass != null && !"".equalsIgnoreCase(flightTravelClass))
			commons.selectByVisibleTextForEleXpath(driver, "//select[@name='flightTravelClass']", flightTravelClass);
		if (isCallingCardRequired)
			commons.clickElementById(driver, "callingCardRequired");
		if (isInsuranceRequired)
			commons.clickElementById(driver, "insuranceRequired");
		if (isVisaRequired)
			commons.clickElementById(driver, "visaRequired");
		if (flightFromLocation != null && !"".equalsIgnoreCase(flightFromLocation))
			commons.setValueToElementById(driver, "flightFromLocation_value", flightFromLocation);
		if (flightToLocation != null && !"".equalsIgnoreCase(flightToLocation))
			commons.setValueToElementById(driver, "flightToLocation_value", flightToLocation);
		if (flightFromDate != null && !"".equalsIgnoreCase(flightFromDate))
			commons.setValueToElementByXpath(driver, "//input[@ng-model='formattedDates.flightFromDate']",
					flightFromDate);
		if (flightToDate != null && !"".equalsIgnoreCase(flightToDate))
			commons.setValueToElementByXpath(driver, "//input[@ng-model='formattedDates.flightToDate']", flightToDate);
		if (otherComments != null && !"".equalsIgnoreCase(otherComments))
			commons.setValueToElementById(driver, "otherComments", otherComments);
		commons.clickElementByXpath(driver, "//button[@class='btn btn-primary']");
		if (travelReason != null && !"".equalsIgnoreCase(travelReason))
			commons.selectByVisibleTextForEleXpath(driver, "//select[@name='travelReason']", travelReason);
		if (travelDescription != null && !"".equalsIgnoreCase(travelDescription))
			commons.setValueToElementByXpath(driver, "//input[@name='tripDescription']", travelDescription);
		return travelDescription;
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
}
