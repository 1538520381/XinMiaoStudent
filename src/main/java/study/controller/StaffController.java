package study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import study.service.StaffService;

/**
 * @author Persolute
 * @version 1.0
 * @description Staff Controller
 * @email 1538520381@qq.com
 * @date 2024/1/31 15:31
 */
@Controller
public class StaffController {
    @Autowired
    private StaffService staffService;
}
