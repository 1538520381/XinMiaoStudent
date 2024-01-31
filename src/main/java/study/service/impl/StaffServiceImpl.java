package study.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import study.dao.StaffMapper;
import study.entity.po.Staff;
import study.service.StaffService;

/**
 * @author Persolute
 * @version 1.0
 * @description Staff ServiceImpl
 * @email 1538520381@qq.com
 * @date 2024/1/31 15:30
 */
@Service
public class StaffServiceImpl extends ServiceImpl<StaffMapper, Staff> implements StaffService {
}
