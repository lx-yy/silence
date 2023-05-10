package top.remained.silence.student.service;

import top.remained.silence.student.bean.Student;
import com.baomidou.mybatisplus.extension.service.IService;
import top.remained.silence.system.bean.Ech;

import java.util.List;
import java.util.Map;

/**
* @author lmk
* @description 针对表【s_student】的数据库操作Service
* @createDate 2022-09-02 13:12:54
*/
public interface StudentService extends IService<Student> {
    public Integer[] getClazzId();
    public Student getDetail(Integer id);
    public List<Map<String,Object>> getEcharts();
    public List<Ech> getEcharts2();
}
