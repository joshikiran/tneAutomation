package com.dhboa.automated.testing;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class UserValidations {

	public WebDriver driver;

	Commons commons = new Commons();

	@BeforeTest
	public void beforeTest() throws InterruptedException {
		driver = commons.getWebDriver("firefox");
		commons.maximizeWindow(driver);
		commons.loginAsConsultant(driver);
	}

	@Test
	public void roleValidation() throws InterruptedException {
		// check if the Manage element is present
		commons.checkMenuItems(driver, false, "headingTwo");
	}

	@AfterTest
	public void afterTest() throws InterruptedException {
		commons.logout(driver);
		driver.quit();
	}
}
