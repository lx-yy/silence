package top.remained.silence.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.remained.silence.user.bean.User;
import top.remained.silence.user.service.UserService;
import top.remained.silence.user.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author lmk
* @description 针对表【user】的数据库操作Service实现
* @createDate 2022-09-05 10:59:55
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




