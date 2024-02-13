package study.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import study.dao.SchoolMapper;
import study.entity.po.School;
import study.entity.result.R;
import study.enums.HttpCodeEnum;
import study.service.SchoolService;

/**
 * @author Persolute
 * @version 1.0
 * @description School ServiceImpl
 * @email 1538520381@qq.com
 * @date 2024/2/1 9:21
 */
@Service
public class SchoolServiceImpl extends ServiceImpl<SchoolMapper, School> implements SchoolService {
    /*
     * @author Persolute
     * @version 1.0
     * @description 登录
     * @email 1538520381@qq.com
     * @date 2024/2/13 17:31
     */
    public R<School> login(School school) {
        LambdaQueryWrapper<School> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(School::getAccount, school.getAccount());
        School school0 = getOne(lambdaQueryWrapper);

        if (school0 == null) {
            return R.error(HttpCodeEnum.ACCOUNT_UNEXIST);
        } else if (!school0.getPassword().equals(school.getPassword())) {
            return R.error(HttpCodeEnum.PASSWORD_ERROR);
        }
        return R.success("登录成功", school0);
    }
}
