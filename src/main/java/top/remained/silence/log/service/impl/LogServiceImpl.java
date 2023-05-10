package top.remained.silence.log.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.remained.silence.log.bean.Log;
import top.remained.silence.log.service.LogService;
import top.remained.silence.log.mapper.LogMapper;
import org.springframework.stereotype.Service;

/**
* @author lmk
* @description 针对表【s_log】的数据库操作Service实现
* @createDate 2022-09-16 15:20:58
*/
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log>
    implements LogService{

}




