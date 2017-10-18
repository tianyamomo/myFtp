/**
 * 
 *ValueChangeListener.java 
 *
 *@author gavin.jiang
 *@email 820367229@qq.com
 *@date 2017-5-8
 *@version 0.1
 */
package cn.gavin.common.listener;

import java.util.EventListener;

/**
 * @author gavin.jiang
 *
 */
public interface ValueChangeListener extends EventListener{
	
	 public abstract void performed(ValueChangeEvent e);
}
