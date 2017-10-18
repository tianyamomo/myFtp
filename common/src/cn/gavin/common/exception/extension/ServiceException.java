package cn.gavin.common.exception.extension;

import cn.gavin.common.exception.BaseException;

/*****
 * 所有Service层使用的Exception类
 * @version 0.1
 * @author gavin.jiang
 * @date 2017/03/16
 */
public class ServiceException extends BaseException {

	private static final long serialVersionUID = 1L;

	public ServiceException() {
		super();
	}

	public ServiceException(String key, Object args, Throwable cause) {
		super(key, args, cause);
	}

	public ServiceException(String key, Object args) {
		super(key, args);
	}

	public ServiceException(String key, Object[] args, Throwable cause) {
		super(key, args, cause);
	}

	public ServiceException(String key, Object[] args) {
		super(key, args);
	}

	public ServiceException(String key, Throwable cause) {
		super(key, cause);
	}

	public ServiceException(String key) {
		super(key);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}
}
