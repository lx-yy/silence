package top.remained.silence.college.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import top.remained.silence.college.bean.College;
import top.remained.silence.college.service.CollegeService;
import top.remained.silence.utils.RedisUtil;

import java.util.List;

/**
 * Project：silence
 * Date：2022/9/11
 * Time：1:01
 * Description：TODO
 *
 * @author lmk
 * @version 1.0
 */
@RequestMapping("college")
@RestController

public class CollegeController {
    @Autowired
    CollegeService collegeService;
    @Autowired
    StringRedisTemplate redisTemplate;
    //    所有信息并分页
    @GetMapping("/getAll/{index}/{size}")
    public Page<College> getAll(@PathVariable("index") Integer index,
                                   @PathVariable("size") Integer size){

        Page<College> page = new Page<>(index,size);
        return collegeService.page(page);
    }
    //      模糊查询并分页
    @GetMapping("/byName/{name}/{index}/{size}")
    public Page<College> findClazzByName(@PathVariable("name") String name,
                                       @PathVariable("index")Integer index,
                                       @PathVariable("size")Integer size){
        Page<College> page = new Page<>(index,size);
        QueryWrapper<College> w = new QueryWrapper<>();
        return collegeService.page(page,w.like("name",name));
    }

    //通过学院id查询并分页
    @GetMapping("/search/{id}/{index}/{size}")
    public Page<College> searchId(@PathVariable("id")Integer id,
                                  @PathVariable("index") Integer index,
                                  @PathVariable("size")Integer size){
        Page<College> page = new Page<>(index,size);
        QueryWrapper<College> w = new QueryWrapper<>();
        return collegeService.page(page,w.in("id",id));
    }
    //    增
    @PostMapping("/add")
    public boolean addClazz(@RequestBody College clazz){

        return  collegeService.save(clazz);
    }
    //    批量删除
    @DeleteMapping("/{ids}")
    public boolean delClazz(@PathVariable("ids") List<Integer> ids){

        return collegeService.removeBatchByIds(ids);
    }
    //    通过id查
    @GetMapping("/{id}")
    public College findClazzById(@PathVariable("id") int id){
        return  collegeService.getById(id);
    }
    @GetMapping("/redis/{index}/{size}")
    public Page<College> get( @PathVariable("index") Integer index,
                              @PathVariable("size")Integer size){
        Page<College> college =(Page<College>) RedisUtil.redis(redisTemplate, index, size, collegeService, "college_list");

        return  college;
    }
    //    修改
    @PutMapping("/update")
    public boolean updateCla(@RequestBody College college){
        return  collegeService.updateById(college);
    }
}
