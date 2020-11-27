package com.selenium.multiplebrowser;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class MultipleBrowser {

	public WebDriver driver;

	//To run test in multiple browser

	@BeforeTest
	@Parameters("browser")

	public void browser(String browser){

		//Checking for parameters whether its chrome or firefox

		try {

			if(browser.equalsIgnoreCase("Firefox")){

				//Creating a firefox instance
				System.setProperty("webdriver.gecko.driver", "src/main/resources/Drivers/geckodriver.exe");
				driver = new FirefoxDriver();
			}
			//Check if parameter passed as 'chrome'
			else if(browser.equalsIgnoreCase("Chrome")){
				//set path to chromedriver.exe
				System.setProperty("webdriver.chrome.driver","src/main/resources/Drivers/chromedriver.exe");
				//Chrome instance is created
				driver = new ChromeDriver();
			}

		}
		catch (WebDriverException e) {
			System.out.println(e.getMessage());
		}
	}

	@AfterTest

	public void browserclose() {
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.quit();
	}


}


