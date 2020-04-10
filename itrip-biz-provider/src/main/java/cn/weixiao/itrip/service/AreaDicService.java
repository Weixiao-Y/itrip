package cn.weixiao.itrip.service;

import cn.weixiao.itrip.pojo.entity.AreaDic;

import java.util.List;
import java.util.Map;

/**
 * <b>爱旅行-区域字典信息业务层接口</b>
 * @author Weixiao
 * @version 1.0.0
 * @since 1.0.0
 */
public interface AreaDicService {
	/**
	 * <b>根据查询获得信息列表</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<AreaDic> getListByQuery(AreaDic query) throws Exception;

	/**
	 * <b>根据map查询信息列表</b>
	 * @param param
	 * @return
	 * @throws Exception
	 */
	List<AreaDic> getListByMap(Map param) throws Exception;
}
