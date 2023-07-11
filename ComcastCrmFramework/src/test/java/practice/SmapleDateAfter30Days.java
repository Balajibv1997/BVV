package practice;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SmapleDateAfter30Days {

	public static void main(String[] args) {
	

		 Calendar cal =  Calendar.getInstance();
		 cal.add(Calendar.DATE,30);		 
	     Date date1 =  cal.getTime();
	   
	    SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
	    String pfdata = sim.format(date1);
	    System.out.println(pfdata);
	}

}
