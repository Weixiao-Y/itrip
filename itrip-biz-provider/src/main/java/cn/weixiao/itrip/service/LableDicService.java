package cn.weixiao.itrip.service;

import cn.weixiao.itrip.pojo.entity.LableDic;

import java.util.List;

/**
 * <b>爱旅行-系统字典信息业务层接口</b>
 * @author Weixiao
 * @version 1.0.0
 * @since 1.0.0
 */
public interface LableDicService {

	/**
	 * <b>根据查询获得信息列表</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<LableDic> getListByQuery(LableDic query) throws Exception;
}
