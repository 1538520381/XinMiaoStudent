package study.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Persolute
 * @version 1.0
 * @description
 * @email 1538520381@qq.com
 * @date 2024/1/31 15:43
 */
@Data
public class StaffVO implements Serializable {
    private static final long serialVersionUID = 1L;
    // 学校主键
    private Long schoolId;

    // 邮箱
    private String email;

    // 验证码
    private String code;
}