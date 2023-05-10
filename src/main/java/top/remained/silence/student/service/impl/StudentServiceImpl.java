package top.remained.silence.student.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import top.remained.silence.student.bean.Student;
import top.remained.silence.student.service.StudentService;
import top.remained.silence.student.mapper.StudentMapper;
import org.springframework.stereotype.Service;
import top.remained.silence.system.bean.Ech;

import java.util.List;
import java.util.Map;

/**
* @author lmk
* @description 针对表【s_student】的数据库操作Service实现
* @createDate 2022-09-02 13:12:54
*/
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
    implements StudentService{
    @Autowired
    StudentMapper studentMapper;
    @Override
    public Integer[] getClazzId() {
        return studentMapper.getClazzId();
    }

    @Override
    public Student getDetail(Integer id) {
        return studentMapper.getDetail(id);
    }

    @Override
    public List<Map<String, Object>> getEcharts() {
        return studentMapper.getEcharts();
    }

    @Override
    public List<Ech> getEcharts2() {
        return studentMapper.getEcharts2();
    }
}




