package top.remained.silence.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.remained.silence.user.bean.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author lmk
* @description 针对表【user】的数据库操作Mapper
* @createDate 2022-09-05 10:59:55
* @Entity top.remained.silence.user.bean.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




