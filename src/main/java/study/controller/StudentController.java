package study.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import study.entity.po.Student;
import study.entity.result.R;
import study.entity.vo.SchoolVO;
import study.entity.vo.StudentVO;
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
    public R<String> add(HttpServletRequest request, @RequestBody List<Student> list) {
        for (Student student : list) {
            if (student.getCollege() == null || student.getSpeciality() == null || student.getClassId() == null || student.getStudentId() == null || student.getPassword() == null || student.getName() == null || student.getGender() == null || student.getPhoto() == null) {
                return R.error(HttpCodeEnum.FIELD_MISSING);
            }
        }

        Long schoolId = (Long) request.getSession().getAttribute("school");
        if (schoolId == null) {
            return R.error(HttpCodeEnum.NO_PERMISSION);
        }

        if (!studentService.add(schoolId, list)) {
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
    public R<StudentVO> login(HttpServletRequest request, @RequestBody Student student) {
        if (student.getStudentId() == null) {
            return R.error(HttpCodeEnum.ACCOUNT_MISSING);
        } else if (student.getPassword() == null) {
            return R.error(HttpCodeEnum.PASSWORD_MISSING);
        }
        R<Student> r = studentService.login(student);
        if (r.getCode() == HttpCodeEnum.SUCCESS.getCode()) {
            request.getSession().setAttribute("student", r.getData().getId());
            StudentVO studentVO = new StudentVO();
            BeanUtils.copyProperties(r.getData(), studentVO);
            return R.success("登录成功", studentVO);
        }
        return R.error(r.getCode(), r.getMsg());
    }

    /*
     * @author Persolute
     * @version 1.0
     * @description 学生个人信息更新
     * @email 1538520381@qq.com
     * @date 2024/2/5 16:29
     */
    @PutMapping("/update")
    public R<String> update(@RequestBody Student student) {
        if (studentService.update(student)) {
            return R.success("修改成功");
        } else {
            return R.error("修改失败");
        }
    }
}
