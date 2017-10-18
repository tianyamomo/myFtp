package cn.gavin.common.util.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.alibaba.druid.util.StringUtils;

import cn.gavin.common.util.DateFormatConstant;


/**
 * Date类型的帮助类
 * @version 0.1
 * @author gavin.jiang
 * @date 2017/03/16
 */
public class DateUtil {

	private DateFormat df ;
	private String year ;
	private String month;
	private String day;
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	
	/*
	 * 默认是用"yyyy/MM/dd HH:mm:ss"
	 */
	public DateUtil(){
		df = new SimpleDateFormat(DateFormatConstant.DATEFORMATOFYYYYMMDDHHMMSS);
	}
	
	public DateUtil(String format){
		df = new SimpleDateFormat(format);
	}
	
	/*
	 * 获取当前时间
	 * @return
	 */
	public String getNowaday(){
		Date dt = new Date();
		return df.format(dt);
	}
	
	/*
	 * 获取当前的日期
	 * @return
	 * @throws ParseException
	 */
	public Date getNowDate() throws ParseException{
		if(null == df){
			df = new SimpleDateFormat(DateFormatConstant.DATEFORMATOFYYYYMMDDHHMMSS);
		}
		return df.parse(df.format(new Date()));
	}
	
	
	/*
	 * 将String类型的dateString转换成Date类型
	 * @param dateString
	 * @return
	 * @throws ParseException
	 */
	public Date stringToDate(String dateString) throws ParseException{
		if(!StringUtils.isEmpty(dateString)){
			if(null == df){
				df = new SimpleDateFormat(DateFormatConstant.DATEFORMATOFYYYYMMDDHHMMSS);
			}
			return df.parse(dateString);
		}else{
			return null;
		}
	}
	
	/*
	 * 
	 * @param dateString
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public Date stringToDateFormat(String dateString,String format) throws ParseException{
		if(!StringUtils.isEmpty(dateString)){
			if(!StringUtils.isEmpty(format)){
				df = new SimpleDateFormat(format);
				return df.parse(dateString);
			}else{
				if(null == df){
					df = new SimpleDateFormat(DateFormatConstant.DATEFORMATOFYYYYMMDDHHMMSS);
				}
				return df.parse(dateString);
			}
		}else{
			return null;
		}
	}
	
	/*
	 * 获取当前时间的long值
	 * @return
	 */
	public static Long getNowTime(){
		Date dt = new Date();
		return dt.getTime();
	}
	
	/*
	 * get the big time
	 * */
	public static Date getBigTime(Date dt1,Date dt2){
		return dt1.before(dt2)?dt2:dt1;
	}
	
}
