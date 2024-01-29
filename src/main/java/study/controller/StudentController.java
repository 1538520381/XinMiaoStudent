package study.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.service.StudentService;

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
}
