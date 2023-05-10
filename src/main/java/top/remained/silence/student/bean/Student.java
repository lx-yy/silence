package top.remained.silence.student.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.sql.Date;
import lombok.Data;
import top.remained.silence.clazz.bean.Clazz;
import top.remained.silence.enums.GenderEnum;
import top.remained.silence.enums.StateEnum;

/**
 * 
 * @TableName s_student
 */
@TableName(value ="s_student")
@Data
public class Student implements Serializable {
    /**
     * 学号
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别1男2女  使用枚举类
     */
    private GenderEnum gender;



    /**
     * 年龄
     */
    private Integer age;

    /**
     * 状态1有工作2没有工作
     */
    private StateEnum state;

    /**
     * 班级编号
     */
    @TableField("clazz_id")
    private Integer clazzId;

    /**
     * 那一届
     */

    private Integer year;


    /**
     * 电话
     */
    private Integer tel;

    /**
     * 出生日期
     */
    private Date birth;

    /**
     * 个人照片
     */
    private String img;
    /**
     * 工作
     */
    private String job;
    @TableField(exist = false)
    private Clazz clazz;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", state=" + state +
                ", clazzId=" + clazzId +
                ", year=" + year +
                ", tel=" + tel +
                ", birth=" + birth +
                ", img='" + img + '\'' +
                ", job='" + job + '\'' +
                ", clazz=" + clazz +
                '}';
    }

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}