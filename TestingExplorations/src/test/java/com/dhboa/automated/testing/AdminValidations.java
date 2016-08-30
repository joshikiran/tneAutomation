package com.dhboa.automated.testing;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AdminValidations {
	public WebDriver driver;

	Commons commons = new Commons();

	@BeforeTest
	public void beforeTest() {
		driver = commons.getWebDriver("firefox");
		commons.maximizeWindow(driver);
		commons.loginAsAdmin(driver);
	}

	@Test
	public void roleValidation() throws InterruptedException {
		// check if the Manage element is present
		commons.checkMenuItems(driver, true, "headingTwo");
	}

	/*@AfterTest
	public void afterTest() {
		commons.logout(driver);
		driver.quit();
	}*/
}
