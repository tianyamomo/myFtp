/**
 * 
 *ChildThreadOFFirstThread.java 
 *
 *@author gavin.jiang
 *@email 820367229@qq.com
 *@date 2017-5-18
 *@version 0.1
 */
package cn.gavin.test.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;

/**
 * @author gavin.jiang
 * 
 */
public class ChildThreadOFFirstThread implements Callable<String> {

	private String str;

	private int cnt;

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public ChildThreadOFFirstThread(String str, int cnt) {
		super();
		this.str = str;
		this.cnt = cnt;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.concurrent.Callable#call()
	 */
	@Override
	public String call() throws Exception {
		System.out.println("secondThread  start : " +  new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
		Thread.currentThread().sleep(300);
		String s = str + cnt * Thread.currentThread().getId();
		System.out.println("secondThread : " + Thread.currentThread().getId() +"  with first Thread ： "+ str);
		System.out.println("firstThread  end : " +  new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
		return s;
	}

}
