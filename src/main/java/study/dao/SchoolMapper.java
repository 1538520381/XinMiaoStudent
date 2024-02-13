package study.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import study.entity.po.School;

/**
 * @author Persolute
 * @version 1.0
 * @description School Mapper
 * @email 1538520381@qq.com
 * @date 2024/2/1 9:20
 */
@Mapper
public interface SchoolMapper extends BaseMapper<School> {
}
