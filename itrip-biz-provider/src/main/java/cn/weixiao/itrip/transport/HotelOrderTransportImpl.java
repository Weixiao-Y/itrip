package cn.weixiao.itrip.transport;

import cn.weixiao.itrip.pojo.entity.HotelOrder;
import cn.weixiao.itrip.pojo.vo.Page;
import cn.weixiao.itrip.pojo.vo.PersonalHotelOrderVO;
import cn.weixiao.itrip.pojo.vo.PersonalOrderRoomVO;
import cn.weixiao.itrip.service.HotelOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <b>爱旅行-酒店订单传输层接口实现类</b>
 * @author Weixiao
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController("hotelOrderTransport")
@RequestMapping("/hotelorder/trans")
public class HotelOrderTransportImpl implements HotelOrderTransport{
	@Autowired
	private HotelOrderService hotelOrderService;

	/**
	 * <b>根据查询条件查询列表信息</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/list")
	 public List<HotelOrder> getHotelListByQuery(@RequestBody HotelOrder query) throws Exception {
		return hotelOrderService.getHotelListByQuery(query);
	}

	/**
	 * <b>保存酒店订单</b>
	 * @param hotelOrder
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/save")
	 public boolean saveHotelOrder(@RequestBody HotelOrder hotelOrder) throws Exception {
		return hotelOrderService.saveHotelOrder(hotelOrder);
	}

	/**
	 * <b>更新订单信息</b>
	 * @param hotelOrder
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/update")
	 public boolean updateHotelOrder(@RequestBody HotelOrder hotelOrder) throws Exception {
		return hotelOrderService.updateHotelOrder(hotelOrder);
	}

	/**
	 * <b>根据订单号查询订单</b>
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/id")
	 public HotelOrder getHotelOrderById(@RequestParam Long orderId) throws Exception {
		return hotelOrderService.getHotelOrderById(orderId);
	}

	/**
	 * <b>根据订单编号查询订单</b>
	 * @param orderNo
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/no")
	public HotelOrder getHotelOrderByNo(@RequestParam String orderNo) throws Exception {
		return hotelOrderService.getHotelOrderByNo(orderNo);
	}

	/**
	 * <b>根据订单编号查询房型相关信息</b>
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/orderId")
	 public PersonalOrderRoomVO getPersonalOrderRoomInfo(@RequestParam Long orderId) throws Exception {
		return hotelOrderService.getPersonalOrderRoomInfo(orderId);
	}

	/**
	 * <b>根据param, pageNo, pageSize查询分页对象</b>
	 * @param param
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/param")
     public	Page queryOrderPageByMap(@RequestParam Map<String, Object> param,
	                         @RequestParam Integer pageNo, @RequestParam Integer pageSize) throws Exception {
		return hotelOrderService.queryOrderPageByMap(param, pageNo, pageSize);
	}
}
