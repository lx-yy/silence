package top.remained.silence.message.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.remained.silence.message.bean.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author lmk
* @description 针对表【s_message】的数据库操作Mapper
* @createDate 2022-09-11 16:31:43
* @Entity top.remained.silence.message.bean.Message
*/
@Mapper
public interface MessageMapper extends BaseMapper<Message> {

}




