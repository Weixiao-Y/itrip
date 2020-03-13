package cn.weixiao.itrip.service.impl;

import cn.weixiao.itrip.dao.HotelDao;
import cn.weixiao.itrip.pojo.entity.Hotel;
import cn.weixiao.itrip.pojo.vo.SearchHotCityVO;
import cn.weixiao.itrip.service.HotelService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>爱旅行-酒店模块业务层接口实现类</b>
 * @author Weixiao
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("hotelService")
@Transactional
public class HotelServiceImpl implements HotelService {
	@Autowired
	private HotelDao hotelDao;
	/**
	 * <b>根据热门城市查询酒店</b>
	 * @param queryVO
	 * @return
	 * @throws Exception
	 */
	public List<Hotel> searchItripHotelListByHotCity(SearchHotCityVO queryVO) throws Exception {
		// 封装查询对象
		Hotel query = new Hotel();
		query.setCityId((long) queryVO.getCityId());

		// 为了获得前六条，可以使用分页查询来进行
		PageHelper.startPage(1, queryVO.getCount());
		List<Hotel> hotelList = hotelDao.findListByQuery(query);
		if (hotelList != null) {
			return hotelList;
		}
		return new ArrayList<Hotel>();
	}
}
