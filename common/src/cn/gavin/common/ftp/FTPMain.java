package cn.gavin.common.ftp;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;

/****
 * FTP方式进行调用FTPProxy
 * 利用FTPProxy的方法进行传输
 * @version 0.1
 * @author gavin.jiang
 * @date 2017/03/16
 */
public class FTPMain{

	private static Logger logger = Logger.getLogger(FTPMain.class);
	
	/***
	 * 获取文件
	 * 如果srcPath 和bakPath 不为空的话，则采取的复制的方式获取文件，这个时候，
	 * @param url  : connect IP
	 * @param port : connect port
	 * @param username : connect userName
	 * @param password : connect password
	 * @param srcPath : sourceFile's directory
	 * @param destPath : destination directory
	 * @param bakPath : backup files directory
	 * @param flag : 1 复制并保存到bakPath; 2 剪切.
	 */
	public static void ftp(String url,int port,String username,String password,String srcPath,String destPath,String bakPath,int flag){
		try{
			if((url != null)&&(username != null) && (password != null)){
				FTPProxy proxy = null;
				if(port == 0){
					proxy = new FTPProxy(url,username,password);
				}else{
					proxy = new FTPProxy(url,port,username,password);
				}
				if((srcPath != null) && (bakPath != null)){
					proxy.copy(srcPath,bakPath, flag);
				}else{
					logger.warn(" srcPath or bakPath is null");
				}
				Thread.sleep(500);
				if(destPath != null){
					proxy.copy(srcPath, destPath, flag);////此时采用的是剪切的方式
				}else{
					logger.error("destPath is null");
					return;
				}
			}
		}catch(Exception e){
			logger.error("ftp failed",e);
		}
	}
	
	
	/*****
	 * 
	 * @param url : connect IP
	 * @param port : connect port
	 * @param username : connect userName
	 * @param password  : connect password
	 * @param srcPath : sourceFile's directory
	 * @param destPath  : destination directory
	 * @param bakPath  : backup files directory
	 */
	public static void ftp(String url,int port,String username,String password,String[] srcPath,String[] destPath,String bakPath){
		try{
			if((url != null)&&(username != null) && (password != null)){
				FTPProxy proxy = null;
				if(port == 0){
					proxy = new FTPProxy(url,username,password);
				}else{
					proxy = new FTPProxy(url,port,username,password);
				}
				
				if((srcPath != null) && srcPath.length>0  && (bakPath != null)){
					for(int i = 0; i < srcPath.length; i++){
						proxy.copy(srcPath[i],bakPath, 1);
					}
				}else{
					logger.warn(" srcPath or bakPath is null");
				}
				Thread.sleep(500);
				if(destPath != null && destPath.length>0  && null != srcPath && srcPath.length>0){
					if(srcPath.length <= destPath.length){  /////如果srcPath 少于 destPath, 则只会从destPath中取和srcPath相同个数的属性，即srcPath有几个，则destPath就会有几个，多余的不认，不传输文件过去；
						for(int i = 0; i < srcPath.length; i++){
							proxy.copy(srcPath[i], destPath[i], 2);////此时采用的是剪切的方式
						}
					}else{ /////如果srcPath 多余 destPath,则会在抛出异常，多余的文件夹中的文件没有去处.
						for(int i = 0; i < destPath.length; i++){
							proxy.copy(srcPath[i], destPath[i], 2);////此时采用的是剪切的方式
						}
						logger.error("ftp Exception : srcDirCount more than destDirCount, pls check and change it");
					}
				}else{
					logger.error("destPath is null");
					return;
				}
			}
		}catch(Exception e){
			logger.error("ftp failed",e);
		}
	}
	
	
	
	/***
	 * 如果fileLs不为空的话，就对list中的file操作
	 * 发送
	 * @param url : connect IP
	 * @param port  : connect port
	 * @param username : connect userName
	 * @param password : connect password
	 * @param srcPath : sourceFile's directory
	 * @param destPath  : destination directory
	 * @param bakPath  : backup files directory
	 * @param fileLs : need sent files's list
	 */
	public static void ftpSend(String url,int port,String username,String password,String srcPath,String destPath,String bakPath,List<File> fileLs){
		try{
			if((url != null)&&(username != null) && (password != null)){
				FTPProxy proxy = null;
				if(port == 0){
					proxy = new FTPProxy(url,username,password);
				}else{
					proxy = new FTPProxy(url,port,username,password);
				}
				if((srcPath != null) && (bakPath != null)){
					proxy.send(srcPath,bakPath, 1,fileLs);
				}else{
					logger.warn(" srcPath or bakPath is null");
				}
				Thread.sleep(5000);
				if(destPath != null){
					proxy.send(srcPath, destPath, 2,fileLs);////此时采用的是剪切的方式
				}else{
					logger.error("destPath is null");
					return;
				}
			}
		}catch(Exception e){
			logger.error("ftp failed",e);
		}
	}
	
}
