package study.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author Persolute
 * @version 1.0
 * @description 验证码工具类
 * @email 1538520381@qq.com
 * @date 2024/1/31 14:14
 */
@Component
public class CodeUtil {
    /*
     * @author Persolute
     * @version 1.0
     * @description 随机生成len位数字验证码
     * @email 1538520381@qq.com
     * @date 2024/1/31 14:15
     */
    public static String getRandomNumCode(int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(new Random().nextInt(10));
        }
        return sb.toString();
    }
}