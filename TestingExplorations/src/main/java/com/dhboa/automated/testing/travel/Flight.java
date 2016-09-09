package com.dhboa.automated.testing.travel;

import java.util.Date;
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

	public String createFlightDraft(WebDriver driver, Properties props, String defaultFilePath, Logger logger,
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
			description = fillFlightDetails(driver, props, logger);
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
		commonActions.waitUntilElementVisibilityById(driver, "myId2");
		commonActions.clickElementById(driver, "myId2");
		commonActions.waitUntilElementVisibilityByXpath(driver, "//a[@data-target='#flight']");
		commonActions.clickElementByXpath(driver, "//a[@data-target='#flight']");
		Thread.sleep(commonActions.shortest);
		commonActions.clickElementByXpath(driver, ".//*[@id='flight']//button[text()='Close']");

		// Click on flight again
		commonActions.waitUntilElementVisibilityById(driver, "myId2");
		commonActions.clickElementById(driver, "myId2");
		commonActions.waitUntilElementVisibilityByXpath(driver, "//a[@data-target='#flight']");
		commonActions.clickElementByXpath(driver, "//a[@data-target='#flight']");
		Thread.sleep(commonActions.shortest);

		if (flightTripType != null && !"".equalsIgnoreCase(flightTripType))
			commonActions.selectByVisibleTextForEleXpath(driver, "//select[@name='flightTripType']", flightTripType);
		if (flightTravelType != null && !"".equalsIgnoreCase(flightTravelType))
			commonActions.selectByVisibleTextForEleXpath(driver, "//select[@name='flightTravelType']", flightTravelType);
		if (flightTravelClass != null && !"".equalsIgnoreCase(flightTravelClass))
			commonActions.selectByVisibleTextForEleXpath(driver, "//select[@name='flightTravelClass']", flightTravelClass);
		if (isCallingCardRequired)
			commonActions.clickElementById(driver, "callingCardRequired");
		if (isInsuranceRequired)
			commonActions.clickElementById(driver, "insuranceRequired");
		if (isVisaRequired)
			commonActions.clickElementById(driver, "visaRequired");
		if (flightFromLocation != null && !"".equalsIgnoreCase(flightFromLocation))
			commonActions.setValueToElementById(driver, "flightFromLocation_value", flightFromLocation);
		if (flightToLocation != null && !"".equalsIgnoreCase(flightToLocation))
			commonActions.setValueToElementById(driver, "flightToLocation_value", flightToLocation);
		if (flightToDate != null && !"".equalsIgnoreCase(flightToDate))
			commonActions.setValueToElementByXpath(driver, "//input[@ng-model='formattedDates.flightToDate']", flightToDate);
		if (flightFromDate != null && !"".equalsIgnoreCase(flightFromDate))
			commonActions.setValueToElementByXpath(driver, "//input[@ng-model='formattedDates.flightFromDate']",
					flightFromDate);
		if (otherComments != null && !"".equalsIgnoreCase(otherComments))
			commonActions.setValueToElementById(driver, "otherComments", otherComments);
		commonActions.clickElementByXpath(driver, "//button[@class='btn btn-primary']");
		if (travelReason != null && !"".equalsIgnoreCase(travelReason))
			commonActions.selectByVisibleTextForEleXpath(driver, "//select[@name='travelReason']", travelReason);
		if (travelDescription != null && !"".equalsIgnoreCase(travelDescription))
			commonActions.setValueToElementByXpath(driver, "//input[@name='tripDescription']", travelDescription);
		return travelDescription;
	}
}
