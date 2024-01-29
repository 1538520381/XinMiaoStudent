package study.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import study.dao.StudentMapper;
import study.entity.po.Student;
import study.service.StudentService;
import study.utils.PwdUtil;

import java.util.List;

/**
 * @author Persolute
 * @version 1.0
 * @description Student ServiceImpl
 * @email 1538520381@qq.com
 * @date 2024/1/29 10:13
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    /*
     * @author Persolute
     * @version 1.0
     * @description 学生账号导入
     * @email 1538520381@qq.com
     * @date 2024/1/29 14:21
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean add(List<Student> list) {
        for (Student student : list) {
            String salt = PwdUtil.getRandomSalt();
            student.setSalt(salt);

            String password = student.getPassword();
            student.setPassword(DigestUtils.md5DigestAsHex((password + salt).getBytes()));
        }
        return saveBatch(list);
    }
}
