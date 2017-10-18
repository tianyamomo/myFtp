package cn.gavin.common.ftp;

import java.util.concurrent.Callable;


/****
 * 
 * FTP文件传输的线程
 * 
 * @version 0.1
 * @author gavin.jiang
 * @date 2017/03/16
 */
public class FTPThread implements Callable<String>{

	private FTPBean ftpBean;
	
	public FTPThread(FTPBean ftpBean) {
		super();
		this.ftpBean = ftpBean;
	}


/*	@Override
	public void run() {
		synchronized(ftpBean){
			String protocol = ftpBean.getProtocol();
			if(protocol.equalsIgnoreCase("SFTP")){
				SFTPMain.sftpDownload(ftpBean.getUrl(), ftpBean.getPort(), ftpBean.getUserName(), ftpBean.getPassWord(), ftpBean.getSrcPath(), ftpBean.getDestPath(), ftpBean.getBakPath());
			}else{
				FTPMain.ftp(ftpBean.getUrl(), ftpBean.getPort(), ftpBean.getUserName(), ftpBean.getPassWord(), ftpBean.getSrcPath(), ftpBean.getDestPath(), ftpBean.getBakPath());
			}
		}
	}*/


	@Override
	public String call() throws Exception {
		synchronized(ftpBean){
			String protocol = ftpBean.getProtocol();
			if(protocol.equalsIgnoreCase("SFTP")){
				SFTPMain.sftpDownload(ftpBean.getUrl(), ftpBean.getPort(), ftpBean.getUserName(), ftpBean.getPassWord(), ftpBean.getSrcPath(), ftpBean.getDestPath(), ftpBean.getBakPath());
			}else{
				FTPMain.ftp(ftpBean.getUrl(), ftpBean.getPort(), ftpBean.getUserName(), ftpBean.getPassWord(), ftpBean.getSrcPath(), ftpBean.getDestPath(), ftpBean.getBakPath());
			}
		}
		return "success";
	}
	
}
