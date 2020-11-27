package com.selenium.testcases;

import java.io.IOException;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.selenium.basecode.BaseCode;
import com.selenium.multiplebrowser.MultipleBrowser;
import com.selenium.utils.ReadExcel;



public class HomePage extends MultipleBrowser{

 static Logger log=LogManager.getLogger(BaseCode.class.getName());
	String excelPath = "src/main/resources/ExcelInput/";
	String excelFileName = "input.xlsx";
	String targetPath = excelPath+excelFileName;
	


	@Test
	public void verifyHomePageTitileTest() throws IOException {
		
		ReadExcel readExcel = new ReadExcel();
		Map<String, String> inputValuesFromExcel =  readExcel.getKeyValuePair(targetPath);
	 	driver.get(inputValuesFromExcel.get("url"));
		log.info("verifying HomePageTitle");
		String title = driver.getTitle();
		log.info("Found homePageTitle->"+title);
		Assert.assertEquals(title,"Online Shoping India|Buy Mobiles, Electronics, Appliances, Clothig and More Online at Flipkart.com");
		System.out.println(title);
	}
}
