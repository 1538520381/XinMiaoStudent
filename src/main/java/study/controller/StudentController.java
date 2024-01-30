package study.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.entity.po.Student;
import study.entity.result.R;
import study.enums.HttpCodeEnum;
import study.service.StudentService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Persolute
 * @version 1.0
 * @description Student Controller
 * @email 1538520381@qq.com
 * @date 2024/1/29 10:16
 */
@Slf4j
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    /*
     * @author Persolute
     * @version 1.0
     * @description 学生账号导入
     * @email 1538520381@qq.com
     * @date 2024/1/29 10:45
     */
    @PostMapping("/add")
    public R<String> add(@RequestBody List<Student> list) {
        for (Student student : list) {
            if (student.getClassId() == null || student.getStudentId() == null || student.getPassword() == null || student.getName() == null || student.getGender() == null) {
                return R.error(HttpCodeEnum.FIELD_MISSING);
            }
        }
        if (!studentService.add(list)) {
            return R.error("导入失败");
        } else {
            return R.success("导入成功");
        }
    }

    /*
     * @author Persolute
     * @version 1.0
     * @description 学生登录
     * @email 1538520381@qq.com
     * @date 2024/1/29 14:23
     */
    @PostMapping("/login")
    public R<Student> login(HttpServletRequest request, @RequestBody Student student) {
        log.info("123");
        if (student.getStudentId() == null) {
            return R.error(HttpCodeEnum.ACCOUNT_MISSING);
        } else if (student.getPassword() == null) {
            return R.error(HttpCodeEnum.PASSWORD_ERROR);
        }
        R<Student> r = studentService.login(student);
        if (r.getCode() == HttpCodeEnum.SUCCESS.getCode()) {
            request.getSession().setAttribute("user", r.getData().getId());
        }
        return r;
    }
}
