package cn.weixiao.itrip.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class HotelOrderCreator {
	public static String createHotelOrderNo(Long hotelId, Long roomId) throws Exception {
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		// 增加 hotelId
		sb.append(hotelId);
		// 增加 roomId
		sb.append(roomId);
		// 获得当前时间，进行格式化
		SimpleDateFormat dateFormat = new SimpleDateFormat();
		sb.append(dateFormat.format(new Date()));
		// 增加随机数
		sb.append(random.nextInt(10));

		// 对于该结果进行MD5加密
		String result = MD5Util.encrypt(sb.toString());
		return result.toUpperCase();
	}
}
