package cn.weixiao.itrip.service;

import cn.weixiao.itrip.pojo.entity.Hotel;
import cn.weixiao.itrip.pojo.vo.HotelVO;
import cn.weixiao.itrip.pojo.vo.Page;
import cn.weixiao.itrip.pojo.vo.SearchHotCityVO;
import cn.weixiao.itrip.pojo.vo.SearchHotelVO;

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
	List<HotelVO> searchItripHotelListByHotCity(SearchHotCityVO queryVO) throws Exception;

	/**
	 * <b>根据主键查询对象信息</b>
	 * @param hotelId
	 * @return
	 * @throws Exception
	 */
	Hotel getHotelById(Long hotelId) throws Exception;

	/**
	 * <b>分页查询酒店信息</b>
	 * @param searchHotelVO
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	Page searchItripHotelPage(SearchHotelVO searchHotelVO, Integer pageNo, Integer pageSize) throws Exception;
}
