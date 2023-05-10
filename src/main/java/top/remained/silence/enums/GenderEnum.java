package top.remained.silence.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Project：silence
 * Date：2022/9/2
 * Time：13:33
 * Description：TODO
 *
 * @author lmk
 * @version 1.0
 */


@AllArgsConstructor
@Getter
public enum GenderEnum {
    MALE(1,"男"),
    FEMALE(2,"女");
    //设置性别信息为枚举项，会将@EnumValue注解所标识的属性值存储到数据库
    @EnumValue
    public Integer gender;
    // 将注解标识的属性值返回给前端
    @JsonValue
    public String  genderName;



}
