package top.remained.silence.firm.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName s_firm
 */
@TableName(value ="s_firm")
@Data
public class Firm implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 公司名字
     */
    private String name;

    /**
     * 负责人
     */
    private String ceo;

    /**
     * 地点
     */
    private String location;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 公司概况
     */
    private String decription;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}