package cn.gavin.common.job;

import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;

/****
 * 封装了JobDetail-JobDetailFactory，是JobDetail的Factory类
 * 获取JobDetail
 * @version 0.1
 * @author gavin.jiang
 * @date 2017/03/16
 */
public class JobDetailFactory {

	private static Logger logger = Logger.getLogger(JobDetailFactory.class);
	
	/****
	 * 获取JobDetail实例化对象
	 * 
	 * @param jobName ： job的名称
	 * @param jobGroupName : job的组名
	 * @param jobClass ：运行job的类名
	 * @param paratMap : 运行job时需要传入的参数,所必须的参数： jobId job的唯一标识符,jobClassName job运行时需要的类名, jobClassMethod job运行时jobClassName类调用的方法
	 * 						可选参数： paramValueList job运行时调用jobClassMethod 方法所需要的参数类型和参数值,如果没有这个参数,则表示调用jobClassMethod方法是没有形参的
	 * @return 返回JobDetail实例化对象
	 * @throws Exception 异常
	 */
	public static JobDetail getJobDetail(String jobName,String jobGroupName,Class <? extends Job> jobClass,Map<String,Object> paratMap) throws Exception{
		try {
			JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).build();  
			if(paratMap != null && !paratMap.isEmpty()){
				Iterator<String> iterator = paratMap.keySet().iterator();
				while (iterator.hasNext()) {
					String key = (String) iterator.next();
					Object value = paratMap.get(key);
					jobDetail.getJobDataMap().put(key, value);
				}
			}
			return jobDetail;
		} catch (Exception e) {
			logger.error("JobDetailFactory创建JobDetail异常",e);
			throw e;
		}
	}
	
}
