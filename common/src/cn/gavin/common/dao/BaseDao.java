package cn.gavin.common.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/****
 * 所有Dao接口的基类
 * 
 * @version 0.1
 * @author gavin.jiang
 * @date 2017/03/16
 *
 * @param <T> 实体类类型
 * @param <PK> 实体类主键的类型
 */
public interface BaseDao<T,PK extends Serializable> {

	/***
	 * insert obj 
	 * @param obj 实体类
	 * @return 返回插入的数据的条数
	 * @throws Exception 异常
	 */
	public int insert(T obj)throws Exception;
	
	/***
	 * 更新 obj
	 * @param obj 实体类
	 * @return 返回更新的数据条数
	 * @throws Exception 异常
	 */
	public int update(T obj) throws Exception;
	
	/****
	 * 删除
	 * @param key 主键
	 * @return 返回删除的数据条数
	 * @throws Exception 异常
	 */
	public int delete(PK key) throws Exception;
	
	/****
	 * 查询所有的
	 * @return 返回包含所有数据的List
	 * @throws Exception 异常
	 */
	public List<T> selectAll()throws Exception;
	
	/****
	 * 查询符合条件的数据
	 * @param param 查询的参数
	 * @return 返回包含符合条件数据的List
	 * @throws Exception 异常
	 */
	public List<T> selectList(Map param) throws Exception;
	
	/****
	 * 查询
	 * @param key 主键
	 * @return 返回符合key值的T 类型对象
	 * @throws Exception 异常
	 */
	public T selectPK(PK key) throws Exception;
}
