package cn.weixiao.itrip.controller;

import cn.weixiao.itrip.base.controller.BaseController;
import cn.weixiao.itrip.base.enums.OrderStatusEnum;
import cn.weixiao.itrip.base.pojo.vo.ResponseDto;
import cn.weixiao.itrip.pojo.entity.*;
import cn.weixiao.itrip.pojo.vo.*;
import cn.weixiao.itrip.transport.*;
import cn.weixiao.itrip.util.DateUtil;
import cn.weixiao.itrip.util.HotelOrderCreator;
import com.ctc.wstx.sw.EncodingXmlWriter;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.support.PageJacksonModule;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.NewThreadAction;

import javax.servlet.http.Cookie;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <b>爱旅行-主业务酒店订单模块控制器</b>
 * @author Weixiao
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController("hotelOrderController")
@RequestMapping("/biz/api/hotelorder")
public class HotelOrderController extends BaseController {
	@Autowired
	private HotelTransport hotelTransport;
	@Autowired
	private HotelRoomTransport hotelRoomTransport;
	@Autowired
	private UserTransport userTransport;
	@Autowired
	private HotelOrderTransport hotelOrderTransport;
	@Autowired
	private OrderLinkUserTransport orderLinkUserTransport;

	/**
	 * <b>生成订单前，获得预定信息</b>
	 * @param validateRoomStoreVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/getpreorderinfo")
	public ResponseDto<Object> getPreOrderInfo(@RequestBody ValidateRoomStoreVO validateRoomStoreVO) throws Exception {
		RoomStoreVO roomStoreVO = new RoomStoreVO();

		// 根据 hotelId 查询对应的 Hotel 对象
		Hotel hotel = hotelTransport.getHotelById(validateRoomStoreVO.getHotelId());
		roomStoreVO.setHotelId(hotel.getId());
		roomStoreVO.setHotelName(hotel.getHotelName());

		// 根据 roomId 查询对应的 HotelRoom 对象
		HotelRoom hotelRoom = hotelRoomTransport.queryHotelRoomById(validateRoomStoreVO.getRoomId());
		roomStoreVO.setRoomId(hotelRoom.getId());
		roomStoreVO.setPrice(hotelRoom.getRoomPrice());

		// 根据入住时间和退房时间，查询该房间所剩房间数量
		int store = hotelRoomTransport.queryHotelRoomStoreByDate(validateRoomStoreVO);
		roomStoreVO.setStore(store);

		roomStoreVO.setCheckInDate(validateRoomStoreVO.getCheckInDate());
		roomStoreVO.setCheckOutDate(validateRoomStoreVO.getCheckOutDate());
		roomStoreVO.setCount(validateRoomStoreVO.getCount());
		return ResponseDto.success(roomStoreVO);
	}

	/**
	 * <b>生成订单</b>
	 * @param addHotelOrderVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/addhotelorder")
	public ResponseDto<Object> addHotelOrder(@RequestBody AddHotelOrderVO addHotelOrderVO) throws Exception {
		// 查询此时是否有房
		ValidateRoomStoreVO validateRoomStoreVO = new ValidateRoomStoreVO();
		BeanUtils.copyProperties(addHotelOrderVO, validateRoomStoreVO);
		int store = hotelRoomTransport.queryHotelRoomStoreByDate(validateRoomStoreVO);

		if (store >= addHotelOrderVO.getCount()) {
			// 有房的情况下，保存订单数据表
			// 通过 Cookie 获得当前登录对象
			String userCode = "";
			Cookie[] cookies = request.getCookies();
			for (Cookie  cookie : cookies) {
				if ("user".equals(cookie.getName())) {
					userCode = cookie.getValue();
				}
			}
			// 通过userCode获得userId
			User query = new User();
			query.setUserCode(userCode);
			List<User> userList = userTransport.getListByQuery(query);
			if (userList != null && userList.size() > 0) {
				User user = userList.get(0);
				HotelOrder hotelOrder = new HotelOrder();
				hotelOrder.setId(addHotelOrderVO.getId());
				hotelOrder.setUserId(user.getId());
				hotelOrder.setOrderType(addHotelOrderVO.getOrderType());
				hotelOrder.setHotelId(addHotelOrderVO.getHotelId());
				hotelOrder.setHotelName(addHotelOrderVO.getHotelName());
				hotelOrder.setRoomId(addHotelOrderVO.getRoomId());
				hotelOrder.setCount(addHotelOrderVO.getCount());
				hotelOrder.setCheckInDate(addHotelOrderVO.getCheckInDate());
				hotelOrder.setCheckOutDate(addHotelOrderVO.getCheckOutDate());
				hotelOrder.setNoticePhone(addHotelOrderVO.getNoticePhone());
				hotelOrder.setNoticeEmail(addHotelOrderVO.getNoticeEmail());
				hotelOrder.setSpecialRequirement(addHotelOrderVO.getSpecialRequirement());
				hotelOrder.setIsNeedInvoice(addHotelOrderVO.getIsNeedInvoice());
				hotelOrder.setInvoiceHead(addHotelOrderVO.getInvoiceHead());
				hotelOrder.setInvoiceType(addHotelOrderVO.getInvoiceType());
				hotelOrder.setCreatedBy(user.getId());
				hotelOrder.setCreationDate(new Date());
				// 预定天数
				Integer days = DateUtil.getBetweenDates(
						addHotelOrderVO.getCheckInDate(), addHotelOrderVO.getCheckOutDate()
				).size()-1;
				if (days <= 0) {
					return ResponseDto.failure("退房日期必须大于入住日期");
				}
				hotelOrder.setBookingDays(days);
				String orderNo = HotelOrderCreator.createHotelOrderNo(addHotelOrderVO.getHotelId(), addHotelOrderVO.getRoomId());
				// 订单编号
				hotelOrder.setOrderNo(orderNo);
				// 交易编号
				hotelOrder.setTradeNo(orderNo);
				// 订单状态
				hotelOrder.setOrderStatus(OrderStatusEnum.ORDER_STATUS_PREPAY.getCode());
				// 订单价格
				HotelRoom hotelRoom = hotelRoomTransport.queryHotelRoomById(addHotelOrderVO.getRoomId());
				hotelOrder.setPayAmount(addHotelOrderVO.getCount() * hotelRoom.getRoomPrice());
				// 添加联系人信息
				List<UserLinkUser> userLinkUserList = addHotelOrderVO.getLinkUser();
				StringBuffer sb = new StringBuffer();
				for (UserLinkUser userLinkUser: userLinkUserList) {
					sb.append(userLinkUser.getLinkUserName() + ",");
				}
				hotelOrder.setLinkUserName(sb.toString().substring(0, sb.toString().length() -1));
				// 保存订单
				hotelOrderTransport.saveHotelOrder(hotelOrder);
				// 获得 HotelOrder 对象的 id 和 OrderId 添加为 Map 集合
				HotelOrder order = hotelOrderTransport.getHotelOrderByNo(orderNo);

				Map<String, Object> resultMap = new HashMap<String, Object>();
				resultMap.put("id", order.getId());
				resultMap.put("orderNo", order.getOrderNo());
				// 返回该 Map 集合
				return ResponseDto.success(resultMap);
			}
		}
		return ResponseDto.success(new HashMap<String, Object>());
	}

	/**
	 * <b>根据订单ID查看个人订单详情</b>
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/getpersonalorderinfo/{orderId}")
	public ResponseDto<Object> getPersonalOrderInfo(@PathVariable("orderId") String orderId) throws Exception {
		HotelOrder hotelOrder = hotelOrderTransport.getHotelOrderById(Long.valueOf(orderId));
		if (hotelOrder != null) {
			PersonalHotelOrderVO personalHotelOrderVO = new PersonalHotelOrderVO();
			personalHotelOrderVO.setId(hotelOrder.getId());
			personalHotelOrderVO.setBookType(hotelOrder.getBookType());
			personalHotelOrderVO.setCreationDate(hotelOrder.getCreationDate());
			personalHotelOrderVO.setOrderNo(hotelOrder.getOrderNo());
			// 查询预定房间信息
			HotelRoom hotelRoom = hotelRoomTransport.queryHotelRoomById(hotelOrder.getRoomId());
			if (hotelRoom != null) {
				Integer orderStatus = hotelOrder.getOrderStatus();
				personalHotelOrderVO.setOrderStatus(orderStatus);
				personalHotelOrderVO.setOrderProcess(null);
				personalHotelOrderVO.setProcessNode(null);
				personalHotelOrderVO.setPayAmount(hotelOrder.getPayAmount());
				personalHotelOrderVO.setRoomPayType(hotelOrder.getPayType());
				personalHotelOrderVO.setNoticePhone(hotelOrder.getNoticePhone());
				return ResponseDto.success(personalHotelOrderVO);
			}
		}
		return ResponseDto.success(new PersonalHotelOrderVO());
	}

	/**
	 * <b>根据订单ID查看个人订单详情-房型相关信息</b>
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/getpersonalorderroominfo/{orderId}")
	public ResponseDto<Object> getPersonalOrderRoomInfo(@PathVariable("orderId") String orderId) throws Exception {
		PersonalOrderRoomVO personalOrderRoomVO = hotelOrderTransport.getPersonalOrderRoomInfo(Long.valueOf(orderId));
		if (personalOrderRoomVO != null) {
			return ResponseDto.success(personalOrderRoomVO);
		}
		return ResponseDto.success(new PersonalOrderRoomVO());
	}

	/**
	 * <b>根据订单ID获取订单信息</b>
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/queryOrderById/{orderId}")
	public ResponseDto<Object> queryOrderById(@PathVariable("orderId") Long orderId) throws Exception {
		HotelOrder hotelOrder = hotelOrderTransport.getHotelOrderById(orderId);
		ModifyHotelOrderVO modifyHotelOrderVO = new ModifyHotelOrderVO();
		BeanUtils.copyProperties(hotelOrder, modifyHotelOrderVO);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("orderId", hotelOrder.getId());
		List<OrderLinkUserVo> orderLinkUserVoList = orderLinkUserTransport.getOrderLinkUserListByParam(param);
		modifyHotelOrderVO.setItripOrderLinkUserList(orderLinkUserVoList);
		if (modifyHotelOrderVO != null) {
			return ResponseDto.success(modifyHotelOrderVO);
		}
		return ResponseDto.success(new ModifyHotelOrderVO());
	}

	/**
	 * <b>根据个人订单列表，并分页显示</b>
	 * @param itripSearchOrderVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getpersonalorderlist")
	public ResponseDto<Object> getPersonalOrderList(@RequestBody ItripSearchOrderVO itripSearchOrderVO) throws Exception {
		// 通过 Cookie 获得当前登录对象
		String userCode = "";
		Cookie[] cookies = request.getCookies();
		for (Cookie  cookie : cookies) {
			if ("user".equals(cookie.getName())) {
				userCode = cookie.getValue();
			}
		}
		// 通过userCode获得userId
		User query = new User();
		query.setUserCode(userCode);
		List<User> userList = userTransport.getListByQuery(query);
		if (userList != null && userList.size() > 0) {
			User user = userList.get(0);
			Map<String, Object> param = new HashMap<String, Object>();
			Integer orderType = itripSearchOrderVO.getOrderType();
			Integer orderStatus = itripSearchOrderVO.getOrderStatus();
			String OrderNo = itripSearchOrderVO.getOrderNo();
			String linkUserName = itripSearchOrderVO.getLinkUserName();
			if (orderType == -1) {
				orderType=null;
			}
			if (orderStatus == -1) {
				orderStatus=null;
			}
			if (OrderNo == "") {
				OrderNo=null;
			}
			if (linkUserName == "") {
				linkUserName=null;
			}
			param.put("orderType", orderType);
			param.put("orderStatus",  orderStatus);
			param.put("userId", user.getId());
			param.put("orderNo",  OrderNo);
			param.put("linkUserName", linkUserName);
			SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
			if (itripSearchOrderVO.getStartDate() != null) {
				param.put("startDate", dateFormat.format(itripSearchOrderVO.getStartDate()));
			}
			if (itripSearchOrderVO.getEndDate() != null) {
				param.put("endDate", dateFormat.format(  itripSearchOrderVO.getEndDate()));
			}
			Integer pageNo = itripSearchOrderVO.getPageNo();
			if (pageNo == null || "".equals(pageNo)) {
				pageNo = 1;
			}
			Integer pageSize = itripSearchOrderVO.getPageSize();
			if (pageSize == null || "".equals(pageSize)) {
				pageNo = 6;
			}
			Page page = hotelOrderTransport.queryOrderPageByMap(param, pageNo, pageSize);
			if (page != null) {
				return ResponseDto.success(page);
			}
		}
		return ResponseDto.success(new Page<ItripListHotelOrderVO>());
	}
}
