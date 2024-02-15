package study.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import study.entity.po.Student;
import study.entity.result.R;
import study.entity.dto.StudentInquireDTO;
import study.entity.vo.StudentVO;
import study.enums.HttpCodeEnum;
import study.service.StudentService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

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
    @PutMapping
    public R<String> update(@RequestBody Student student) {
        if (studentService.update(student)) {
            return R.success("修改成功");
        } else {
            return R.error("修改失败");
        }
    }

    /*
     * @author Persolute
     * @version 1.0
     * @description 根据主键查询学生信息
     * @email 1538520381@qq.com
     * @date 2024/2/14 18:10
     */
    @GetMapping("/{id}")
    public R<StudentVO> getById(@PathVariable Long id) {
        Student student = studentService.getById(id);
        if (student == null) {
            return R.error(HttpCodeEnum.USER_UNEXIST);
        }
        StudentVO studentVO = new StudentVO();
        BeanUtils.copyProperties(student, studentVO);
        return R.success("查询成功", studentVO);
    }

    /*
     * @author Persolute
     * @version 1.0
     * @description 根据限制分页查询学生
     * @email 1538520381@qq.com
     * @date 2024/2/14 18:36
     */
    @GetMapping("/limit/{page}/{pageSize}")
    public R<Page<StudentVO>> getByLimit(HttpServletRequest request, @PathVariable Integer page, @PathVariable Integer pageSize, @RequestBody StudentInquireDTO studentInquireDTO) {
        Long schoolId = (Long) request.getSession().getAttribute("school");
        if (schoolId == null) {
            return R.error(HttpCodeEnum.NO_PERMISSION);
        }
        Page<Student> studentPages = studentService.getByLimit(page, pageSize, schoolId, studentInquireDTO);
        Page<StudentVO> studentVOPages = new Page<>();
        BeanUtils.copyProperties(studentPages, studentVOPages, "records");
        List<Student> students = studentPages.getRecords();
        List<StudentVO> studentVOS = students.stream().map(student -> {
            StudentVO studentVO = new StudentVO();
            BeanUtils.copyProperties(student, studentVO);
            return studentVO;
        }).collect(Collectors.toList());
        studentVOPages.setRecords(studentVOS);
        return R.success("查询成功", studentVOPages);
    }

    /*
     * @author Persolute
     * @version 1.0
     * @description 根据主键删除学生
     * @email 1538520381@qq.com
     * @date 2024/2/15 9:16
     */
    @DeleteMapping("/{id}")
    public R<String> deleteById(@PathVariable Long id) {
        if (studentService.removeById(id)) {
            return R.success("删除成功");
        } else {
            return R.error("删除失败");
        }
    }
}
