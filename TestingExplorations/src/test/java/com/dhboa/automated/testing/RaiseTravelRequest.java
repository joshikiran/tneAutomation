package com.dhboa.automated.testing;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class RaiseTravelRequest {
	
	WebDriver driver;
	
	Commons commons = new Commons();
	
	@BeforeTest
	public void beforeRaisingRequest(){
		driver = commons.getWebDriver("firefox");
		commons.maximizeWindow(driver);
		commons.loginAsConsultant(driver);
	}
	
	@Test
	public void raiseTravelRequest(){
		
	}
	
	@AfterTest
	public void afterRaisingRequest(){
		commons.logout(driver);
		driver.quit();
	}
}
