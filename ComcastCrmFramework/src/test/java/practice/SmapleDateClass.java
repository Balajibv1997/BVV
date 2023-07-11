package practice;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.mysql.cj.x.protobuf.MysqlxResultset.FetchSuspendedOrBuilder;

public class SmapleDateClass {

	public static void main(String[] args) {
	

	   Date date = new Date();
	   SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
	   String fdate =   sim.format(date);
	   
	   
         System.out.println(fdate);
	}

}
