package cn.weixiao.itrip.service.impl;

import cn.weixiao.itrip.dao.ItripImageDao;
import cn.weixiao.itrip.pojo.entity.ItripImage;
import cn.weixiao.itrip.service.ItripImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>爱旅行-图片业务层接口实现类</b>
 * @author Weixiao
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("itripImageService")
@Transactional
public class ItripImageServiceImpl implements ItripImageService {
	@Autowired
	private ItripImageDao itripImageDao;
		/**
		 * <b>根据条件查询列表</b>
		 * @param query
		 * @return
		 * @throws Exception
		 */
	public 	List<ItripImage> getImageListByQuery(ItripImage query) throws Exception {
	List<ItripImage> itripImageList = itripImageDao.findListByQuery(query);
	if (itripImageList != null) {
		return itripImageList;
	}
	return new ArrayList<ItripImage>();
	}
}
