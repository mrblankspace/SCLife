package cn.swpu.util;

import org.apache.commons.lang3.StringUtils;

/**
 * String宸ュ叿 涓昏鏄StringUtils涓�浜涙柟娉曡繘琛岄噸鍐�,浠庤�岃揪鍒版洿濂界殑搴旂敤
 * 
 * @author ztt
 * 
 */
public class StringUtil extends StringUtils {

	/**
	 * 涓�娆℃�у垽鏂涓垨鍗曚釜瀵硅薄涓虹┖銆�
	 * 
	 * @param objects
	 * @author ztt
	 * @return 鍙鏈変竴涓厓绱犱负Blank锛屽垯杩斿洖true
	 */
	public static boolean isBlank(Object... objects) {
		Boolean result = false;
		for (Object object : objects) {
			if (null == object || "".equals(object.toString().trim()) || "null".equals(object.toString().trim())) {
				result = true;
				break;
			}
		}
		return result;
	}

	/**
	 * 涓�娆℃�у垽鏂涓垨鍗曚釜瀵硅薄涓嶄负绌恒��
	 * 
	 * @param objects
	 * @author ztt
	 * @return 鍙鏈変竴涓厓绱犱笉涓築lank锛屽垯杩斿洖true
	 */
	public static boolean isNotBlank(Object... objects) {
		return !isBlank(objects);
	}

	public static boolean isBlank(String... objects) {
		Object[] object = objects;
		return isBlank(object);
	}

	public static boolean isNotBlank(String... objects) {
		Object[] object = objects;
		return !isBlank(object);
	}

	public static boolean isBlank(String str) {
		Object object = str;
		return isBlank(object);
	}

	public static boolean isNotBlank(String str) {
		Object object = str;
		return !isBlank(object);
	}

}
