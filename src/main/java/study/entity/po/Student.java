package study.entity.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Persolute
 * @version 1.0
 * @description Student实体类
 * @email 1538520381@qq.com
 * @date 2024/1/28 19:05
 */
// 学生
@Data
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    // 主键
    private Long id;

    // 学校主键
    private Long schoolId;

    // 学院
    private String college;

    // 专业
    private String speciality;

    // 班级
    private Long classId;

    // 学号
    private Long studentId;

    // 密码
    private String password;

    // 盐
    private String salt;

    // 姓名
    private String name;

    // 性别
    private Integer gender;

    // 照片名
    private String photo;

    // 手机号
    private Long phone;

    // 地址
    private String address;

    // 创建时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    // 修改时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    // 创建用户
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    // 修改用户
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;
}
