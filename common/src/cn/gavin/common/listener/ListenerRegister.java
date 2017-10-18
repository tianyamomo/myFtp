/**
 * 
 *ListenerRegister.java 
 *
 *@author gavin.jiang
 *@email 820367229@qq.com
 *@date 2017-5-8
 *@version 0.1
 */
package cn.gavin.common.listener;

import java.util.Vector;
import java.util.concurrent.Callable;

/**
 * @author gavin.jiang
 *
 */
public class ListenerRegister  implements Callable<String>{
	
	private ValueChangeEvent evt;
	private ValueChangeListener listener;

	public ValueChangeEvent getEvt() {
		return evt;
	}

	public void setEvt(ValueChangeEvent evt) {
		this.evt = evt;
	}

	public ValueChangeListener getListener() {
		return listener;
	}

	public void setListener(ValueChangeListener listener) {
		this.listener = listener;
	}

	@Override
	public String call() throws Exception {
		try{
			Thread.currentThread().sleep(1000);
			listener.performed(evt);
			return "success";
		}catch(Exception e){
			e.printStackTrace();
			return "failed";
		}
	}  
}
