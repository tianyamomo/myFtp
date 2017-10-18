/**
 * 
 *FirstThreadProcess.java 
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author gavin.jiang
 * 
 */
public class FirstThreadProcess implements Callable<String> {

	private static ExecutorService executor = Executors.newFixedThreadPool(10);

	private String str;

	private int cnt;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.concurrent.Callable#call()
	 */
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

	public FirstThreadProcess(String str, int cnt) {
		super();
		this.str = str;
		this.cnt = cnt;
	}

	@Override
	public String call() throws Exception {
		System.out.println("firstThread start : " +  new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
		Thread.currentThread().sleep(2000);
		str = str + "_" + cnt+"_"+Thread.currentThread().getId();
		System.out.println("firstThread : " + Thread.currentThread().getId());
		System.out.println("firstThread end: " +  new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
		return str;
	}

	/*
	 * private String callChildThread() {
	 * 
	 * return ""; }
	 */

}
