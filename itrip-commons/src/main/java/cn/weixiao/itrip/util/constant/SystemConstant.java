package cn.weixiao.itrip.util.constant;

import java.util.Properties;

/**
 * <b>系统常量工具类</b>
 * @author Weixiao
 * @version 1.0.0
 * @since 1.0.0
 */
public class SystemConstant {
	private static Properties props = new Properties();

	static {
		try {
			props.load(SystemConstant.class.getClassLoader().getResourceAsStream("prop/commons.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static final String SECRET_KEY = props.getProperty("secert.key");
}
