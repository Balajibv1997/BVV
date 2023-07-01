package com.comcastcrm.organizationtest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.comcast.genericutlity.ExcelUtlity;
import com.comcast.genericutlity.FileUtlity;
import com.comcast.genericutlity.JavaUtlity;
import com.comcast.genericutlity.WebActionUtility;

public class createOrgWithType {
	
	@Test
	public void createOrgWithMobileNum() throws Throwable {
		/*create Object for utlity */
		FileUtlity fLib = new FileUtlity();
		ExcelUtlity eLib = new ExcelUtlity();
		JavaUtlity jLib = new JavaUtlity();
		WebActionUtility wLib = new WebActionUtility();

		/*get the FILE PATH*/
       String ENV_FILE_PATH =    fLib.getFilePathFromPropertiesFile("projectConfigDataFilePath");
       String TEST_SCRIPT_EXCEL_FILE_PATH =    fLib.getFilePathFromPropertiesFile("testScriptdatafilePath");
       
       /*Read the common data*/
	   String BROWSER = fLib.getDataFromProperties(ENV_FILE_PATH, "browser");
	   String URL = fLib.getDataFromProperties(ENV_FILE_PATH, "url");
	   String USERNAME = fLib.getDataFromProperties(ENV_FILE_PATH, "username");
	   String PASSWORd = fLib.getDataFromProperties(ENV_FILE_PATH, "password");

		/*test script data*/
	   int randomNum = jLib.getRandomNumber();
		
		  String orgName = eLib.getDataFromExcelBasedTestId(TEST_SCRIPT_EXCEL_FILE_PATH, "org", "tc_02", "organizationName") +"_"+ randomNum;
		  String industry = eLib.getDataFromExcelBasedTestId(TEST_SCRIPT_EXCEL_FILE_PATH, "org", "tc_02", "Industries") ;
		  String type = eLib.getDataFromExcelBasedTestId(TEST_SCRIPT_EXCEL_FILE_PATH, "org", "tc_02", "type") ;

	
		WebDriver driver = null;

		/*step 1 : login to app*/
		if(BROWSER.equalsIgnoreCase("chrome")) {		
		    driver = new ChromeDriver();
		}else if(BROWSER.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}else if(BROWSER.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}else {
		    driver = new ChromeDriver();
		}
		
		
		
		wLib.waitForElementInDOM(driver);
 		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORd);
		driver.findElement(By.id("submitButton")).click();
		/*step 2 : navigate to Org page*/
		driver.findElement(By.linkText("Organizations")).click();
		/*step 3 :  navigate to create Org page*/
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		
		
		/*step 4 :  create a new org*/
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		
		WebElement insEle = driver .findElement(By.name("industry"));
        wLib.select(insEle, industry);

		WebElement typeEle = driver .findElement(By.name("accounttype"));
        wLib.select(typeEle, type);
				
        driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		 /*verify expected result*/
         String actHeader = driver.findElement(By.className("dvHeaderText")).getText();
         if(actHeader.contains(orgName)) {
        	 System.out.println(orgName+ "is verified PASS");
         }else {
        	 System.out.println(orgName+ "is not verified FAIL");

         }
         
        String actIndustry =  driver.findElement(By.id("dtlview_Industry")).getText();
        if(actIndustry.equals(industry)) {
       	 System.out.println(industry+ "is verified PASS");
        }else {
       	 System.out.println(industry+ "is not verified FAIL");

        }
        
        String actType =  driver.findElement(By.id("dtlview_Type")).getText();
        if(actType.equals(type)) {
       	 System.out.println(type+ "is verified PASS");
        }else {
       	 System.out.println(type+ "is not verified FAIL");

        }
        
        
		/*step 4 :  logout*/
		WebElement ele =  driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
          wLib.mouseOverOnElement(driver, ele);
		driver.findElement(By.linkText("Sign Out")).click();
		driver.close();

	}

}
