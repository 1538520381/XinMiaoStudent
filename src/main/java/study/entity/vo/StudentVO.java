package study.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Persolute
 * @version 1.0
 * @description Student VO
 * @email 1538520381@qq.com
 * @date 2024/2/14 17:08
 */
@Data
public class StudentVO implements Serializable {
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
}
