package top.remained.silence.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.remained.silence.student.bean.Student;
import top.remained.silence.user.bean.User;
import top.remained.silence.user.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Project：silence
 * Date：2022/9/5
 * Time：11:00
 * Description：TODO
 *
 * @author lmk
 * @version 1.0
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/findAllUser")
    public  Map<String,Object> findUserById(){
        Page<User> pages = new Page<User>(1,5);
        Page<User> page = userService.page(pages);
        Map<String,Object> map = new HashMap<>();
        map.put("users",page.getRecords());
        map.put("index",page.getCurrent());
        map.put("size",page.getSize());
        return  map;
    }

}
