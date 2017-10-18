/**
 * 
 *DateTimeRegex.java 
 *
 *@author gavin.jiang
 *@email 820367229@qq.com
 *@date 2017-5-15
 *@version 0.1
 */
package cn.gavin.test;

import java.util.Date;

/**
 * @author gavin.jiang
 *
 */
public class DateTimeRegex {

	
	
	
	public static void main(String[] args) {
		String regx = "^[0-9]{4}\\/(((0[13578]|(10|12))\\/(0[1-9]|[1-2][0-9]|3[0-1]))|(02\\/(0[1-9]|[1-2][0-9]))|((0[469]|11)\\/(0[1-9]|[1-2][0-9]|30))) (([1-9]{1})|([0-1][0-9])|([1-2][0-3]))(:[0-5][0-9]){1,2}$";
		String regex_date = "^[0-9]{4}\\/(((0[13578]|(10|12))\\/(0[1-9]|[1-2][0-9]|3[0-1]))|(02\\/(0[1-9]|[1-2][0-9]))|((0[469]|11)\\/(0[1-9]|[1-2][0-9]|30)))$";
		String dateStr = "2016/05/15 10:11:10";
		String ds = "2016/06/10";
//		if(dateStr.matches(regx)){
//			System.out.println(" isMatch: " + true);
//		}else{
//			System.out.println(" isMatch: " + false);
//		}
		
		String[] months = new String[]{"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
		
//		try {
//			Date dtt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(dateStr);
//			System.out.println(dtt);
//			
//			GregorianCalendar gCal = new GregorianCalendar();
//			StringBuffer strBuf = new StringBuffer("");
//			strBuf.append(gCal.get(Calendar.YEAR)).append("-");
//			strBuf.append(months[gCal.get(Calendar.MONTH)]).append("-");
//			strBuf.append(gCal.get(Calendar.DAY_OF_MONTH)).append(" ");
//			strBuf.append(gCal.get(Calendar.HOUR_OF_DAY)).append(":");
//			strBuf.append(gCal.get(Calendar.MINUTE));
//			System.out.println(strBuf.toString());
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		
		
		Date dt1 = new Date("2017/02/17 13:49:30");
		Date dt2 = new Date();
		
		if(dt1.getTime()< dt2.getTime()){
			System.out.println("true");
		}else{
			System.out.println(false);
		}
		
		
		
		
	}
	
}
