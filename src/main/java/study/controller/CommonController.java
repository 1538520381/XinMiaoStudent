package study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import study.entity.result.R;
import study.enums.HttpCodeEnum;
import study.utils.CodeUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Persolute
 * @version 1.0
 * @description 通用Controller
 * @email 1538520381@qq.com
 * @date 2024/1/31 16:02
 */
@Controller
@RequestMapping("/common")
public class CommonController {
    @Autowired
    private JavaMailSender javaMailSender;

    // 发送邮箱者的邮箱
    @Value("${spring.mail.username}")
    private static String fromEmail;

    /*
     * @author Persolute
     * @version 1.0
     * @description 发送邮箱验证码
     * @email 1538520381@qq.com
     * @date 2024/1/31 14:25
     */
    @PostMapping("/sendCode/{toEmail}")
    public R<String> sendCode(HttpServletRequest request, @PathVariable String toEmail) {
        if (toEmail == null) {
            return R.error(HttpCodeEnum.EMAIL_MISSING);
        }

        String code = CodeUtil.getRandomNumCode(6);

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromEmail);
        simpleMailMessage.setTo(toEmail);
        simpleMailMessage.setSubject("验证码");
        simpleMailMessage.setText(code);

        request.getSession().setAttribute("code", code);

        try {
            javaMailSender.send(simpleMailMessage);
            return R.success("验证码发送成功");
        } catch (Exception e) {
            return R.error(HttpCodeEnum.SEND_CODE_FAIL);
        }
    }
}
