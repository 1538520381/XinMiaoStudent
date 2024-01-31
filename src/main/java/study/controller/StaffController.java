package study.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.entity.po.Staff;
import study.entity.result.R;
import study.entity.vo.StaffVO;
import study.enums.HttpCodeEnum;
import study.service.StaffService;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author Persolute
 * @version 1.0
 * @description Staff Controller
 * @email 1538520381@qq.com
 * @date 2024/1/31 15:31
 */
@RestController
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    private StaffService staffService;

    /*
     * @author Persolute
     * @version 1.0
     * @description 职员注册
     * @email 1538520381@qq.com
     * @date 2024/1/31 16:01
     */
    @PostMapping("/register")
    public R<String> register(HttpServletRequest request, @RequestBody StaffVO staffVO) {
        if (staffVO.getSchoolId() == null) {
            return R.error(HttpCodeEnum.SCHOOL_MISSING);
        } else if (staffVO.getEmail() == null) {
            return R.error(HttpCodeEnum.EMAIL_MISSING);
        } else if (staffVO.getCode() == null) {
            return R.error(HttpCodeEnum.CODE_MISSING);
        }

        String code0 = (String) request.getSession().getAttribute("code");
        if (Objects.equals(code0, staffVO.getCode())) {
            Staff staff = new Staff();
            BeanUtils.copyProperties(staffVO, staff);
            if (staffService.register(staff)) {
                return R.success("注册成功");
            } else {
                return R.error(HttpCodeEnum.REGISTER_FAIL);
            }
        } else {
            return R.error(HttpCodeEnum.CODE_WRONG);
        }
    }
}
