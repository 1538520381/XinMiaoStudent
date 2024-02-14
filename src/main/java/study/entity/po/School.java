package study.entity.po;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Persolute
 * @version 1.0
 * @description School实体类
 * @email 1538520381@qq.com
 * @date 2024/1/31 13:54
 */
@Data
public class School implements Serializable {
    private static final long serialVersionUID = 1L;

    // 主键
    private Long id;

    // 账号
    private String account;

    // 密码
    private String password;
}
