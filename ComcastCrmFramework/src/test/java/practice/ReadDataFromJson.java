package practice;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadDataFromJson {

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		
		/*parse the Json File*/
		JSONParser p = new JSONParser();
		Object obj = p.parse(new FileReader("C:\\Users\\User\\Desktop\\data\\appCommonData.json"));
		
		/*downcast the obj in to JsonObject , automatically all the will stored in the form og map */
		JSONObject map = (JSONObject)obj;
		System.out.println(map.get("browser"));
		
	
	}

}
