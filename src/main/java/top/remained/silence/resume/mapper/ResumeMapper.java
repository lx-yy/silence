package top.remained.silence.resume.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.remained.silence.resume.bean.Resume;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author lmk
* @description 针对表【s_resume】的数据库操作Mapper
* @createDate 2022-09-09 22:15:03
* @Entity top.remained.silence.resume.bean.Resume
*/
@Mapper
public interface ResumeMapper extends BaseMapper<Resume> {

}




