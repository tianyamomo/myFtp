package cn.gavin.common.ftp;

import java.io.Serializable;

/***
 * 由于这个是将文件传输到本地的文件夹,所以不需要使用FTP/SFTP的方式进行传输
 * @author gavin.jiang
 * @version 0.1
 * @date 2017/03/16
 *
 */
@SuppressWarnings("serial")
public class FilePathBean implements Serializable{

	/** key值 **/
	private String key;
	/** 源文件夹 **/
	private String sourceDir;
	/** 目标文件夹 **/
	private String[] destDir;
	
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getSourceDir() {
		return sourceDir;
	}
	public void setSourceDir(String sourceDir) {
		this.sourceDir = sourceDir;
	}
	public String[] getDestDir() {
		return destDir;
	}
	public void setDestDir(String[] destDir) {
		this.destDir = destDir;
	}
	
	public FilePathBean() {
	}
	
	public FilePathBean(String key, String sourceDir, String[] destDir) {
		super();
		this.key = key;
		this.sourceDir = sourceDir;
		this.destDir = destDir;
	}
	
}
