package cn.gavin.common.util.string;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import cn.gavin.common.util.DateFormatConstant;


/****
 * String类型的帮助类
 * @version 0.1
 * @author gavin.jiang
 * @date 2017/03/16
 */
public class StringUtil {
	
	
	/***
	 * get 32 Byte UUID.
	 * @return 返回String类型数据
	 */
	public static String get32ByteStringUUID(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}
	
	/***
	 * 获取当前时间的字符串
	 * @param pattern 格式
	 * @return 返回String类型的当前日期
	 */
	public static String getNowadayOfString(String pattern){
		DateFormat df = new SimpleDateFormat(pattern);
		return df.format(new Date());
	}
	
	/***
	 * 日期转字符串
	 * @param dt 日期
	 * @param pattern 日期格式
	 * @return 返回String类型的日期
	 */
	public static String dateToString(Date dt,String pattern){
		DateFormat df = new SimpleDateFormat(pattern);
		return df.format(dt);
	}
	
	/***
	 * 获取当前时间
	 * @return 当前时间的Long类型数据
	 */
	public static Long getNowTime(){
		Date dt = new Date();
		return dt.getTime();
	}
	
	/***
	 * 获取dayDif天前的日期
	 * @param dt Date类型的日期
	 * @param dayDif int类型天数差
	 * @param pattern 日期格式
	 * @return 返回dt日期dayDif天之前的String类型数据
	 */
	public static String getDayBefore(Date dt,int dayDif,String pattern){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, (0-dayDif));
		Date dt2 = cal.getTime();
		DateFormat df = new SimpleDateFormat(pattern);
		return df.format(dt2);
	}
	
	/***
	 * 获取dayDif天后的日期 
	 * @param dt Date类型的日期
	 * @param dayDif int类型天数差
	 * @param pattern 日期格式
	 * @return 返回dt日期dayDif天之后的String类型数据
	 */
	public static String getDayAfter(Date dt,int dayDif,String pattern){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, dayDif);
		DateFormat df = new SimpleDateFormat(pattern);
		return df.format(cal.getTime());
	}
	
	/***
	 * 获取当前的周数
	 * @return 返回当前周
	 */
	public static int getWeek(){
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.WEEK_OF_YEAR);
	}
	
	/***
	 * 获取当前是一年中的第几天
	 * @return 获取当前是一年中的天数
	 */
	public static int getDayOfYear(){
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.DAY_OF_YEAR);
	}
	
	/***
	 * 获取某个日期在当年的周数
	 * @param dt Date类型日期
	 * @return 返回dt在一年中的的几周
	 */
	public static int  getWeekOfSomeDay(Date dt){
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		return cal.get(Calendar.WEEK_OF_YEAR);
	}
	
	/***
	 * 获取当前所在周的第一天的日期
	 * @return 返回当前周的第一天的日期
	 */
	public static String getfirstDayOfWeek(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_WEEK,Calendar.SUNDAY-cal.get(Calendar.DAY_OF_WEEK));
		DateFormat df = new SimpleDateFormat(DateFormatConstant.DATEFORMATOFYYYYMMDD);
		return df.format(cal.getTime());
	}
	
	/***
	 * 获取dt所在周的第一天
	 * @param dt 日期
	 * @return 返回dt所在周的第一天
	 */
	public static String getFirstDayOfWeekOfSomeDay(Date dt){
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		cal.add(Calendar.DAY_OF_YEAR, Calendar.SUNDAY-cal.get(Calendar.DAY_OF_WEEK));
		DateFormat df = new SimpleDateFormat(DateFormatConstant.DATEFORMATOFYYYYMMDD);
		return df.format(cal.getTime());
	}
	
	
}
