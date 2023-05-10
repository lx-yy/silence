package top.remained.silence.log.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.sql.Date;
import lombok.Data;

/**
 * 
 * @TableName s_log
 */
@TableName(value ="s_log")
@Data
public class Log implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;


    /**
     * 
     */
    @TableField("firm_name")
    private String firmName;

    /**
     * 
     */
    @TableField("student_id")
    private Long studentId;

    /**
     * 
     */
    @TableField("student_name")
    private String studentName;

    /**
     * 投递时间
     */
    private Date time1;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}