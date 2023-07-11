package practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadDataFromPropertiesFile {

	public static void main(String[] args) throws Throwable {
	
		//step 1 : get the java representation object of the physical file 
		FileInputStream fis = new FileInputStream("C:\\Users\\User\\Desktop\\data\\commondata.properties");
		//step 2 : using Properties class , load all the keys & value pairs
		Properties pObj = new Properties();
		           pObj.load(fis); 
		//step 3 : get the value using getProperties(key)
		           System.out.println(pObj.getProperty("browser"));

	}

}
