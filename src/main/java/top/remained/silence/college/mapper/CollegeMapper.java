package top.remained.silence.college.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.remained.silence.college.bean.College;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author lmk
* @description 针对表【s_college(学院表)】的数据库操作Mapper
* @createDate 2022-09-10 15:13:23
* @Entity top.remained.silence.college.bean.College
*/
@Mapper
public interface CollegeMapper extends BaseMapper<College> {

}




