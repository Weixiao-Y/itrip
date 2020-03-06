package cn.weixiao.itrip.util;

import java.util.Random;

/**
 * <b>爱旅行-激活码生产工具类</b>
 * @author Weixiao
 * @version 1.0.0
 * @since 1.0.0
 */
public class ActiveCodeUtil {

	/**
	 * <b>随机生成六位激活码</b>
	 * @return
	 */
	public static String createActiveCode() {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < 6; i ++) {
			sb.append(random.nextInt(10));
		}
		return sb.toString();
	}
}
