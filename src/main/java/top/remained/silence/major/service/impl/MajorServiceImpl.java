package top.remained.silence.major.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import top.remained.silence.major.bean.Major;
import top.remained.silence.major.service.MajorService;
import top.remained.silence.major.mapper.MajorMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author lmk
* @description 针对表【s_major(专业表)】的数据库操作Service实现
* @createDate 2022-09-10 15:01:19
*/
@Service
public class MajorServiceImpl extends ServiceImpl<MajorMapper, Major>
    implements MajorService{
    @Autowired
    MajorMapper majorMapper;
    @Override
    public List<Major> getAll(Integer index, Integer size,String name) {
        return majorMapper.getAll((index-1)*size, size,name);
    }
}




