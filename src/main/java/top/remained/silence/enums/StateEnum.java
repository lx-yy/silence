package top.remained.silence.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Project：silence
 * Date：2022/9/2
 * Time：14:50
 * Description：TODO
 *
 * @author lmk
 * @version 1.0
 */
@Getter
@AllArgsConstructor
public enum StateEnum {
    HAVE(1,"有工作"),
    NO(2,"没有工作");

    @EnumValue
    private Integer state;
    @JsonValue
    private String stateEnum;
}
