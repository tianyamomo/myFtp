package cn.gavin.common.ftp;

import java.io.Serializable;

/****
 * FTP/SFTP获取文件时需使用的Bean
 * @author gavin.jiang
 * @version 0.1
 * @date 2017/03/16
 *
 */
@SuppressWarnings("serial")
public class FTPBean implements Serializable{

	private String key;
	private String protocol;
	private String url;
	private int port;
	private String userName;
	private String passWord;
	private String[] srcPath;
	private String[] destPath;
	private String bakPath;

	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String[] getSrcPath() {
		return srcPath;
	}
	public void setSrcPath(String[] srcPath) {
		this.srcPath = srcPath;
	}
	public String[] getDestPath() {
		return destPath;
	}
	public void setDestPath(String[] destPath) {
		this.destPath = destPath;
	}
	
	public String getBakPath() {
		return bakPath;
	}
	public void setBakPath(String bakPath) {
		this.bakPath = bakPath;
	}
	public FTPBean() {
		
	}
	
	public FTPBean(String key, String protocol, String url, int port,
			String userName, String passWord, String[] srcPath,
			String[] destPath, String bakPath) {
		super();
		this.key = key;
		this.protocol = protocol;
		this.url = url;
		this.port = port;
		this.userName = userName;
		this.passWord = passWord;
		this.srcPath = srcPath;
		this.destPath = destPath;
		this.bakPath = bakPath;
	}

}
