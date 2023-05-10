package top.remained.silence.major.service;

import top.remained.silence.major.bean.Major;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author lmk
* @description 针对表【s_major(专业表)】的数据库操作Service
* @createDate 2022-09-10 15:01:19
*/
public interface MajorService extends IService<Major> {
    public List<Major> getAll(Integer index, Integer size,String name);
}
