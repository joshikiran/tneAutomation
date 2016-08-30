package com.dhboa.automated.testing;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Commons {
	public enum browsers {
		FIREFOX("firefox"), CHROME("chrome"), IE("ie");

		private String browserName;

		browsers(String name) {
			this.browserName = name;
		}

		public String getBrowserName() {
			return browserName;
		}
	}

	public WebDriver getWebDriver(String browserName) {
		if (browserName.equalsIgnoreCase(browsers.FIREFOX.getBrowserName())) {
			return new FirefoxDriver();
		} else
			return null;
	}

	public void maximizeWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}

	public void loginAsAdmin(WebDriver driver) {
		login(driver, "http://219.65.70.150/trex/#/login?redirect=%2F", "username", "password", "btnLogIn",
				"admin@default.com", "pass");
	}

	public void loginAsConsultant(WebDriver driver) {
		login(driver, "http://219.65.70.150/trex/#/login?redirect=%2F", "username", "password", "btnLogIn",
				"cons@darkhorseboa.com", "cons");
	}
	public void loginAsTravel(WebDriver driver) {
		login(driver, "http://219.65.70.150/trex/#/login?redirect=%2F", "username", "password", "btnLogIn",
				"travel@darkhorseboa.com", "travel");
	}

	public void login(WebDriver driver, String applicationUrl, String userNameElId, String passwordElId,
			String loginElId, String userName, String password) {
		driver.get(applicationUrl);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		setValueToElementById(driver, userNameElId, userName);
		setValueToElementById(driver, passwordElId, password);
		clickElementById(driver, loginElId);
	}

	public void logout(WebDriver driver) {
		// This is for logging out
		clickElementByXpath(driver, "//a[contains(@class,'dropdown-toggle') and @data-toggle='dropdown']");
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@ng-click='logout()']")));
		clickElementByXpath(driver, "//a[@ng-click='logout()']");
	}

	public void setValueToElementById(WebDriver driver, String id, String value) {
		driver.findElement(By.id(id)).sendKeys(value);
	}

	public void setValueToElementByXpath(WebDriver driver, String xpath, String value) {
		driver.findElement(By.xpath(xpath)).sendKeys(value);
	}

	public void clickElementById(WebDriver driver, String id) {
		driver.findElement(By.id(id)).click();
	}

	public void clickElementByXpath(WebDriver driver, String xpath) {
		driver.findElement(By.xpath(xpath)).click();
	}

	public void isElementPresentById(WebDriver driver, String id) {
		boolean elementPresent = true;
		try {
			elementPresent = (driver.findElement(By.id(id)) == null && driver.findElement(By.id(id)).isDisplayed())
					? false : true;
		} catch (Exception e) {
			elementPresent = false;
		}
		Assert.assertTrue(elementPresent, "Unable to find the element with id " + id);
	}

	public void checkMenuItems(WebDriver driver, boolean checkCondition, String... ids) {
		boolean elementPresent = true;
		String elementId = "";
		for (String id : ids) {
			try {
				elementId = id;
				elementPresent = (driver.findElement(By.id(id)) == null && driver.findElement(By.id(id)).isDisplayed())
						? false : true;
				elementPresent = !driver.findElement(By.xpath("//..[@id='" + id + "']/..")).getAttribute("class")
						.contains("ng-hide");
			} catch (Exception e) {
				elementPresent = false;
				break;
			}
		}
		if (checkCondition)
			Assert.assertTrue(elementPresent, "Unable to find the element with id " + elementId);
		else
			Assert.assertFalse(elementPresent, "Element found with id " + elementId);
	}

	public void isElementPresentByXpath(WebDriver driver, String xpath) {
		boolean elementPresent = true;
		try {
			elementPresent = (driver.findElement(By.xpath(xpath)) == null
					&& driver.findElement(By.xpath(xpath)).isDisplayed()) ? false : true;
			elementPresent = !driver.findElement(By.xpath(xpath)).getAttribute("class").contains("ng-hide");
		} catch (Exception e) {
			elementPresent = false;
		}
		Assert.assertTrue(elementPresent, "Unable to find the element with xpath " + xpath);
	}
}
