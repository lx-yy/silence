package top.remained.silence.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import top.remained.silence.firm.bean.Firm;
import top.remained.silence.firm.service.FirmService;
import top.remained.silence.log.service.LogService;
import top.remained.silence.resume.service.ResumeService;
import top.remained.silence.student.bean.Student;
import top.remained.silence.student.service.StudentService;
import top.remained.silence.user.bean.User;
import top.remained.silence.utils.CreateVerifiCodeImage;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Project：silence
 * Date：2022/9/13
 * Time：16:14
 * Description：TODO
 *
 * @author lmk
 * @version 1.0
 */
@RestController
@RequestMapping("/sys")
public class SystemController {

    @Autowired
    StudentService studentService;
    @Autowired
    FirmService firmService;
    @Autowired
    LogService logService;
    @Autowired
    ResumeService resumeService;
    @Autowired
    StringRedisTemplate redisTemplate;

    /*
     * 发送验证码图片
     * */
    @GetMapping("/getCodeImage")
    public void getCodeImage(HttpServletRequest request, HttpServletResponse response) {
        // 获取图片
        BufferedImage verifiCodeImage = CreateVerifiCodeImage.getVerifiCodeImage();
        // 获取图片上的验证码
        String verifiCode = new String(CreateVerifiCodeImage.getVerifiCode());
        // 将验证码文本放入session域,为下一次验证做准备
        HttpSession session = request.getSession();
        session.setAttribute("verifiCode", verifiCode);
        redisTemplate.opsForValue().set("verifiCode", verifiCode);
        System.out.println(verifiCode + "获取图片时");
        // 将验证码图片响应给浏览器
        try {
            ImageIO.write(verifiCodeImage, "JPEG", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/login")
    public Map<String, Object> login(
            @RequestBody User user,
            HttpServletRequest request) {

        // 验证码校验
        HttpSession session = request.getSession();
        String sessionVerifiCode = redisTemplate.opsForValue().get("verifiCode");
        System.out.println(sessionVerifiCode + "登陆时");
        Map<String, Object> map = new HashMap<>();
        String userCode = user.getCode();
        if ("".equals(sessionVerifiCode) || null == sessionVerifiCode) {
//            验证码获取失败
            map.put("msg", "验证码获取失败");
            return map;
        }
        if (!sessionVerifiCode.equalsIgnoreCase(userCode)) {
//            验证码输入错误
            map.put("msg", "验证码输入错误");
            return map;
        }
        // 从session域中移除现有验证码
        session.removeAttribute("verifiCode");
        //        判断是否是管理员
        if (user.getName().equalsIgnoreCase("lx") &&
                user.getPwd().equalsIgnoreCase("yyds")) {
            map.put("msg","登录成功");
            map.put("id",0);
            return map;
        }
        // 分用户类型进行校验
        if (user.getType() == null) {
            map.put("msg","请选择类型");
            return map;
        }
        switch (user.getType()) {
            case 1:
                try {
                    QueryWrapper<Student> w = new QueryWrapper<>();
                    w.eq("name", user.getName()).eq("id", user.getPwd());
                    Student student = studentService.getOne(w);
                    if (student == null) {
                        map.put("msg", "账号密码错误");
                        return map;
                    }
                    map.put("student", student);
                    map.put("msg", "登录成功");
                    return map;
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    map.put("msg", "账号密码错误");
                    return map;
                }
            case 2:
                try {
                    QueryWrapper<Firm> w = new QueryWrapper<>();
                    w.eq("name", user.getName()).eq("pwd", user.getPwd());
                    Firm firm = firmService.getOne(w);
                    if (firm == null) {
                        map.put("msg", "账号密码错误");
                        return map;
                    }
                    map.put("firm", firm);
                    map.put("msg", "登录成功");
                    return map;
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    map.put("msg", "账号密码错误");
                    return map;
                }
            default:
                map.put("msg","查无此用户");
                return map;
        }
        //查无此用户
//        map.put("msg", "查无此用户");
//        return map;


    }
//    统计信息
    @GetMapping("/statistics")
    public Map<String,Long> statistics(){
     Map <String,Long> map = new HashMap<>();
//     已注册公司数量
        map.put("firmCount",firmService.count());
//        已注册学生数量
        map.put("studentCount",studentService.count());
//        已生成简历数量
        map.put("resumeCount",studentService.count());
//        学生已投递公司 数量
        map.put("recordCount",resumeService.count());
     return  map;
    }

}
