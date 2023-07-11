package practice;

import com.comcast.genericutlity.ExcelUtlity;
import com.comcast.genericutlity.FileUtlity;

public class SampleCodeToGetFilePath {

	public static void main(String[] args) throws Throwable {
      
		ExcelUtlity eLib = new ExcelUtlity();
		String data = eLib.getDataFromExcelBasedTestId("./testdata/testScriptdata.xlsx", "contact", "tc_05", "LastName");
		System.out.println(data);
	}

}
