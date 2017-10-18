package cn.gavin.common.email;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.log4j.Logger;

/****
 * mail发送的执行者
 * @version 0.1
 * @author gavin.jiang
 * @date 2017/03/16
 */
public class EMAILS {

	private static Logger log = Logger.getLogger(EMAILS.class);
	private String host;
	private String auth;
	private String username;
//	private String domainUser;
	private String password;
	
	/*****
	 * 邮件发送
	 * @param to : email to
	 * @param cc : email copy to 
	 * @param bcc : email BCC to 
	 * @param subject : email subject
	 * @param content : email content
	 * @return  ：返回true：返送成功  ； false： 发送失败
	 * @throws MessagingException 异常
	 */
	public boolean send(String[] to, String[] cc, String[] bcc, String subject, String content) throws MessagingException {
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", auth);
		Session s = Session.getInstance(props);
		s.setDebug(true);

		MimeMessage message = new MimeMessage(s);

		InternetAddress from = new InternetAddress(username);
		message.setFrom(from);
		InternetAddress[] Toaddress = new InternetAddress[to.length];
		for (int i = 0; i < to.length; i++)
			Toaddress[i] = new InternetAddress(to[i]);
		message.setRecipients(Message.RecipientType.TO, Toaddress);

		if (cc != null) {
			InternetAddress[] Ccaddress = new InternetAddress[cc.length];
			for (int i = 0; i < cc.length; i++)
				Ccaddress[i] = new InternetAddress(cc[i]);
			message.setRecipients(Message.RecipientType.CC, Ccaddress);
		}

		if (bcc != null) {
			InternetAddress[] Bccaddress = new InternetAddress[bcc.length];
			for (int i = 0; i < bcc.length; i++)
				Bccaddress[i] = new InternetAddress(bcc[i]);
			message.setRecipients(Message.RecipientType.BCC, Bccaddress);
		}
		try {
			message.setSubject(MimeUtility.encodeText(subject,"UTF-8","B"));
		} catch (UnsupportedEncodingException e) {
			log.error(" MimeMessage can't support this encoding, will use default encoding",e);
			message.setSubject(subject);
		}
		message.setSentDate(new Date());

		BodyPart mdp = new MimeBodyPart();
		try {
			mdp.setContent(new String(content.getBytes("GBK"),"GBK"), "text/html;charset=UTF-8");
		} catch (UnsupportedEncodingException e) {
			log.error(" MimeMessage can't support this encoding, will use default encoding",e);
			mdp.setContent(content, "text/html;charset=UTF-8");
		}
		Multipart mm = new MimeMultipart();
		mm.addBodyPart(mdp);
		message.setContent(mm);

		message.saveChanges();
		Transport transport = s.getTransport("smtp");
		transport.connect(host, username, password);
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
		return true;
	}
	
	public EMAILS (String host, String auth, String username, String password) {
		super();
		this.host = host;
		this.auth = auth;
		this.username = username;
		this.password = password;
	}
	
}
