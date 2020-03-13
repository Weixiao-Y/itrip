package cn.weixiao.itrip.dao;

import cn.weixiao.itrip.pojo.entity.LableDic;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <b>爱旅行-系统字典信息数据持久层接口</b>
 * @author Weixiao
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
public interface LableDicDao {

	/**
	 * <b>按照查询条件查询信息列表</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<LableDic> findListByQuery(LableDic query) throws Exception;
}
