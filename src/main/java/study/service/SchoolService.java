package study.service;

import com.baomidou.mybatisplus.extension.service.IService;
import study.entity.po.School;
import study.entity.result.R;

/**
 * @author Persolute
 * @version 1.0
 * @description School Service
 * @email 1538520381@qq.com
 * @date 2024/2/1 9:20
 */
public interface SchoolService extends IService<School> {
    R<School> login(School school);
}
