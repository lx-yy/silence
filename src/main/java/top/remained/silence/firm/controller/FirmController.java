package top.remained.silence.firm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import top.remained.silence.college.bean.College;
import top.remained.silence.firm.bean.Firm;
import top.remained.silence.firm.service.FirmService;
import top.remained.silence.utils.RedisUtil;

import java.util.List;

/**
 * Project：silence
 * Date：2022/9/14
 * Time：19:09
 * Description：TODO
 *
 * @author lmk
 * @version 1.0
 */
@RestController
@RequestMapping("firm")
public class FirmController {
    @Autowired
    FirmService firmService;
    @Autowired
    StringRedisTemplate redisTemplate;
    //    所有信息并分页
    @GetMapping("/getAll/{index}/{size}")
    public Page<Firm> getAll(@PathVariable("index") Integer index,
                                @PathVariable("size") Integer size){

        Page<Firm> page = new Page<>(index,size);
        return firmService.page(page);
    }
    //      模糊查询并分页
    @GetMapping("/byName/{name}/{index}/{size}")
    public Page<Firm> findClazzByName(@PathVariable("name") String name,
                                         @PathVariable("index")Integer index,
                                         @PathVariable("size")Integer size){
        Page<Firm> page = new Page<>(index,size);
        QueryWrapper<Firm> w = new QueryWrapper<>();
        return firmService.page(page,w.like("name",name));
    }

    //通过学院id查询并分页
    @GetMapping("/search/{id}/{index}/{size}")
    public Page<Firm> searchId(@PathVariable("id")Integer id,
                                  @PathVariable("index") Integer index,
                                  @PathVariable("size")Integer size){
        Page<Firm> page = new Page<>(index,size);
        QueryWrapper<Firm> w = new QueryWrapper<>();
        return firmService.page(page,w.in("id",id));
    }
    //    增
    @PostMapping("/add")
    public boolean addClazz(@RequestBody Firm firm){

        return  firmService.save(firm);
    }
    //    批量删除
    @DeleteMapping("/{ids}")
    public boolean delClazz(@PathVariable("ids") List<Integer> ids){

        return firmService.removeBatchByIds(ids);
    }
    //    通过id查
    @GetMapping("/{id}")
    public Firm findClazzById(@PathVariable("id") int id){
        return  firmService.getById(id);
    }
    @GetMapping("/redis/{index}/{size}")
    public Page<Firm> get( @PathVariable("index") Integer index,
                              @PathVariable("size")Integer size){

        Page<Firm> page =(Page<Firm>) RedisUtil.redis(redisTemplate, index, size, firmService, "firm_list");

        return  page;
    }
    //    修改
    @PutMapping("/update")
    public boolean updateCla(@RequestBody Firm college){
        return  firmService.updateById(college);
    }

}
