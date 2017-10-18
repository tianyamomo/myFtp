package cn.gavin.common.util.dataSourceUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.util.Iterator;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.alibaba.druid.pool.DruidDataSource;

import cn.gavin.common.exception.extension.DataSourceException;

/**
 * 采用alibaba的Druid创建数据库连接池和连接
 * 用来查询N4的数据
 *
 *这个Class 需要加载的jdbc.properties的属性如下（oracle）：
 *driverClassName ： 数据库驱动
 *url ： 数据库连接串
 *username ： 连接数据库的账号
 *password ： 连接数据库账号的密码
 *initialSize ： 初始化连接的个数
 *maxActive ： 最大活跃的连接个数
 *minIdle ： 最小的空闲连接数
 *maxWait ： 连接的最大等待时间
 *minEvictableIdleTimeMillis ： 
 *timeBetweenEvictionRunsMillis ： 
 *poolPreparedStatements ： 
 *validationQuery ：
 *testWhileIdle ：
 *testOnBorrow ：
 *testOnReturn ：
 *removeAbandoned ： 
 *
 *@version 0.1
 *@author gavin.jiang
 *@date 2017/03/16
 */
public class JdbcUtils {
	private static Logger logger = Logger.getLogger(JdbcUtils.class);
	private static DruidDataSource dataSource;
	private static JdbcUtils instance = null;
	private static Connection con = null;
	private DataSourceParameterEntity dspe = new DataSourceParameterEntity();
	
	static{
		instance = new JdbcUtils();
	}
	
	private JdbcUtils(){
		
	}
	
	/**
	 *初始化数据库连接池
	 */
	private void instances(String jdbcFile,String startWithStr){
		Properties p = new Properties();
		InputStream is = null;
		
		try {
			String path = System.getProperty("user.dir");
			path = path.replace("\\", "/")+jdbcFile; 
			is =  new FileInputStream(path);  ////this.getClass().getResourceAsStream("/jdbc.properties");
			p.load(is);
			Iterator<Object> it =  p.keySet().iterator();
			while(it.hasNext()){
				String key = it.next().toString();
				String keyName = "";
				if(key.contains(".")){
					keyName = key.substring(key.lastIndexOf(".")+1, key.length());
				}else{
					keyName = key;
				}
				if(null != startWithStr && (!startWithStr.equals(""))){
					if(!key.startsWith(startWithStr)){
						continue;
					}
				}
				if(null != p.get(key)){
					Type fieldType = dspe.getClass().getDeclaredField(keyName).getType();  /////获取field的Type类型
					String fieldClass = fieldType.toString().substring(fieldType.toString().indexOf(" ")+1, fieldType.toString().length());
					Object args = null ;
					if(fieldClass.equals("java.lang.String")){
						args = p.get(key).toString();
					}else if(fieldClass.equals("java.lang.Long")){
						args = Long.valueOf(p.get(key).toString());
					}else if(fieldClass.equals("java.lang.Integer")){
						args = Integer.valueOf(p.get(key).toString());
					}else if(fieldClass.equals("java.lang.Boolean")){
						args = Boolean.valueOf(p.get(key).toString());
					}else if(fieldClass.equals("java.lang.Double")){
						args = Double.valueOf(p.get(key).toString());
					}else if(fieldClass.equals("java.lang.Short")){
						args = Short.valueOf(p.get(key).toString());
					}else if(fieldClass.equals("java.lang.Byte")){
						args = Byte.valueOf(p.get(key).toString());
					}else if(fieldClass.equals("java.lang.Float")){
						args = Float.valueOf(p.get(key).toString());
					}
					Method mt = dspe.getClass().getMethod("set"+keyName.substring(0,1).toUpperCase()+keyName.substring(1, keyName.length()), new Class[]{args.getClass()});
					mt.invoke(dspe, args);
				}
			}
			
			getDataSource();
		}catch(Exception e){
			logger.error("InsJdbcUtils: init dataSource failed exception",e);
		}finally{
			if(is != null){
				try {
					is.close();
					is = null;
				} catch (IOException e) {
					logger.error("InsJdbcUtils : file closed Exception", e);
				}
			}
		}
	}
	
	
	private void getDataSource(){
		dataSource = new DruidDataSource();
		
		Field[] fields = dspe.getClass().getDeclaredFields();
		for(int i = 0; i < fields.length; i++){
			String fieldName = fields[i].getName();
			try{
				Method dsMt = dspe.getClass().getMethod("get"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1, fieldName.length()));
				Object value = dsMt.invoke(dspe);
				if(value  instanceof String){
					Method mt = dataSource.getClass().getMethod("set"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1, fieldName.length()), new Class[]{value.getClass()});
					mt.invoke(dataSource, value.toString());
				}else if(value instanceof Integer){
					Method mt = dataSource.getClass().getMethod("set"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1, fieldName.length()), new Class[]{int.class});
					mt.invoke(dataSource, value);
				}else if(value instanceof Long){
					Method mt = dataSource.getClass().getMethod("set"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1, fieldName.length()), new Class[]{long.class});
					mt.invoke(dataSource, value);
				}
			}catch(Exception e){
				try {
					throw new DataSourceException(e);
				} catch (DataSourceException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	
	/**
	 * 获取数据库连接
	 * @return 返回connection对象
	 */
	public static synchronized Connection getConnection(){
		try{
			con = dataSource.getConnection();
		}catch(Exception e){
			logger.error("get connection failed exception", e);
		}
		return con;
	}
	
	/***
	 * 调用函数，创建数据库连接池
	 * @param jdbcPropertiesFileRelativePath jdbc.properties文件相对于项目的路径
	 * @param startWithStr  jdbc.properties文件中配置项开头字符串，如果没有多个数据库的配置的话，可以为空
	 */
	public static void init(String jdbcPropertiesFileRelativePath,String startWithStr){
		synchronized(instance.getClass()){
			if(null != dataSource){
				
			}else{
				instance.instances(jdbcPropertiesFileRelativePath,startWithStr);
			}
		}
	}
}
