package study.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import study.entity.po.Student;

/**
 * @author Persolute
 * @version 1.0
 * @description Student Mapper
 * @email 1538520381@qq.com
 * @date 2024/1/29 10:11
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {
}
