package cn.gavin.common.bean;

import java.io.Serializable;

/***
 * BaseEntity : all entity classes's base class
 * @version 0.1
 * @author gavin.jiang
 * @date 2017/03/16
 */
public class BaseEntity implements Serializable {

	/*
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * class function description
	 */
	protected String classDescription;
	
	/**
	 * class name
	 */
	protected String className;
}
