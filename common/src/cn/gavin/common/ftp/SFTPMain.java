package cn.gavin.common.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;


/****
 * SFTP方式传输文件
 * @version 0.1
 * @author gavin.jiang
 * @date 2017/03/16
 */
public class SFTPMain {

	private static Logger logger = Logger.getLogger(SFTPMain.class);

	/***
	 * 用SFTP方式发送文件
	 * @param url  : connect IP
	 * @param port : connect port
	 * @param username : connect userName
	 * @param password : connect password
	 * @param srcPath : sourceFile's directory
	 * @param destPath : destination directory
	 * @param bakPath : backup files directory
	 * @param fileLs : need send files's list
	 */
	public static void sftpSend(String url, int port, String username,
			String password, String srcPath, String destPath, String bakPath,List<File> fileLs) {
		ChannelSftp sftp = null;
		try {
			JSch jsch = new JSch();
			jsch.getSession(username, url, port);
			Session sshSession = jsch.getSession(username, url, port);
			// System.out.println("Session created.");
			sshSession.setPassword(password);
			Properties sshConfig = new Properties();
			sshConfig.put("StrictHostKeyChecking", "no");
			sshSession.setConfig(sshConfig);
			sshSession.connect();
			logger.info("Session connected.");
			Channel channel = sshSession.openChannel("sftp");
			channel.connect();
			sftp = (ChannelSftp) channel;
			logger.info("Connected to " + url + ".");
			upload(destPath, srcPath, sftp,fileLs);
			channel.disconnect();
			sshSession.disconnect();
		} catch (Exception e) {
			logger.error("SFTP send files exception", e);
		}
	}
	
	/***
	 * SFTP方式下载文件
	 * @param url : connect IP
	 * @param port : connect port
	 * @param username : connect userName
	 * @param password : connect password
	 * @param srcPath : sourceFile's directory
	 * @param destPath : destination directory
	 * @param bakPath : backup files directory
	 */
	public static void sftpDownload(String url, int port, String username,
			String password, String srcPath, String destPath, String bakPath) {
		ChannelSftp sftp = null;
		try {
			JSch jsch = new JSch();
			jsch.getSession(username, url, port);
			Session sshSession = jsch.getSession(username, url, port);
			// System.out.println("Session created.");
			sshSession.setPassword(password);
			Properties sshConfig = new Properties();
			sshConfig.put("StrictHostKeyChecking", "no");
			sshSession.setConfig(sshConfig);
			sshSession.connect();
			logger.info("Session connected.");
			Channel channel = sshSession.openChannel("sftp");
			channel.connect();
			sftp = (ChannelSftp) channel;
			logger.info("Connected to " + url + ".");
			synchronized(SFTPMain.class){
				download(destPath,srcPath,sftp,true);
			}
			channel.disconnect();
			sshSession.disconnect();
		} catch (Exception e) {
			logger.error("SFTP send files exception", e);
		}
	}
	
	/***
	 * SFTP方式下载文件,同一个server下可以是有多个srcPath和多个的destPath
	 * srcPath的个数应该和destPath的个数一样多
	 * 如果个数不一样，则按照少的来计算
	 * @param url ：ftp服务器的url
	 * @param port ： ftp服务器的port
	 * @param username ：ftp connect的username
	 * @param password ：ftp connect的password
	 * @param srcPath ： 源文件夹路径
	 * @param destPath ： 目标文件夹路径
	 * @param bakPath ： 备份文件夹的路径
	 */
	public static void sftpDownload(String url, int port, String username,
			String password, String[] srcPath, String[] destPath, String bakPath) {
		ChannelSftp sftp = null;
		try {
			JSch jsch = new JSch();
			jsch.getSession(username, url, port);
			Session sshSession = jsch.getSession(username, url, port);
			// System.out.println("Session created.");
			sshSession.setPassword(password);
			Properties sshConfig = new Properties();
			sshConfig.put("StrictHostKeyChecking", "no");
			sshSession.setConfig(sshConfig);
			sshSession.connect();
			logger.info("Session connected.");
			Channel channel = sshSession.openChannel("sftp");
			channel.connect();
			sftp = (ChannelSftp) channel;
			logger.info("Connected to " + url + ".");
			synchronized(SFTPMain.class){
				if(srcPath.length <= destPath.length){
					for(int i = 0; i < srcPath.length; i++){
						download(destPath[i],srcPath[i],sftp,true);
					}
				}else{
					for(int i = 0; i < destPath.length; i++){
						download(destPath[i],srcPath[i],sftp,true);
					}
				}
			}
			if(channel.isConnected()){
				channel.disconnect();
			}
			if(sshSession.isConnected()){
				sshSession.disconnect();
			}
		} catch (Exception e) {
			logger.error("SFTP send files exception", e);
		}
	}
	
	
	
	
	/**
	 * upload files from srcPath to directory
	 * @param directory
	 * @param srcPath
	 * @param sftp
	 * @param fileLs
	 */
	private static void upload(String directory, String srcPath,
			ChannelSftp sftp,List<File> fileLs) {
		try {
			sftp.cd(directory);
			File file = new File(srcPath);
			if (file.exists()) {
				if (file.isDirectory()) {
					//////////////////////修改
					File[] files ;
					if(null == fileLs || fileLs.size()<1){
						files = file.listFiles();
					}else{
						files =  (File[]) fileLs.toArray();
					}
					
					for (int i = 0; i < files.length; i++) {
						InputStream is = new FileInputStream(files[i]);
						if (null != is) {
							sftp.put(is, files[i].getName());
							is.close();
						}
						files[i].delete();
					}
				} else {
					InputStream is = new FileInputStream(file);
					if (null != is) {
						sftp.put(is, file.getName());
						is.close();
					}
					file.delete();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/***
	 * 下载文件，并根据deleteSourceFile的值判断是否删除源文件
	 * @param destDirectory ：  destination directory
	 * @param srcPath : sourceFile's directory 
	 * @param sftp : SFTP 通道
	 * @param fileLs : if null then get files from srcPath  ,otherwise downLoad files from fileLs list
	 * @param deleteSourceFile : true --> to delete ; false--> stay alive
	 */
	private static void download(String destDirectory, String srcPath,
			ChannelSftp sftp,boolean deleteSourceFile){
		try {
			sftp.cd(srcPath);
			Vector<LsEntry> vec = sftp.ls(srcPath);
			Iterator<LsEntry> fileNameIterator = vec.iterator();
			while(fileNameIterator.hasNext()){
				LsEntry entry = fileNameIterator.next();
				String fileName = entry.getFilename();
				if(!fileName.equals(".") && (!fileName.equals(".."))){
					sftp.get(fileName, destDirectory);
					if(deleteSourceFile){
						sftp.rm(fileName); ////删除文件
					}
				}
			}
		} catch (SftpException e) {
			logger.error("SFTP download file Exception :", e);
		}
		
	}
}
