package top.remained.silence.clazz.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.remained.silence.clazz.bean.Clazz;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author lmk
* @description 针对表【s_clazz】的数据库操作Mapper
* @createDate 2022-09-05 10:15:38
* @Entity top.remained.silence.clazz.bean.Clazz
*/
@Mapper
public interface ClazzMapper extends BaseMapper<Clazz> {

}




