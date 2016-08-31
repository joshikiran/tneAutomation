package com.dhboa.automated.testing;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ApproveRequest {
	WebDriver driver;

	Commons commons = new Commons();
	ApproverUtilities approver = new ApproverUtilities(commons);
	
	@BeforeTest
	public void beforeRaisingRequest() throws InterruptedException{
		driver = commons.getWebDriver("chrome");
		commons.maximizeWindow(driver);
		commons.loginAsTravel(driver);
	}
	
	@Test
	public void approveTask() throws InterruptedException{
		String taskId = "TR/CON/2016/02/05/00107";
		approver.openInboxTask(driver, taskId, false);
		approver.modifyTravelOptions(driver);
		approver.reservationAction(driver);
	}
	
	@AfterTest
	public void afterRaisingRequest() throws InterruptedException{
		commons.logout(driver);
		driver.quit();
	}
}
