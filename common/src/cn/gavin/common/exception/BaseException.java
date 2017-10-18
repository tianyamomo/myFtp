package cn.gavin.common.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

/****
 * All Exception class should extends this class
 * 
 * @author gavin.jiang
 * @version 0.1
 * @date 2017/03/16
 */
public abstract class BaseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 异常抛出对象  **/
	protected Throwable rootCause;
	
	/** 异常对应的key **/
	private String messageKey;
	/** 异常key对应的消息串 **/
	private Object[] messageArgs;
	
	
	/**
	 * Constructor 
	 */
	public BaseException(){
		rootCause = null;
		messageKey="system.err.Unkonw";
		messageArgs = null;
	}
	
	/****
	 * Constructor
	 * @param cause Exception Object(异常对象)
	 */
	public BaseException(Throwable cause){
		rootCause = null;
		messageKey="system.err.Unkonw";
		messageArgs = null;
		rootCause = cause;
	}
	
	/**
	 * Constructor 
	 * @param key Exception key (异常的key)
	 */
	public BaseException(String key){
		this(key,(Throwable)null);
	}
	
	/*****
	 * Constructor 
	 * @param key Exception key (异常的key)
	 * @param cause  Exception Object(异常对象)
	 */
	public BaseException(String key,Throwable cause){
		rootCause = null;
		messageKey = key;
		rootCause = cause;
		messageArgs = null;
	}

	
	/****
	 * Constructor 
	 * @param key Exception key (异常的key)
	 * @param args Exception message(异常的消息)
	 */
	public BaseException(String key,Object[] args){
		this(key,args,null);
	}
	
	/**
	 * Constructor 
	 * @param key Exception key (异常的key)
	 * @param args Exception message(异常的消息)
	 */
	public BaseException(String key, Object args) {
		Object temp[] = new Object[] { args };
		rootCause = null;
		messageKey = key;
		messageArgs = temp;
	}
	
	
	/***
	 * Constructor
	 * @param key Exception key (异常的key)
	 * @param args Exception message(异常的消息)
	 * @param cause Exception Object(异常对象)
	 */
	public BaseException(String key,Object args,Throwable cause){
		Object []temp = new Object[]{args};
		rootCause = null;
		rootCause = cause;
		messageKey = key;
		messageArgs = temp;
	}
	
	
	/***
	 * Constructor
	 * @param key Exception key (异常的key)
	 * @param args Exception message(异常的消息)
	 * @param cause Exception Object(异常对象)
	 */
	public BaseException(String key,Object[] args,Throwable cause){
		rootCause = cause;
		messageKey = key;
		messageArgs = args;
	}
	
	/**
	 *获取异常key值 
	 *@return 消息主键
	 **/
	public String getMessageKey() {
		return messageKey;
	}

	/***
	 * 设置异常Key值
	 * @param messageKey 消息主键
	 */
	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

	/***
	 * 获取异常Key对应的消息
	 * @return 返回异常的消息
	 */
	public Object[] getMessageArgs() {
		return messageArgs;
	}

	/***
	 * 
	 * @param messageArgs 异常消息
	 */
	public void setMessageArgs(Object[] messageArgs) {
		this.messageArgs = messageArgs;
	}

	/***
	 * 获取异常对象
	 * @return Throwable对象
	 */
	public Throwable getRootCause() {
		return rootCause;
	}

	/***
	 * 打印异常
	 */
	public void printStackTrace(){
		printStackTrace(System.err);
	}
	
	/***
	 * 打印
	 * @param ostream 打印流
	 */
	public void printStackTrace(PrintStream ostream){
		printStackTrace(new PrintWriter(ostream));
	}
	
	/***
	 * 打印异常消息
	 * @param writer 打印
	 */
	public void printStackTrace(PrintWriter writer){
		super.printStackTrace(writer);
		if (getRootCause() != null)
			getRootCause().printStackTrace(writer);
		writer.flush();
	}
	
}
