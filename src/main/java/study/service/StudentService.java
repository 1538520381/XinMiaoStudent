package study.service;

import com.baomidou.mybatisplus.extension.service.IService;
import study.entity.po.Student;
import study.entity.result.R;

import java.util.List;

/**
 * @author Persolute
 * @version 1.0
 * @description Student Service
 * @email 1538520381@qq.com
 * @date 2024/1/29 10:13
 */
public interface StudentService extends IService<Student> {
    boolean add(Long schoolId, List<Student> list);

    R<Student> login(Student student);

    boolean update(Student student);
}
