package cn.weixiao.itrip.service;

import cn.weixiao.itrip.pojo.entity.Hotel;
import cn.weixiao.itrip.pojo.vo.SearchHotCityVO;

import java.util.List;

/**
 * <b>爱旅行-酒店模块业务层接口</b>
 * @author Weixiao
 * @version 1.0.0
 * @since 1.0.0
 */
public interface HotelService {

	/**
	 * <b>根据热门城市查询酒店</b>
	 * @param queryVO
	 * @return
	 * @throws Exception
	 */
	List<Hotel> searchItripHotelListByHotCity(SearchHotCityVO queryVO) throws Exception;
}
