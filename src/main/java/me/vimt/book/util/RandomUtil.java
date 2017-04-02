package me.vimt.book.util;

import java.util.Random;

/**
 * User: Tao
 * Email: 562593188@qq.com
 * Time: 2016/11/4 20:00
 * Description:
 */
public class RandomUtil {
    /**
     * 生成随机字符串（字母和数字组成）
     *
     * @param length 生成字符串的长度
     * @return 长度为length的随机字符串
     */
    public static String getStringRandom(int length) {
        Random random = new Random(System.currentTimeMillis());
        String val = "";
        String charStr = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int charLength = charStr.length();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(charLength);
            val += charStr.charAt(index);
        }
        return val;
    }
}
