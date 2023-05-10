package top.remained.silence.message.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.sql.Date;
import lombok.Data;

/**
 * 
 * @TableName s_message
 */
@TableName(value ="s_message")
@Data
public class Message implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 招聘的岗位
     */
    private String job;

    /**
     * 招聘的企业
     */
    @TableField("firm_name")
    private String firmName;

    /**
     * 工作地点
     */
    private String location;

    /**
     * 招聘岗位的描述
     */
    private String description;

    /**
     * 招聘的人数
     */
    private Integer total;

    /**
     * 截至日期
     */
    @TableField("end_time")
    private Date endTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}