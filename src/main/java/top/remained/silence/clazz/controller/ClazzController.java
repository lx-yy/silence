package top.remained.silence.clazz.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.remained.silence.clazz.bean.Clazz;
import top.remained.silence.clazz.service.ClazzService;
import top.remained.silence.message.bean.Message;
import top.remained.silence.student.bean.Student;
import top.remained.silence.student.service.StudentService;

import java.util.*;

/**
 * Project：silence
 * Date：2022/9/5
 * Time：10:16
 * Description：TODO
 *
 * @author lmk
 * @version 1.0
 */
@RestController
@RequestMapping("clazz")
public class ClazzController {
    @Autowired
    ClazzService clazzService;
    @Autowired
    StudentService studentService;
//    通过多个id查询所在的班级信息
    @GetMapping("/getAllName")
    public List<Map<String, Object>>  getClazzBysId() {

        Integer[] clazzId = studentService.getClazzId();
        QueryWrapper<Clazz> w = new QueryWrapper<>();
//        通过获取的id查所有name和id 前端选择器要用
        w.select("name","id").in("id", clazzId);

        return clazzService.listMaps(w);
    }
//    所有信息并分页
    @GetMapping("/getAll/{index}/{size}")
    public Page<Clazz>   getAllClazz( @PathVariable("index") Integer index,
                                      @PathVariable("size") Integer size){

        Page<Clazz> page = new Page<>(index,size);
        return clazzService.page(page);
    }
//      模糊查询并分页
    @GetMapping("/byName/{name}/{index}/{size}")
    public Page<Clazz> findClazzByName(    @PathVariable("name") String name,
                                           @PathVariable("index")Integer index,
                                           @PathVariable("size")Integer size){
        Page<Clazz> page = new Page<>(index,size);
        QueryWrapper<Clazz> w = new QueryWrapper<>();
        return clazzService.page(page,w.like("name",name));
    }

//
    @GetMapping("/search/{id}/{index}/{size}")
    public Page<Clazz> searchId(        @PathVariable("id")Integer id,
                                        @PathVariable("index") Integer index,
                                        @PathVariable("size")Integer size){
        Page<Clazz> page = new Page<>(index,size);
        QueryWrapper<Clazz> w = new QueryWrapper<>();
        return clazzService.page(page,w.in("id",id));
    }
//    获取学院名字
    @GetMapping("/getCollegeName")
    public List<Clazz> getCollegeName(){
        QueryWrapper<Clazz> w = new QueryWrapper<>();
        w.select("college_name","id");
        return clazzService.list(w);
    }

    //      通过学院进行筛选
    @GetMapping("/byCollege/{id}/{index}/{size}")
    public Page<Clazz> searchByCollege(     @PathVariable("id") Integer id,
                                           @PathVariable("index")Integer index,
                                           @PathVariable("size")Integer size){
        QueryWrapper<Clazz> w = new QueryWrapper<>();

        Page<Clazz> page = new Page<>(index,size);
        return clazzService.page(page,w.in("id",id));

    }

//    增
    @PostMapping("/addClazz")
    public boolean addClazz(@RequestBody Clazz clazz){

        return  clazzService.save(clazz);
    }
//    批量删除
    @DeleteMapping("/{ids}")
    public boolean delClazz(@PathVariable("ids") List<Integer> ids){

        return clazzService.removeBatchByIds(ids);
    }
//    通过id查
    @GetMapping("/{id}")
    public Clazz findClazzById(@PathVariable("id") int id){
        return  clazzService.getById(id);
    }
//    修改
    @PutMapping("/update")
    public boolean updateCla(@RequestBody Clazz clazz){
        return  clazzService.updateById(clazz);
    }




}