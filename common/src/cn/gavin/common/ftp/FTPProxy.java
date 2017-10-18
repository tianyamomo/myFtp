package cn.gavin.common.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

/****
 * FTP文件传输的proxy类
 * 由FTPMain进行调用
 * @version 0.1
 * @author gavin.jiang
 * @date 2017/03/16
 */
public class FTPProxy {

	private static Logger logger = Logger.getLogger(FTPProxy.class);

	private String url;
	private int port;
	private String username;
	private String password;

	public FTPProxy(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}

	public FTPProxy(String url, int port, String username, String password) {
		this.url = url;
		this.port = port;
		this.username = username;
		this.password = password;
	}

	/****
	 * 
	 * @param srcPath : 源文件夹路径
	 * @param destPath ：目标文件夹路径
	 * @param flag   1：copy，2：cut
	 */
	public synchronized void copy(String srcPath, String destPath, int flag) {
		FTPClient client = new FTPClient();
		client.setConnectTimeout(30000);
//		client.setDefaultTimeout(10000);
		client.setDataTimeout(10000);
		try {
			try {
				if (0 == port) { // // 没有设置端口号
					client.connect(url);
				} else {// //设置了端口号
					client.connect(url, port);
				}
			} catch (Exception e1) {
				logger.error("connect failed, check the firewall and the values of your setting properties",e1);
			}
			
			int reply = client.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				logger.warn("connect failed,pls check url or port");
//				client.logout();
				client.disconnect();
			} else {
				client.login(username, password);
				reply = client.getReplyCode();
				if(FTPReply.isPositiveCompletion(reply)){
					logger.info("source dir : " + srcPath);
					FTPFile[] files = client.listFiles(srcPath);
					if (flag == 1) {// //copy
						if(files.length>0){
							logger.info("start copy file");
							for (int i = 0; i < files.length; i++) {
								FTPFile file = files[i];
//								file.setPermission(FTPFile.USER_ACCESS, FTPFile.WRITE_PERMISSION, true);
								if (file.isFile()
										&& file.hasPermission(FTPFile.USER_ACCESS,
												FTPFile.WRITE_PERMISSION) && (file.getSize()>0)) {
									String name = file.getName();
									File f = new File(destPath + name);
									f.setReadable(true);
									f.setWritable(true);
									if(!f.exists()){
										f.createNewFile();
									}
									OutputStream os = new FileOutputStream(f);
									client.retrieveFile(srcPath + name, os);
									os.flush();
									os.close();
								}
							}
							logger.info("copy finish");
						}
					} else {// ///复制到文件夹中，然后删除
						for (int i = 0; i < files.length; i++) {
							FTPFile file = files[i];
							long sourceFileSize = file.getSize();
							long destFileSize = 0;
							logger.info(file.getName()+" with size in byte : "+file.getSize());
//							file.setPermission(FTPFile.USER_ACCESS, FTPFile.WRITE_PERMISSION, true);
							if (file.isFile()
									&& file.hasPermission(FTPFile.USER_ACCESS,
											FTPFile.WRITE_PERMISSION) && (file.getSize()>0)) {
								String name = file.getName();
								logger.info("cut file :"+name);
								File f = new File(destPath + name);
//								logger.info(destPath+name);
								if(!f.exists()){
									f.createNewFile();
								}
								f.setReadable(true);
								f.setWritable(true);
								OutputStream os = new FileOutputStream(f);
								client.retrieveFile(srcPath + name, os);
								os.flush();
								os.close();
								client.deleteFile(srcPath + name);
								InputStream fis = new FileInputStream(f);
								destFileSize = fis.available();
								fis.close();
							}
							///////// To Check whether file is complete or not
//							if(sourceFileSize == destFileSize){
//								logger.info( file.getName()+" cut finish");
//							}else{
//								logger.error(file.getName()+" cut failed, some contents lost");
//							}
							
						}
					}
					logger.info("cut finish");
				}else{
					logger.error("login failed : username password wrong! ");
				}
			}
		} catch (Exception e) {
			logger.error("copy file error", e);
		} finally {
			try {
				if (null != client && client.isConnected()) {
					client.logout();
					client.disconnect();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/***
	 * 通过ftp的方式发送文件
	 * @param srcPath : 源文件夹路径
	 * @param destPath ： 源文件夹路径
	 * @param flag : 1，copy;  2，cut
	 * @param fileLs ：需要进行操作的文件list
	 */
	public void send(String srcPath, String destPath, int flag,List<File> fileLs){
		FTPClient client = new FTPClient();
		client.setConnectTimeout(30000);
//		client.setDefaultTimeout(10000);
		client.setDataTimeout(10000);
		try {
			try {
				if (0 == port) { // // 没有设置端口号
					client.connect(url);
				} else {// //设置了端口号
					client.connect(url, port);
				}
			} catch (Exception e1) {
				logger.error(
						"connect failed, check the firewall and the values of  your setting properties",e1);
			}
			
			int reply = client.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				logger.warn("connect failed");
				client.disconnect();
			} else {
				client.login(username, password);
				reply = client.getReplyCode();
				if(FTPReply.isPositiveCompletion(reply)){
					/////////////////////////////修改
					File[] files ;
					if(null == fileLs){
						File ff= new File(srcPath);
						files = ff.listFiles();
					}else{
						files = fileLs.toArray(new File[fileLs.size()]); // (File[]) fileLs.toArray();
					}
					
					if (flag == 1) {// //copy
						if(files.length>0){
							logger.info("start copy file");
							for (int i = 0; i < files.length; i++) {
								File file = files[i];
								if (file.isFile() && file.canRead()) { ////canWrite()
									String name = file.getName();
									File f = new File(destPath + name);
									if(!f.exists()){
										f.createNewFile();
									}
									InputStream is = new FileInputStream(f);
									client.storeFile(f.getName(), is);
									is.close();
			//						OutputStream os = new FileOutputStream(f);
			//						client.retrieveFile(srcPath + name, os);
			//						os.flush();
			//						os.close();
								}
							}
							logger.info("copy finish");
						}
					} else {// ///复制到备份文件夹中，然后删除
						for (int i = 0; i < files.length; i++) {
							File file = files[i];
							if (file.isFile() && file.canRead()) {////canWrite()
								String name = file.getName();
			//					file.setWritable(true);
								client.changeWorkingDirectory(destPath);
								logger.info("file send :"+name);
								InputStream is = new FileInputStream(file);
								client.storeFile(name, is);
								is.close();
								file.delete();
							}
						}
					}
				}else{
					logger.error("login failed. username password wrong!");
				}
			}
		} catch (Exception e) {
			logger.error("copy file error", e);
		} finally {
			try {
				if (null != client && client.isConnected()) {
					client.logout();
					client.disconnect();
				}
			} catch (IOException e) {
				logger.error("logout failed", e);
			}
		}
	}

}
