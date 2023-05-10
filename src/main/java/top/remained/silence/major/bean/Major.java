package top.remained.silence.major.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import top.remained.silence.clazz.bean.Clazz;
import top.remained.silence.college.bean.College;
import top.remained.silence.student.bean.Student;

/**
 * 专业表
 * @TableName s_major
 */
@TableName(value ="s_major")
@Data
public class Major implements Serializable {
    /**
     * 专业代码
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 专业名称
     */
    private String name;

    /**
     * 专业学制
     */
    private String year;

    /**
     * 对应的班级id
     */
    @TableField("class_id")
    private Integer classId;

    /**
     * 专业类型
     */
    private String type;

    /**
     * 
     */
    @TableField("college_id")
    private Integer collegeId;
//    对应学院 多对1
    private College college;
//    对应班级 1对多
    private List<Clazz> clazzes;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}