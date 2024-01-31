package study.utils;

import com.alibaba.druid.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.regex.Pattern;

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

    /*
     * @author Persolute
     * @version 1.0
     * @description 判断邮箱是否合法
     * @email 1538520381@qq.com
     * @date 2024/1/31 16:55
     */
    public static boolean isEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            return false;
        }
        Pattern regex = Pattern.compile("^([a-z0-9A-Z]+[-|_.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
        return regex.matcher(email).matches();
    }
}