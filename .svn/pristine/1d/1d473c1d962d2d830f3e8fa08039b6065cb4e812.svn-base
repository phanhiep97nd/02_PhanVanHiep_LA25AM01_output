/**
 * Copyright(C) 2020  Luvina Software
 * MessageProperties.java, Jul 14, 2020 Phan Văn Hiệp
 */
package manageuser.utils;

import java.io.IOException;
import java.util.Properties;

/**
 * Đọc các thông tin về các câu thông báo, lỗi của hệ thống
 * @author Phan Van Hiep
 */
public class MessageProperties {
	/**
	 * Đọc dữ liệu từ file properties
	 * @param key Giá trị key
	 * @return value tìm được tương ứng với key
	 */
	public static String readFileProperties(String key) {
		String value = "";
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Properties properties = new Properties();
		try {
			properties.load(classLoader.getResourceAsStream("manageuser/utils/message.properties"));
			value = properties.getProperty(key);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}
}
