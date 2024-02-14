package study.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Persolute
 * @version 1.0
 * @description School VO
 * @email 1538520381@qq.com
 * @date 2024/2/13 17:36
 */
@Data
public class SchoolVO implements Serializable {
    private static final long serialVersionUID = 1L;

    // 主键
    private Long id;

    // 账号
    private String account;
}
