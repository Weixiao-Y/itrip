package cn.weixiao.itrip.service.impl;

import cn.weixiao.itrip.dao.LableDicDao;
import cn.weixiao.itrip.pojo.entity.LableDic;
import cn.weixiao.itrip.service.LableDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>爱旅行-系统字典信息业务层接口实现类</b>
 * @author Arthur
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("lableDicService")
@Transactional
public class LableDicServiceImpl implements LableDicService {
	@Autowired
	private LableDicDao lableDicDao;

	/**
	 * <b>根据查询获得信息列表</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public List<LableDic> getListByQuery(LableDic query) throws Exception {
		// 通过数据持久层查询结果
		List<LableDic> areaDicList = lableDicDao.findListByQuery(query);

		if (areaDicList != null) {
			return areaDicList;
		}

		return new ArrayList<LableDic>();
	}
}
