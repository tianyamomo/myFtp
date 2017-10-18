package cn.gavin.common.exception.extension;

import cn.gavin.common.exception.BaseException;

/****
 * 参数异常类
 * 
 * @version 0.1
 * @author gavin.jiang
 * @date 2017/03/16
 */
public class ParameterException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ParameterException() {
		super();
	}

	public ParameterException(String key, Object args, Throwable cause) {
		super(key, args, cause);
	}

	public ParameterException(String key, Object args) {
		super(key, args);
	}

	public ParameterException(String key, Object[] args, Throwable cause) {
		super(key, args, cause);
	}

	public ParameterException(String key, Object[] args) {
		super(key, args);
	}

	public ParameterException(String key, Throwable cause) {
		super(key, cause);
	}

	public ParameterException(String key) {
		super(key);
	}

	public ParameterException(Throwable cause) {
		super(cause);
	}

	
	
}
