/**
 * 
 *EventProducer.java 
 *
 *@author gavin.jiang
 *@email 820367229@qq.com
 *@date 2017-5-8
 *@version 0.1
 */
package cn.gavin.common.listener;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author gavin.jiang
 * 
 */
public class EventProducer {
	
	private static ExecutorService executor = Executors.newFixedThreadPool(3);
	private int value;

	public int getValue() {
		return value;
	}

	public void setValue(int newValue) {
		if (value != newValue) {
			value = newValue;
			ListenerRegister register = new ListenerRegister();
			ValueChangeEvent event = new ValueChangeEvent(this, value);
			ValueChangeListener listener = new EventConsumer();
			register.setEvt(event);
			register.setListener(listener);
			Future<String> future = executor.submit(register);
			try {
				System.out.println(future.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
	}

}
