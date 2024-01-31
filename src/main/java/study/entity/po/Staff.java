package study.entity.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Persolute
 * @version 1.0
 * @description Staff实体类
 * @email 1538520381@qq.com
 * @date 2024/1/31 14:07
 */
@Data
public class Staff implements Serializable {
    private static final long serialVersionUID = 1L;

    // 主键
    private Long id;

    // 学校主键
    private Long schoolId;

    // 姓名
    private String name;

    // 邮箱
    private String email;

    // 创建时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    // 修改时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
