package cn.weixiao.itrip.dao;

import cn.weixiao.itrip.pojo.entity.AreaDic;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <b>爱旅行-酒店区域信息数据持久层接口</b>
 * @author Weixiao
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
public interface AreaDicDao {

	/**
	 * <b>按照查询条件查询信息列表</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<AreaDic> findListByQuery(AreaDic query) throws Exception;

	/**
	 * <b>根据map集合查询列表</b>
	 * @param param
	 * @return
	 * @throws Exception
	 */
	List<AreaDic> getListByMap(Map param) throws Exception;
}
