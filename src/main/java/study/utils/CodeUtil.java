package study.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
    private static JavaMailSender javaMailSender;

    @Autowired
    public void setJavaMailSender(JavaMailSender javaMailSender) {
        CodeUtil.javaMailSender = javaMailSender;
    }

    // 发送邮箱者的邮箱
    private static String fromEmail;

    @Value("${spring.mail.username}")
    public void setFromEmail(String fromEmail) {
        CodeUtil.fromEmail = fromEmail;
    }

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
     * @description 发送邮箱验证码
     * @email 1538520381@qq.com
     * @date 2024/1/31 14:25
     */
    public boolean sendEmailCode(String toEmail, String code) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromEmail);
        simpleMailMessage.setTo(toEmail);
        simpleMailMessage.setSubject("验证码");
        simpleMailMessage.setText(code);
        try {
            javaMailSender.send(simpleMailMessage);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}