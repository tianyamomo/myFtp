package cn.gavin.common.email;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.MessagingException;

/****
 * 邮件发送的入口
 * @version 0.1
 * @author gavin.jiang
 * @date 2017/03/16
 */
public class EmailSendWorker {

	public EmailSendWorker(){
		
	}

	/***
	 * 通过解析configPropertyfileRelativePathName获取发送邮件时所需的信息,调用EMAILS的方法发送邮件
	 * @param subject 邮件主题
	 * @param content 邮件内容
	 * @param configPropertyfileRelativePathName 邮件配置所在的文件相对路径
	 */
	public synchronized void send(String subject,StringBuffer content,String configPropertyfileRelativePathName){
		String host = null;
		String auth = "false";
		String emailSrc = null;
		String srcPasswd = null;
		String[] emailTo = null;
		String[] emailCC = null;
		String[] emailBCC = null;
		Properties p = new Properties();
		InputStream is = null;
		try{
			String path = System.getProperty("user.dir");
			is = new FileInputStream(path.replace("\\","/")+configPropertyfileRelativePathName);
			p.load(is);
			host = p.getProperty("host");
			auth = p.getProperty("auth");
			emailSrc = p.getProperty("emailSrc");
			srcPasswd = p.getProperty("srcPasswd");
			String to = p.getProperty("emailTo");
			String cc = p.getProperty("emailCC");
			String bcc = p.getProperty("emailBCC");
			if(null != to &&  (!to.equals(""))){
				emailTo = to.split(";");
			}
			if(null != cc && (!cc.equals(""))){
				emailCC = cc.split(";");
			}
			if(null != bcc && (!bcc.equals(""))){
				emailBCC = bcc.split(";");
			}
			EMAILS emails = new EMAILS(host,auth,emailSrc,srcPasswd);
			emails.send(emailTo, emailCC, emailBCC, subject, content.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**** 
	 * 传入发送Email所需要的数据，调用EMAILS发送邮件 
	 * @param host : the mail server host 
	 * @param auth : authentication,"false"||"true", default "false" 
	 * @param emailSrc : email from account 
	 * @param srcPasswd : email from account's password 
	 * @param emailTo :  send to 
	 * @param emailCC : copy to 
	 * @param emailBCC : BCC to 
	 * @param subject : email's subject 
	 * @param content : email's content 
	 * @return 异常
	 */
	public boolean sendMail(String host,String auth,String emailSrc,String srcPasswd,String[] emailTo,String[] emailCC,String[] emailBCC,String subject,StringBuffer content){
		boolean flag = false;
		if(!auth.equalsIgnoreCase("true")){
			auth = "false";
		}
		EMAILS emails = new EMAILS(host,auth,emailSrc,srcPasswd);
		try {
			flag = emails.send(emailTo, emailCC, emailBCC, subject, content.toString());
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
}
