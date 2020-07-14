/**
 * Copyright(C) 2020  Luvina Software
 * DatabaseProperties.java, Jul 13, 2020 Phan Văn Hiệp
 */
package manageuser.utils;

import java.io.IOException;
import java.util.Properties;

/**
 * Đọc các thông tin thiết định kết nối tới database
 * @author Phan Van Hiep
 */
public class DatabaseProperties {
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
			properties.load(classLoader.getResourceAsStream("manageuser/utils/database.properties"));
			value = properties.getProperty(key);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}
}
