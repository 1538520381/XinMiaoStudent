package study.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import study.entity.po.Staff;

/**
 * @author Persolute
 * @version 1.0
 * @description staff Mapper
 * @email 1538520381@qq.com
 * @date 2024/1/31 15:32
 */
@Mapper
public interface StaffMapper extends BaseMapper<Staff> {
}
