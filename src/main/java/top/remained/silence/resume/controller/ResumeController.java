package top.remained.silence.resume.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.remained.silence.resume.bean.Resume;
import top.remained.silence.resume.service.ResumeService;
import top.remained.silence.student.service.StudentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Project：silence
 * Date：2022/9/9
 * Time：22:15
 * Description：TODO
 *
 * @author lmk
 * @version 1.0
 */
@RestController
@RequestMapping("resume")
public class ResumeController {
    @Autowired
    ResumeService resumeService;
    @Autowired
    StudentService studentService;
    @PostMapping("/add")
    public boolean addResume(@RequestBody Resume resume){

       return resumeService.save(resume);
    }
//    判断是否有简历 获取简历 获取学生信息 动态填写
    @GetMapping("/{studentId}")
    public Map<String,Object> getResumeByStudentId(@PathVariable("studentId")Long studentId){
        QueryWrapper<Resume> w = new QueryWrapper<>();
        w.eq("student_id",studentId);
            //eq 等于 ge大于等于 le小于等于 lt小于 gt大于
        Map<String,Object> map = new HashMap<>();
        map.put("student",studentService.getById(studentId));

        try {
            map.put("resume",resumeService.getOne(w));
            return map ;
        } catch (Exception e) {
            return map;
        }

    }
//    添加或更改
    @PostMapping("/saveOrUpdate")
    public boolean saveOrUpdate1(@RequestBody Resume resume){

        return resumeService.saveOrUpdate(resume);
    }



}
