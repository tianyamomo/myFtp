package cn.gavin.common.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import cn.gavin.common.dao.BaseDao;

/***
 * 所有Dao实现类的基类
 * @version 0.1
 * @author gavin.jiang
 * @date 2017/03/16
 *
 * @param <T> 实体类类型
 * @param <PK> 实体类主键的类型
 */
public class BaseDaoImpl<T, PK extends Serializable> implements BaseDao<T, PK> {
	
	public String nameSpace;
	
	public BaseDaoImpl(String nameSpace){
		this.nameSpace = nameSpace;
	}
	
	
	
	public BaseDaoImpl(){
		
	}


	@Override
	public int insert(T obj) throws Exception {
		return 0;
	}


	@Override
	public int update(T obj) throws Exception {
		return 0;
	}


	@Override
	public int delete(PK key) throws Exception {
		return 0;
	}


	@Override
	public List<T> selectAll() throws Exception {
		return null;
	}


	@Override
	public List<T> selectList(Map param) throws Exception {
		return null;
	}


	@Override
	public T selectPK(PK key) throws Exception {
		return null;
	}
}
