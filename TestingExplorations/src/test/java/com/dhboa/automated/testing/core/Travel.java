package com.dhboa.automated.testing.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.dhboa.automated.testing.actions.CommonActions;
import com.dhboa.automated.testing.actions.TravelActions;
import com.dhboa.automated.testing.travel.Flight;

public class Travel {

	WebDriver driver = null;
	CommonActions commons = new CommonActions();
	TravelActions travelActions = new TravelActions(commons);
	Flight flight = new Flight(travelActions, commons);
	String defaultFilePath = "D:\\Office\\Projects\\Selenium\\TnE\\";
	HashMap<String, Properties> travelPropsList = new HashMap<String, Properties>();
	private Logger logger = LoggerFactory.getLogger(getClass());

	@BeforeTest
	public void onBeforeTesting() throws FileNotFoundException, IOException {
		driver = commons.getWebDriver("chrome");
		commons.maximizeWindow(driver);

		File folder = new File(defaultFilePath + "Travel\\");
		File[] listOfFiles = folder.listFiles();
		Properties travelProps = null;
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				travelProps = new Properties();
				travelProps.load(new FileInputStream(defaultFilePath + "Travel\\" + listOfFiles[i].getName()));
				travelPropsList.put(listOfFiles[i].getName(), travelProps);
				logger.info("DH Automation test case : Travel : OnBeforeTesting : Loading the property file {}",
						listOfFiles[i].getName());
			}
		}
	}

	@Test(description = "Complete Flow of travel")
	public void travelFlow() throws InterruptedException {
		Properties travelProps = null;
		for (String fileName : travelPropsList.keySet()) {
			try {
				travelProps = travelPropsList.get(fileName);
				commons.login(driver, travelProps.getProperty("travel.credentials.initiator"));
				travelActions.clickOnTravel(driver);
				flight.createTravelDraft(driver, travelProps, defaultFilePath, logger, fileName);
				// This is because it will take time for saving into database
				Thread.sleep(commons.normal);
				flight.checkTravelDraft(driver, travelProps, logger, fileName);
				flight.editTravelDraft(driver, travelProps, logger, fileName);
				flight.discardTravelDraft(driver, travelProps, logger, fileName);
				flight.showApprovals(driver, travelProps, logger, fileName);
				flight.submitTravelRequest(driver, travelProps, defaultFilePath, logger, fileName);
				Thread.sleep(commons.normal);
				travelActions.clickOnTravel(driver);
				commons.logout(driver);
				flight.approvalFlow(driver, travelProps, logger, fileName);
				logger.info("DH Automation test case : Travel {}: travelFlow : Test Case Successful", fileName);
			} catch (Exception e) {
				logger.info("DH Automation test case : Travel {}: travelFlow : Test Case failed", fileName);
			}
		}
	}

	@AfterTest
	public void onAfterTest() throws InterruptedException {
		logger.info("DH Automation test case : Travel {}: onAfterTest : Browser quit action");
		driver.quit();
	}
}
