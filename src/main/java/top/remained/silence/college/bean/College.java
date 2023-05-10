package top.remained.silence.college.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.sql.Date;
import lombok.Data;

/**
 * 学院表
 * @TableName s_college
 */
@TableName(value ="s_college")
@Data
public class College implements Serializable {
    /**
     * 学院编号
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 学院名字
     */
    private String name;

    /**
     * 校长
     */
    private String president;

    /**
     * 学院描述
     */
    private String description;

    /**
     * 成立日期
     */
    private Date buildeTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}