/**
 * 
 *EntityPropertiesChangeListener.java 
 *
 *@author gavin.jiang
 *@email 820367229@qq.com
 *@date 2017-5-8
 *@version 0.1
 */
package cn.gavin.common.listener;

import java.util.EventObject;

/**
 * @author gavin.jiang
 *
 */
public class ValueChangeEvent extends EventObject {
	
	private int value;
	
	public ValueChangeEvent(Object source) {
		this(source,0);
	}

	public ValueChangeEvent(Object source,int value){
		super(source);
		this.value = value;
	}
	
	
	public int getValue(){
		return value;
	}
	
	public void setValue(int val){
		value = val;
	}
}
