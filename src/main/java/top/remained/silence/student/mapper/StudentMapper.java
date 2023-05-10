package top.remained.silence.student.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.models.auth.In;
import javafx.scene.control.Pagination;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.remained.silence.student.bean.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.remained.silence.system.bean.Ech;

import java.util.List;
import java.util.Map;

/**
* @author lmk
* @description 针对表【s_student】的数据库操作Mapper
* @createDate 2022-09-02 13:12:53
* @Entity top.remained.silence.student.bean.Student
*/
@Mapper
public interface StudentMapper extends BaseMapper<Student> {
    @Select("select distinct (clazz_id) from s_student where clazz_id is not null")
    public Integer[] getClazzId();

    public Student getDetail(Integer id);

//    获取echarts echarts
//    @MapKey("name")
    public List<Map<String,Object>> getEcharts();
    public List<Ech> getEcharts2();



}




