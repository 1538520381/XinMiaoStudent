package study.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Persolute
 * @version 1.0
 * @description StudentInquire VO
 * @email 1538520381@qq.com
 * @date 2024/2/14 18:38
 */
@Data
    public class StudentInquireDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    // 学院
    private String college;

    // 专业
    private String speciality;

    // 班级
    private Long classId;

    // 学号
    private Long studentId;
}
