package top.remained.silence.clazz.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName s_clazz
 */
@TableName(value ="s_clazz")
@Data
public class Clazz implements Serializable {
    /**
     * 班级编号
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 班级名字
     */
    private String name;

    /**
     * 班主任名字
     */
    @TableField("teacher_name")
    private String teacherName;

    /**
     * 代课老师名字
     */
    @TableField("teacher1_name")
    private String teacher1Name;

    /**
     * 所属学院
     */
    @TableField("college_name")
    private String collegeName;

    /**
     * 所属专业
     */
    @TableField("major_name")
    private String majorName;

    /**
     * 班级人数
     */
    private Integer num;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}