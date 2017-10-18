/**
 * 
 *EventConsumer.java 
 *
 *@author gavin.jiang
 *@email 820367229@qq.com
 *@date 2017-5-8
 *@version 0.1
 */
package cn.gavin.common.listener;

import java.util.Date;

/**
 * @author gavin.jiang
 * 
 */
public class EventConsumer implements ValueChangeListener {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.gavin.common.listener.ValueChangeListener#performed(cn.gavin.common
	 * .listener.ValueChangeEvent)
	 */
	@Override
	public void performed(ValueChangeEvent e) {
		System.out.println(new Date().getTime() + "  :  value changed, new value = " + e.getValue());
	}
}
