package top.remained.silence.resume.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName s_resume
 */
@TableName(value ="s_resume")
@Data
public class Resume implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    @TableField("student_id")
    private Long studentId;

    /**
     *  项目经验
     */
    private String project;

    /**
     *  掌握技能
     */
    private String skill;

    /**
     *  籍贯
     */
    private String hometown;

    /**
     * 在校经历
     */
    private String school;

    /**
     * 
     */

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}