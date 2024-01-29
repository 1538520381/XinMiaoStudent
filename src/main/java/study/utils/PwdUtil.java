package study.utils;

import java.util.Random;

/**
 * @author Persolute
 * @version 1.0
 * @description 加密工具类
 * @email 1538520381@qq.com
 * @date 2024/1/29 12:30
 */
public class PwdUtil {
    /*
     * @author Persolute
     * @version 1.0
     * @description 随机生成盐
     * @email 1538520381@qq.com
     * @date 2024/1/29 12:33
     */
    public static String getRandomSalt() {
        char[] cs = ("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890!@#$%^&*()_+").toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            sb.append(cs[new Random().nextInt(cs.length)]);
        }
        return sb.toString();
    }
}
