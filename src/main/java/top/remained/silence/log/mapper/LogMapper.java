package top.remained.silence.log.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.remained.silence.log.bean.Log;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author lmk
* @description 针对表【s_log】的数据库操作Mapper
* @createDate 2022-09-16 15:20:58
* @Entity top.remained.silence.log.bean.Log
*/
@Mapper
public interface LogMapper extends BaseMapper<Log> {

}




