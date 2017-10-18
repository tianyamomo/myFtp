package cn.gavin.common.exception.extension;

import cn.gavin.common.exception.BaseException;

/*****
 *所有Dao层的Exception使用这个类
 * 
 * @version 0.1
 * @author gavin.jiang
 */
public class DaoException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DaoException() {
		super();
	}

	public DaoException(String key, Object args, Throwable cause) {
		super(key, args, cause);
	}

	public DaoException(String key, Object args) {
		super(key, args);
	}

	public DaoException(String key, Object[] args, Throwable cause) {
		super(key, args, cause);
	}

	public DaoException(String key, Object[] args) {
		super(key, args);
	}

	public DaoException(String key, Throwable cause) {
		super(key, cause);
	}

	public DaoException(String key) {
		super(key);
	}

	public DaoException(Throwable cause) {
		super(cause);
	}

	
	
}
