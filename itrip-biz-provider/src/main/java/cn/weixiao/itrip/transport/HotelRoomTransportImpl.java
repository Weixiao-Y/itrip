package cn.weixiao.itrip.transport;

import cn.weixiao.itrip.pojo.entity.HotelRoom;
import cn.weixiao.itrip.pojo.vo.SearchHotelRoomVO;
import cn.weixiao.itrip.service.HotelRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <b>爱旅行-酒店房间模块传输层接口实现类</b>
 * @author Weixiao
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController("hotelRoomTransport")
@RequestMapping("/hotelroom/trans")
public class HotelRoomTransportImpl implements HotelRoomTransport{
	@Autowired
	private HotelRoomService hotelRoomService;
	/**
	 * <b>查询酒店房间列表-此刻可以预定的房间列表</b>
	 * @param searchHotelRoomVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/queryhotelroombyhotel")
	public List<HotelRoom> queryHotelRoombyHotel(@RequestBody SearchHotelRoomVO searchHotelRoomVO) throws Exception {
		return hotelRoomService.queryHotelRoombyHotel(searchHotelRoomVO);
	}
}
