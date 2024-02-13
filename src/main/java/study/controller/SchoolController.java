package study.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.entity.po.School;
import study.entity.result.R;
import study.entity.vo.SchoolVO;
import study.enums.HttpCodeEnum;
import study.service.SchoolService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Persolute
 * @version 1.0
 * @description School Controller
 * @email 1538520381@qq.com
 * @date 2024/2/1 9:23
 */
@Slf4j
@RestController
@RequestMapping("/school")
public class SchoolController {
    @Autowired
    private SchoolService schoolService;

    /*
     * @author Persolute
     * @version 1.0
     * @description 学校登录
     * @email 1538520381@qq.com
     * @date 2024/2/5 17:24
     */
    @PostMapping("/login")
    public R<SchoolVO> login(HttpServletRequest request, @RequestBody School school) {
        if (school.getAccount() == null) {
            return R.error(HttpCodeEnum.ACCOUNT_MISSING);
        } else if (school.getPassword() == null) {
            return R.error(HttpCodeEnum.PASSWORD_MISSING);
        }

        R<School> r = schoolService.login(school);
        if (r.getCode() != HttpCodeEnum.SUCCESS.getCode()) {
            return R.error(r.getCode(), r.getMsg());
        }

        request.getSession().setAttribute("school", r.getData().getId());
        SchoolVO schoolVO = new SchoolVO();
        BeanUtils.copyProperties(r.getData(), schoolVO);
        return R.success(schoolVO);
    }
}
