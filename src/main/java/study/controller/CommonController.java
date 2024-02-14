package study.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import study.entity.result.R;
import study.enums.HttpCodeEnum;
import study.utils.CodeUtil;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

/**
 * @author Persolute
 * @version 1.0
 * @description 通用Controller
 * @email 1538520381@qq.com
 * @date 2024/1/31 16:02
 */
@Slf4j
@RestController
@RequestMapping("/common")
public class CommonController {
    @Autowired
    private JavaMailSender javaMailSender;

    // 发送邮箱者的邮箱
    @Value("${spring.mail.username}")
    private String fromEmail;

    // 图片存放路径
    @Value("${xin-miao-student.photo.path}")
    private String photoPath;

    /*
     * @author Persolute
     * @version 1.0
     * @description 发送邮箱验证码
     * @email 1538520381@qq.com
     * @date 2024/1/31 14:25
     */
    @PostMapping("/sendCode/{toEmail}")
    public R<String> sendCode(HttpServletRequest request, @PathVariable String toEmail) {
        if (!CodeUtil.isEmail(toEmail)) {
            return R.error(HttpCodeEnum.ILLEGAL_MAIL);
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

    /*
     * @author Persolute
     * @version 1.0
     * @description 图片上传
     * @email 1538520381@qq.com
     * @date 2024/2/13 18:16
     */
    @PostMapping("/photo/upload")
    public R<String> photoUpload(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = UUID.randomUUID() + suffix;
        File dir = new File(photoPath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        try {
            file.transferTo(new File(photoPath + fileName));
        } catch (IOException e) {
            return R.error(HttpCodeEnum.UPLOAD_FAIL);
        }
        return R.success("上传成功", fileName);
    }

    /*
     * @author Persolute
     * @version 1.0
     * @description 图片下载
     * @email 1538520381@qq.com
     * @date 2024/2/13 18:17
     */
    @GetMapping("/photo/download/{fileName}")
    public void photoDownload(@PathVariable String fileName, HttpServletResponse response) throws IOException {
        try {
            FileInputStream fileInputStream = new FileInputStream(photoPath + fileName);
            ServletOutputStream servletOutputStream = response.getOutputStream();
            response.setContentType("image/jpeg");
            int len;
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes)) != -1) {
                servletOutputStream.write(bytes, 0, len);
                servletOutputStream.flush();
            }
            servletOutputStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            throw e;
        }
    }
}
