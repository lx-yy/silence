package top.remained.silence.major.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.remained.silence.major.bean.Major;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.remained.silence.student.bean.Student;

import java.util.List;

/**
* @author lmk
* @description 针对表【s_major(专业表)】的数据库操作Mapper
* @createDate 2022-09-10 15:01:19
* @Entity top.remained.silence.major.bean.Major
*/
@Mapper
public interface MajorMapper extends BaseMapper<Major> {
    public List<Major> getAll(@Param("offset") Integer offset,@Param("size")Integer size,@Param("name")String name);

}




