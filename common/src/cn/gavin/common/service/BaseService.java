package cn.gavin.common.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/*****
 * 所有Service层Service的基类
 * 
 * @version 0.1
 * @author gavin.jiang
 * @date 2017/03/16
 * @param <T> 实体类类型
 * @param <PK> 实体类主键类型
 */
public interface BaseService<T,PK extends Serializable> {

	/****
	 * 插入数据
	 * @param obj 实体类
	 * @return 插入数据的条数
	 * @throws Exception 异常
	 */
	public int insert(T obj)throws Exception;
	
	/***
	 * 更新数据
	 * @param obj 实体类
	 * @return 更新数据的条数
	 * @throws Exception 异常
	 */
	public int update(T obj) throws Exception;
	
	/****
	 * 删除数据
	 * @param key 主键
	 * @return 删除数据的条数
	 * @throws Exception 异常
	 */
	public int delete(PK key) throws Exception;
	
	/***
	 * 查询所有的数据
	 * @return 所有数据的List
	 * @throws Exception  异常
	 */
	public List<T> selectAll()throws Exception;
	
	/*****
	 * 查询符合条件的数据
	 * @param param 查询时需要的参数
	 * @return  符合条件的数据的List
	 * @throws Exception 异常
	 */
	public List<T> selectList(Map param) throws Exception;
	
	/****
	 * 根据key值来查询数据
	 * @param key 主键
	 * @return 返回符合条件的T 实体类
	 * @throws Exception 异常
	 */
	public T selectPK(PK key) throws Exception;
}
