package com.selenium.basecode;


import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.swing.JSlider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.selenium.multiplebrowser.MultipleBrowser;
import com.selenium.utils.ReadExcel;


public class BaseCode extends MultipleBrowser{
	public static Properties prop;
	public static Logger log=LogManager.getLogger(BaseCode.class.getName());


	@Test
	public  void executeTest() throws IOException  {

		try {

			String excelPath = "src/main/resources/ExcelInput/";
			String excelFileName = "input.xlsx";
			String targetPath = excelPath+excelFileName;

			//Launching Browser
			ReadExcel readExcel = new ReadExcel();
			Map<String, String> inputValuesFromExcel =  readExcel.getKeyValuePair(targetPath);
			driver.get(inputValuesFromExcel.get("url"));


			//TestCase1: To verify HomePage is loading correctly
			log.info("verifying HomePageTitle");
			String title = driver.getTitle();
			log.info("Found homePageTitle->"+title);
			System.out.println(title);
			Assert.assertEquals(title,"Google");
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);


			//Maximizing the window
			driver.manage().window().maximize();

			//Check Search Box is Displayed
			WebElement searchBox = driver.findElement(By.name("q"));

			//TestCase2: Search Box is displayed
			Assert.assertEquals(searchBox.isDisplayed(), true);
			Reporter.log("Search Box is displayed." , true);
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

			//Enter jpr as input in Search text box
			searchBox.sendKeys(inputValuesFromExcel.get("input"));

			//TestCase3: Check if JPG to PDF is present in the Google suggestions
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			List<WebElement> lstGoogle = driver.findElements(By.xpath("//ul[@role='listbox']//li/descendant::div[@class='sbl1']"));
			//driver.findElement(By.xpath("//ul[@role='listbox']")).findElements(By.xpath("//li[@role='presentation']"));

			System.out.println("Total no of suggestions in search box:::====> " + lstGoogle.size());
			for (int i = 0; i < lstGoogle.size(); i++) { 
				System.out.println(lstGoogle.get(i).getText()); 
				//System.out.println("abc");

				if (lstGoogle.get(i).getText().contains("jpg to pdf")) {
					System.out.println("jpg to pdf found :::--->" + lstGoogle.get(i).getText());
					//lstGoogle.get(i).click();
					break;

				}
			} 
		}
		catch (StaleElementReferenceException e)
		{System.out.println(e);
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		}
	}
}
