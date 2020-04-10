package cn.weixiao.itrip.service;

import cn.weixiao.itrip.pojo.entity.HotelOrder;
import cn.weixiao.itrip.pojo.vo.Page;
import cn.weixiao.itrip.pojo.vo.PersonalHotelOrderVO;
import cn.weixiao.itrip.pojo.vo.PersonalOrderRoomVO;

import java.util.List;
import java.util.Map;

/**
 * <b>爱旅行-酒店订单业务层接口</b>
 * @author Weixiao
 * @version 1.0.0
 * @since 1.0.0
 */
public interface HotelOrderService {
	/**
	 * <b>根据查询条件查询列表信息</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<HotelOrder> getHotelListByQuery(HotelOrder query) throws Exception;

	/**
	 * <b>保存酒店订单</b>
	 * @param hotelOrder
	 * @return
	 * @throws Exception
	 */
	boolean saveHotelOrder(HotelOrder hotelOrder) throws Exception;

	/**
	 * <b>更新订单信息</b>
	 * @param hotelOrder
	 * @return
	 * @throws Exception
	 */
	boolean updateHotelOrder(HotelOrder hotelOrder) throws Exception;

	/**
	 * <b>根据订单号查询订单</b>
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	HotelOrder getHotelOrderById(Long orderId) throws Exception;

	/**
	 * <b>根据订单编号查询订单</b>
	 * @param orderNo
	 * @return
	 * @throws Exception
	 */
	HotelOrder getHotelOrderByNo(String orderNo) throws Exception;

	/**
	 * <b>根据订单编号查询房型相关信息</b>
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	PersonalOrderRoomVO getPersonalOrderRoomInfo(Long orderId) throws Exception;

	/**
	 * <b>根据param, pageNo, pageSize查询分页对象</b>
	 * @param param
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	Page queryOrderPageByMap(Map<String, Object> param, Integer pageNo, Integer pageSize) throws Exception;
}
