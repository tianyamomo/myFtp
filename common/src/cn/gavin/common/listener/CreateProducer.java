/**
 * 
 *CreateProducer.java 
 *
 *@author gavin.jiang
 *@email 820367229@qq.com
 *@date 2017-5-9
 *@version 0.1
 */
package cn.gavin.common.listener;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author gavin.jiang
 * 
 */
public class CreateProducer implements Callable<String> {

	private int val;

	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.concurrent.Callable#call()
	 */
	@Override
	public String call() throws Exception {

		Thread.currentThread().sleep(100);
		return "create : " + this.getVal();
	}

}
