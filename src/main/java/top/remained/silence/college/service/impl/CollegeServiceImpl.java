package top.remained.silence.college.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.remained.silence.college.bean.College;
import top.remained.silence.college.service.CollegeService;
import top.remained.silence.college.mapper.CollegeMapper;
import org.springframework.stereotype.Service;
import top.remained.silence.major.bean.Major;

import java.util.List;

/**
* @author lmk
* @description 针对表【s_college(学院表)】的数据库操作Service实现
* @createDate 2022-09-10 15:13:23
*/
@Service
public class CollegeServiceImpl extends ServiceImpl<CollegeMapper, College>
    implements CollegeService{


}




