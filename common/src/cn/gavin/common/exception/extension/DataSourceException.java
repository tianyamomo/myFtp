package cn.gavin.common.exception.extension;

import cn.gavin.common.exception.BaseException;

/****
 * 所有的DataSource层使用这个Exception类
 * 
 * @version 0.1
 * @author gavin.jiang
 *
 */
public class DataSourceException extends BaseException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataSourceException() {
		super();
	}

	public DataSourceException(String key, Object args, Throwable cause) {
		super(key, args, cause);
	}

	public DataSourceException(String key, Object args) {
		super(key, args);
	}

	public DataSourceException(String key, Object[] args, Throwable cause) {
		super(key, args, cause);
	}

	public DataSourceException(String key, Object[] args) {
		super(key, args);
	}

	public DataSourceException(String key, Throwable cause) {
		super(key, cause);
	}

	public DataSourceException(String key) {
		super(key);
	}

	public DataSourceException(Throwable cause) {
		super(cause);
	}
}
