package cn.weixiao.itrip.service.impl;

import cn.weixiao.itrip.dao.HotelOrderDao;
import cn.weixiao.itrip.pojo.entity.HotelOrder;
import cn.weixiao.itrip.pojo.vo.ItripListHotelOrderVO;
import cn.weixiao.itrip.pojo.vo.Page;
import cn.weixiao.itrip.pojo.vo.PersonalHotelOrderVO;
import cn.weixiao.itrip.pojo.vo.PersonalOrderRoomVO;
import cn.weixiao.itrip.service.HotelOrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ParameterMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <b>爱旅行-酒店订单业务层接口</b>
 * @author Weixiao
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("hotelOrderService")
@Transactional
public class HotelOrderServiceImpl implements HotelOrderService {
	@Autowired
	private HotelOrderDao hotelOrderDao;

	/**
	 * <b>根据查询条件查询列表信息</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	 public List<HotelOrder> getHotelListByQuery(HotelOrder query) throws Exception {
		List<HotelOrder> hotelOrderList = hotelOrderDao.findHotelOrderListByQuery(query);
		 if (hotelOrderList != null) {
			 return hotelOrderList;
		 }
		 return new ArrayList<HotelOrder>();
	 }

	/**
	 * <b>保存酒店订单</b>
	 * @param hotelOrder
	 * @return
	 * @throws Exception
	 */
	 public boolean saveHotelOrder(HotelOrder hotelOrder) throws Exception {
		int count = hotelOrderDao.saveHotelOrder(hotelOrder);
		if (count > 0) {
			return true;
		}
		return false;
	 }

	/**
	 * <b>更新订单信息</b>
	 * @param hotelOrder
	 * @return
	 * @throws Exception
	 */
	 public boolean updateHotelOrder(HotelOrder hotelOrder) throws Exception {
		 int count = hotelOrderDao.updateHotelOrder(hotelOrder);
		 if (count > 0) {
			 return true;
		 }
		 return false;
	 }

	/**
	 * <b>根据订单号查询订单</b>
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	 public HotelOrder getHotelOrderById(Long orderId) throws Exception {
		HotelOrder query = new HotelOrder();
		query.setId(orderId);

		List<HotelOrder> hotelOrderList = hotelOrderDao.findHotelOrderListByQuery(query);
		if (hotelOrderList != null && hotelOrderList.size() > 0) {
			return hotelOrderList.get(0);
		}
		return null;
	 }

	/**
	 * <b>根据订单编号查询订单</b>
	 * @param orderNo
	 * @return
	 * @throws Exception
	 */
	 public HotelOrder getHotelOrderByNo(String orderNo) throws Exception {
		 HotelOrder query = new HotelOrder();
		 query.setOrderNo(orderNo);

		 List<HotelOrder> hotelOrderList = hotelOrderDao.findHotelOrderListByQuery(query);
		 if (hotelOrderList != null && hotelOrderList.size() > 0) {
			 return hotelOrderList.get(0);
		 }
		 return null;
	 }

	/**
	 * <b>根据订单编号查询房型相关信息</b>
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	public PersonalOrderRoomVO getPersonalOrderRoomInfo(Long orderId) throws Exception {

		return hotelOrderDao.getHotelOrderRoomInfoById(orderId);
	}

	/**
	 * <b>根据param, pageNo, pageSize查询分页对象</b>
	 * @param param
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public Page queryOrderPageByMap(Map<String, Object> param, Integer pageNo, Integer pageSize) throws Exception {
//		// 需要分页查询，首先调用Mybatis PageHelper拦截器，说明需要分页查询
//		PageHelper.startPage(pageNo, pageSize);
//		// 直接查询列表，此时的列表已经经过分页后的结果
//		List<ItripListHotelOrderVO> orderVOList = hotelOrderDao.getOrderListByMap(param);
//		PageInfo<ItripListHotelOrderVO> pageInfo = new PageInfo<ItripListHotelOrderVO>();
//		// 通过PageInfo对象提取分页信息
//		// 总数量
		Integer total = hotelOrderDao.getOrderCountByMap(param);
		Page page = new Page(pageNo, pageSize, total);
		param.put("beginPos", page.getBeginPos());
		param.put("pageSize", page.getPageSize());
		List<ItripListHotelOrderVO> itripListHotelOrderVOList = hotelOrderDao.getOrderListByMap(param);
		page.setRows(itripListHotelOrderVOList);
		return page;
	}
}
