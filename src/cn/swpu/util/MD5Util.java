package cn.swpu.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import cn.swpu.util.StringUtil;

public class MD5Util {
	
	public static void main(String[] args) throws UnsupportedEncodingException{
		String string = MD5Util.encodePassword("informationf1http://zb.mrblankspace.cn/PayServlet1234561234560.01http://www.baidu.com7fc340b48559bb218779b5bf9b971e1ce060d5b0a8347544149bd9f2");
		System.out.println(string);
	}
	
    public static String encodePassword(String password) throws UnsupportedEncodingException {
        if (StringUtil.isEmpty(password)) {
            return password;
        }
        return getMD5(password.getBytes("utf-8"));
    }

    public static String getMD5(byte[] source) {
        String s = null;
        char hexDigits[] = { // 鐢ㄦ潵灏嗗瓧鑺傝浆鎹㈡垚 16 杩涘埗琛ㄧず鐨勫瓧绗�
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            md.update(source);
            byte tmp[] = md.digest(); // MD5 鐨勮绠楃粨鏋滄槸涓�涓� 128 浣嶇殑闀挎暣鏁帮紝
            // 鐢ㄥ瓧鑺傝〃绀哄氨鏄� 16 涓瓧鑺�
            char str[] = new char[16 * 2]; // 姣忎釜瀛楄妭鐢� 16 杩涘埗琛ㄧず鐨勮瘽锛屼娇鐢ㄤ袱涓瓧绗︼紝
            // 鎵�浠ヨ〃绀烘垚 16 杩涘埗闇�瑕� 32 涓瓧绗�
            int k = 0; // 琛ㄧず杞崲缁撴灉涓搴旂殑瀛楃浣嶇疆
            for (int i = 0; i < 16; i++) { // 浠庣涓�涓瓧鑺傚紑濮嬶紝瀵� MD5 鐨勬瘡涓�涓瓧鑺�
                // 杞崲鎴� 16 杩涘埗瀛楃鐨勮浆鎹�
                byte byte0 = tmp[i]; // 鍙栫 i 涓瓧鑺�
                str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 鍙栧瓧鑺備腑楂� 4 浣嶇殑鏁板瓧杞崲,
                // >>> 涓洪�昏緫鍙崇Щ锛屽皢绗﹀彿浣嶄竴璧峰彸绉�
                str[k++] = hexDigits[byte0 & 0xf]; // 鍙栧瓧鑺備腑浣� 4 浣嶇殑鏁板瓧杞崲
            }
            s = new String(str); // 鎹㈠悗鐨勭粨鏋滆浆鎹负瀛楃涓�

        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public final static String MD5(String inputStr) {
        // 鐢ㄤ簬鍔犲瘑鐨勫瓧绗�
        char md5String[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        try {
            // 浣跨敤utf-8瀛楃闆嗗皢姝� String 缂栫爜涓� byte搴忓垪锛屽苟灏嗙粨鏋滃瓨鍌ㄥ埌涓�涓柊鐨� byte鏁扮粍涓�
            byte[] btInput = inputStr.getBytes("utf-8");

            // 淇℃伅鎽樿鏄畨鍏ㄧ殑鍗曞悜鍝堝笇鍑芥暟锛屽畠鎺ユ敹浠绘剰澶у皬鐨勬暟鎹紝骞惰緭鍑哄浐瀹氶暱搴︾殑鍝堝笇鍊笺��
            MessageDigest mdInst = MessageDigest.getInstance("MD5");

            // MessageDigest瀵硅薄閫氳繃浣跨敤 update鏂规硶澶勭悊鏁版嵁锛� 浣跨敤鎸囧畾鐨刡yte鏁扮粍鏇存柊鎽樿
            mdInst.update(btInput);

            // 鎽樿鏇存柊涔嬪悗锛岄�氳繃璋冪敤digest锛堬級鎵ц鍝堝笇璁＄畻锛岃幏寰楀瘑鏂�
            byte[] md = mdInst.digest();

            // 鎶婂瘑鏂囪浆鎹㈡垚鍗佸叚杩涘埗鐨勫瓧绗︿覆褰㈠紡
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) { // i = 0
                byte byte0 = md[i]; // 95
                str[k++] = md5String[byte0 >>> 4 & 0xf]; // 5
                str[k++] = md5String[byte0 & 0xf]; // F
            }

            // 杩斿洖缁忚繃鍔犲瘑鍚庣殑瀛楃涓�
            return new String(str);

        } catch (Exception e) {
            return null;
        }
    }
    
	public static String encryption(String plain) throws UnsupportedEncodingException {
		String re_md5 = new String();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plain.getBytes("utf-8"));
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}

			re_md5 = buf.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return re_md5;
	}
}