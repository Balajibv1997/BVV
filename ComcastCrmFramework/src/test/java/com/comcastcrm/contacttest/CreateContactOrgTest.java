package com.comcastcrm.contacttest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.comcast.genericutlity.ExcelUtlity;
import com.comcast.genericutlity.FileUtlity;
import com.comcast.genericutlity.JavaUtlity;
import com.comcast.genericutlity.WebActionUtility;



public class CreateContactOrgTest {
	
	@Test
	public void createOrgTest() throws Throwable{		
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
		
		  String orgName = eLib.getDataFromExcelBasedTestId(TEST_SCRIPT_EXCEL_FILE_PATH, "contact", "tc_06", "OrgName") +"_"+ randomNum;
		  String lastName = eLib.getDataFromExcelBasedTestId(TEST_SCRIPT_EXCEL_FILE_PATH, "contact", "tc_06", "ContactLastName") +"_"+ randomNum;

	
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
        driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
        
   	 /*verify expected result*/
        String actHeader = driver.findElement(By.className("dvHeaderText")).getText();
        if(actHeader.contains(orgName)) {
       	 System.out.println(orgName+ "is verified PASS");
        }else {
       	 System.out.println(orgName+ "is not verified FAIL");

        }
        
     wLib.waitForElement(driver, driver.findElement(By.xpath("//img[@alt='Create Contact...']")));
		/*step 5 : navigate to Org page*/
		driver.findElement(By.linkText("Contacts")).click();
		
		/*step 6 :  navigate to create Org page*/
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		
		/*step 4 :  create a new org*/
		driver.findElement(By.name("lastname")).sendKeys(lastName);		
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
		 //switch to child window
		wLib.swithToWindowBasedOnURL(driver, "module=Accounts");
	    driver.findElement(By.id("search_txt")).sendKeys(orgName);
	    driver.findElement(By.name("search")).click();
	    driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
	     //switch to parent window window
	    wLib.swithToWindowBasedOnURL(driver, "module=Contacts");  
        driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
        
		 /*verify expected result*/
         actHeader = driver.findElement(By.className("dvHeaderText")).getText();
        if(actHeader.contains(lastName)) {
       	 System.out.println(lastName+ "is verified PASS");
        }else {
       	 System.out.println(lastName+ "is not verified FAIL");

        }
        
       String actOrgName =  driver.findElement(By.xpath("//td[@id='mouseArea_Organization Name']/a")).getText();
       if(actOrgName.contains(orgName)) {
      	 System.out.println(orgName+ "is verified PASS");
       }else {
      	 System.out.println(orgName+ "is not verified FAIL");

         
       
       
		/*step 4 :  logout*/
		WebElement ele =  driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
        wLib.mouseOverOnElement(driver, ele);
		driver.findElement(By.linkText("Sign Out")).click();
		driver.close();

	}


       
	}
}





