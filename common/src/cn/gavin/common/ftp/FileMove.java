package cn.gavin.common.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.log4j.Logger;

import com.alibaba.druid.util.StringUtils;

/***
 * 进入FTP或者是SFTP的入口
 * 
 * @version 0.1
 * @author gavin.jiang
 * @date 2017/03/16
 */
public class FileMove {
	private static Logger logger = Logger.getLogger(FileMove.class);
	private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);

	/***
	 * 获取文件
	 * @param propertyFileName : Configuration  property File Name
	 * @return List类型的数据
	 */
	public synchronized List<Future<String>> getEdi(String propertyFileName) {
		List<Future<String>> futures = new ArrayList<Future<String>>();
		int sourceServerCount = 0; ///FTP server 个数
		String filepath = System.getProperty("user.dir");
		Properties p = new Properties();
		InputStream is = null;
		try {
			is = new FileInputStream(new File(filepath.replace("\\", "/")
					+ "/config/"+propertyFileName+".properties"));
			p.load(is);
			sourceServerCount = Integer.valueOf(p.getProperty("sourceServerCount"));
			if(sourceServerCount>0){
//				futures.add(sourceServerCount);
				String url = null;
				int port = 0;
				String username = null;
				String password = null;
//				String srcPath = null;
//				String destPath = null;
				String bakPath = null;
				String protocol = FTPConstantUtil.PROTOCOL;
				int srcDirCount = 0;
				int destDirCount = 0;
				FTPBean[] ftpBean = new FTPBean[sourceServerCount]; /////创建FTPBean
				for(int i = 0; i < sourceServerCount ; i++){
					url = p.getProperty(FTPConstantUtil.SERVERNAME+(i+1)+"_url");
					String pt = p.getProperty(FTPConstantUtil.SERVERNAME+(i+1)+"_port");
					if (null != pt) {
						port = Integer.parseInt(pt);
					}
					username = p.getProperty(FTPConstantUtil.SERVERNAME+(i+1)+"_username");
					password = p.getProperty(FTPConstantUtil.SERVERNAME+(i+1)+"_password");
//					srcPath = p.getProperty(FTPConstantUtil.SERVERNAME+(i+1)+"_srcPath");
//					destPath = filepath.replace("\\", "/")+p.getProperty(FTPConstantUtil.SERVERNAME+(i+1)+"_destPath");
					bakPath = p.getProperty(FTPConstantUtil.SERVERNAME+(i+1)+"_bakPath");
					protocol = p.getProperty(FTPConstantUtil.SERVERNAME+(i+1)+"_protocol");
					String srcPathCount = p.getProperty(FTPConstantUtil.SERVERNAME+(i+1)+"_srcDirCount");
					String destPathCount = p.getProperty(FTPConstantUtil.SERVERNAME+(i+1)+"_destDirCount");
					if(!StringUtils.isEmpty(srcPathCount)){
						srcDirCount = Integer.valueOf(srcPathCount);
					}
					if(!StringUtils.isEmpty(destPathCount)){
						destDirCount = Integer.valueOf(destPathCount);
					}
					if(srcDirCount > 0){
						String[] srcDir = new String[srcDirCount];
						for(int j = 0; j < srcDirCount; j++){
							String path = p.getProperty(FTPConstantUtil.SERVERNAME+(i+1)+"_srcPath"+(j+1));
							if(!StringUtils.isEmpty(path)){
								srcDir[j] = path;
							}else{
								logger.warn(FTPConstantUtil.SERVERNAME+(i+1)+"_srcPath"+(j+1)+" is NULL");
							}
						}
						ftpBean[i] = new FTPBean();
						ftpBean[i].setSrcPath(srcDir);
					}
					if(destDirCount >0){
						String[] destDir = new String[destDirCount];
						for(int j = 0; j < destDirCount; j++){
							String path = filepath.replace("\\", "/")+p.getProperty(FTPConstantUtil.SERVERNAME+(i+1)+"_destPath"+(j+1));
							if(!StringUtils.isEmpty(path)){
								destDir[j] = path;
							}else{
								logger.warn(FTPConstantUtil.SERVERNAME+(i+1)+"_destPath"+(j+1)+" is NULL");
							}
						}
						ftpBean[i].setDestPath(destDir);
					}
					
					String key = UUID.randomUUID().toString();
					ftpBean[i].setKey(key.replace("-", ""));
					ftpBean[i].setUrl(url);
					ftpBean[i].setProtocol(protocol);
					ftpBean[i].setPort(port);
					ftpBean[i].setUserName(username);
					ftpBean[i].setPassWord(password);
					ftpBean[i].setBakPath(bakPath);
					
					if(!StringUtils.isEmpty(url) && (!StringUtils.isEmpty(username)) && (!StringUtils.isEmpty(password)) && (!StringUtils.isEmpty(protocol)) && (ftpBean[i].getSrcPath().length>0) && (ftpBean[i].getDestPath().length>0)){
						FTPThread ftpTh = new FTPThread(ftpBean[i]);
						Future<String> future = fixedThreadPool.submit(ftpTh);
						futures.add(future);
						
					}else{
						logger.warn("FTPBean some fields is NULL. Will not get files from FTP Server");
					}
				}
			}
		} catch (Exception e) {
			logger.error("analyze "+propertyFileName+".properties failed", e);
		} finally {
			try {
				if (null != is) {
					is.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return futures;
	}
	
	/***
	 * 发送文件
	 * @param propertyFileName 配置文件名
	 * @param protocol ftp/sftp
	 * @param fileLs : 如果fileLs不为空的话，则对fileLs中的file操作
	 */
	public synchronized void send(String propertyFileName,String protocol,List<File> fileLs) {
		String url = null;
		int port = 0;
		String username = null;
		String password = null;
		String srcPath = null;
		String destPath = null;
		String bakPath = null;
		String filepath = System.getProperty("user.dir");
		Properties p = new Properties();
		InputStream is = null;
		try {
			is = new FileInputStream(new File(filepath.replace("\\", "/")
					+ "/config/"+propertyFileName+".properties"));
			p.load(is);
			url = p.getProperty("url");
			String pt = p.getProperty("port");
			if (null != pt) {
				port = Integer.parseInt(pt);
			}
			username = p.getProperty("username");
			password = p.getProperty("password");
			srcPath = filepath.replace("\\", "/")+p.getProperty("srcPath");
			destPath = p.getProperty("destPath");
			bakPath = p.getProperty("bakPath");
			
			/////////传输文件
			if(protocol.equalsIgnoreCase("FTP")){
//				FTPMain ftp = new FTPMain();
				FTPMain.ftpSend(url, port, username, password, srcPath, destPath, bakPath,fileLs);
			}else if(protocol.equalsIgnoreCase("SFTP")){
//				SFTPMain sftp = new SFTPMain();
				SFTPMain.sftpSend(url, port, username, password, srcPath, destPath, bakPath,fileLs);
			}
			
		} catch (Exception e) {
			logger.error("analyze "+propertyFileName+".properties failed", e);
		} finally {
			try {
				if (null != is) {
					is.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
