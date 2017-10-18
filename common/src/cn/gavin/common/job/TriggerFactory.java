package cn.gavin.common.job;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.CronExpression;
import org.quartz.CronTrigger;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

/****
 * 封装了Trigger，是Trigger的factory类
 * 
 * @version 0.1
 * @author gavin.jiang
 * @date 2017/03/16
 */
public class TriggerFactory {

	private static Logger logger = Logger.getLogger(TriggerFactory.class);
	
	/****
	 * 实例化Trigger对象
	 * @param triggerName : 触发器的名称
	 * @param group : 触发器的组别
	 * @param startTime : job开始运行的时间
	 * @param endTime : job停止的时间
	 * @param repeatCount :job重复的次数
	 * @param interval  : job每两次运行中间的时间间隔
	 * @param conexpression ：job运行的时间配置字符串
	 * @return  返回的是Trigger的实例化对象
	 * @throws Exception 抛出异常
	 */
	public static Trigger getQuartzTrigger(String triggerName,String group,Date startTime,Date endTime,int repeatCount,long interval,String conexpression) throws Exception{
		
		TriggerBuilder  builder = TriggerBuilder.newTrigger();
		builder.startAt(startTime);
		builder.withIdentity(triggerName, group);
		if(null != endTime){
			builder.endAt(endTime);
		}
		if(null == conexpression){
			//builder.withSchedule(simpleSchedule());
			builder.withSchedule(simpleSchedule().withRepeatCount(repeatCount).withIntervalInMilliseconds(interval));
			return (SimpleTrigger)builder.build();
		}else{
			CronExpression con = new CronExpression(conexpression);
			builder.withSchedule(cronSchedule(con));
			return (CronTrigger) builder.build();
		}
	}
}
