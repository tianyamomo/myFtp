package cn.gavin.common.job;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
/**
 * 实现了Job接口 
 *    job运行的时候首先会调用execute方法
 *    通过java的反射机制来决定需要调用哪个类的哪个方法进行执行
 * @version 0.1
 * @author gavin.jiang
 * @date 2017/03/16
 */
public class JobWork implements Job{

	private static Logger logger = Logger.getLogger(JobWork.class);
	private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		String jobId = arg0.getJobDetail().getJobDataMap().get("jobId").toString();
		String jobClassName = arg0.getJobDetail().getJobDataMap().get("jobClassName").toString();
		String jobClassMethod = arg0.getJobDetail().getJobDataMap().get("jobClassMethod").toString();
		List<Map<String,Object>> paramAndValueList = (List<Map<String, Object>>) arg0.getJobDetail().getJobDataMap().get("paramValueList");
//		boolean jobUseThread = Boolean.parseBoolean(arg0.getJobDetail().getJobDataMap().get("jobUseThread").toString());
//		int jobThreadCount = Integer.valueOf(arg0.getJobDetail().getJobDataMap().get("jobThreadCount").toString());
		logger.info("the job is running: "+jobId);
		//////调用service
		try {
			Class c = Class.forName(jobClassName);
			Object obj = c.newInstance();
			Class[] parameterTypes = null;
			Object[] args = null;
			if(null !=paramAndValueList && paramAndValueList.size()>0){
				parameterTypes = new Class[paramAndValueList.size()];
				args = new Object[paramAndValueList.size()];
				for(int i = 0; i < paramAndValueList.size(); i++){
					Map<String,Object> paramMap = paramAndValueList.get(i);
					Iterator<String> it = paramMap.keySet().iterator();
					while(it.hasNext()){
						String key = it.next();
						parameterTypes[i] = Class.forName(key);
						args[i] = paramMap.get(key);
					}
				}
			}
			//利用反射找到对应的方法，并且传入参数进行调用该方法.
			Method mt = c.getMethod(jobClassMethod, parameterTypes);
			mt.invoke(obj, args);
		} catch (Exception e) {
			logger.error("job start running exception", e);
		}
	}
}
