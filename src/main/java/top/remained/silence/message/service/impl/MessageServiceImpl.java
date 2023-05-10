package top.remained.silence.message.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.remained.silence.message.bean.Message;
import top.remained.silence.message.service.MessageService;
import top.remained.silence.message.mapper.MessageMapper;
import org.springframework.stereotype.Service;

/**
* @author lmk
* @description 针对表【s_message】的数据库操作Service实现
* @createDate 2022-09-11 16:31:43
*/
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message>
    implements MessageService{

}




