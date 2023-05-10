package top.remained.silence.firm.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.remained.silence.firm.bean.Firm;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author lmk
* @description 针对表【s_firm】的数据库操作Mapper
* @createDate 2022-09-14 19:09:21
* @Entity top.remained.silence.firm.bean.Firm
*/
@Mapper
public interface FirmMapper extends BaseMapper<Firm> {

}




