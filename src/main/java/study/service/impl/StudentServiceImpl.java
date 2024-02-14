package study.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import study.dao.StudentMapper;
import study.entity.po.Student;
import study.entity.result.R;
import study.enums.HttpCodeEnum;
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
    public boolean add(Long schoolId, List<Student> list) {
        for (Student student : list) {
            String salt = PwdUtil.getRandomSalt();
            student.setSalt(salt);

            String password = student.getPassword();
            student.setPassword(DigestUtils.md5DigestAsHex((password + salt).getBytes()));

            student.setSchoolId(schoolId);
        }
        return saveBatch(list);
    }

    /*
     * @author Persolute
     * @version 1.0
     * @description 学生登陆
     * @email 1538520381@qq.com
     * @date 2024/1/29 14:23
     */
    @Override
    public R<Student> login(Student student) {
        LambdaQueryWrapper<Student> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Student::getStudentId, student.getStudentId());
        Student student0 = this.getOne(lambdaQueryWrapper);
        if (student0 == null) {
            return R.error(HttpCodeEnum.ACCOUNT_UNEXIST);
        }

        String salt = student0.getSalt();
        if (!DigestUtils.md5DigestAsHex((student.getPassword() + salt).getBytes()).equals(student0.getPassword())) {
            return R.error(HttpCodeEnum.PASSWORD_ERROR);
        }
        return R.success("登录成功", student0);
    }

    /*
     * @author Persolute
     * @version 1.0
     * @description 学生个人信息更新
     * @email 1538520381@qq.com
     * @date 2024/2/14 18:30
     */
    @Override
    public boolean update(Student student) {
        String salt = PwdUtil.getRandomSalt();
        student.setSalt(salt);
        student.setPassword(DigestUtils.md5DigestAsHex((student.getPassword() + salt).getBytes()));
        return updateById(student);
    }
}
