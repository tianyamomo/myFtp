package cn.gavin.common.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import cn.gavin.common.service.BaseService;


/****
 * 所有service层service实现类的基类
 * @version 0.1
 * @author gavin.jiang
 * @date 2017/03/16
 * @param <T>  实体类类型
 * @param <PK> 实体类主键类型
 */
public class BaseServiceImpl<T, PK extends Serializable> implements BaseService<T, PK> {

	
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
