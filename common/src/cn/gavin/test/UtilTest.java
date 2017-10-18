/**
 * 
 *UtilTest.java 
 *
 *@author gavin.jiang
 *@email 820367229@qq.com
 *@date 2017-6-5
 *@version 0.1
 */
package cn.gavin.test;

import cn.gavin.common.email.EmailSendWorker;

/**
 * @author gavin.jiang
 *
 */
public class UtilTest {

	
	public static void main(String[] args) {
	//// -----  part 1  -------	
	/*	String sql = "hello ? where ack = ?";
		StringBuffer strbuf = new StringBuffer(sql);
		for(int i = 0; i < 2; i++){
			strbuf.replace(strbuf.indexOf("?"), strbuf.indexOf("?")+1, "123"+i);
		}
		
		System.out.println(strbuf.toString());*/
		
		
		////------  part 2  ------
		
		EmailSendWorker worker = new EmailSendWorker();
		worker.sendMail("172.25.4.103", "false", "JasperReport@DCBTerminals.com", "", new String[]{"gavin.jiang@DCBTerminals.com"}, null, null, "测试test邮件", new StringBuffer("dkjfoeij江海东的测试kdjfljeiojd"));
	}
}
