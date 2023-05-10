package top.remained.silence.message.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.remained.silence.message.bean.Message;
import top.remained.silence.message.service.MessageService;

import java.util.*;

/**
 * Project：silence
 * Date：2022/9/11
 * Time：20:13
 * Description：TODO
 *
 * @author lmk
 * @version 1.0
 */
@RestController
@RequestMapping("message")
public class MessageController {
    @Autowired
    MessageService messageService;
    //    所有信息并分页
    @GetMapping("/getAll/{index}/{size}")
    public Page<Message> getAll(@PathVariable("index") Integer index,
                                @PathVariable("size") Integer size){

        Page<Message> page = new Page<>(index,size);
        return messageService.page(page);
    }
    //    所有信息并分页
    @GetMapping("/getAll/byFirm/{firmName}/{index}/{size}")
    public Page<Message> getAllByFirm(@PathVariable("firmName") String firmName,
                                      @PathVariable("index") Integer index,
                                      @PathVariable("size") Integer size){
        QueryWrapper<Message> w = new QueryWrapper<>();
        w.eq("firm_name",firmName);
        Page<Message> page = new Page<>(index,size);
        return messageService.page(page,w);
    }
    //      模糊查询并分页
    @GetMapping("/byJob/{job}/{index}/{size}")
    public Page<Message> findClazzByName(@PathVariable("job") String job,
                                         @PathVariable("index")Integer index,
                                         @PathVariable("size")Integer size){
        Page<Message> page = new Page<>(index,size);
        QueryWrapper<Message> w = new QueryWrapper<>();
        return messageService.page(page,w.like("job",job));
    }
    @GetMapping("/byJob/{firmName}/{job}/{index}/{size}")
    public Page<Message> findClazzByFireName(@PathVariable("job") String firmName,
                                         @PathVariable("job") String job,
                                         @PathVariable("index")Integer index,
                                         @PathVariable("size")Integer size){
        Page<Message> page = new Page<>(index,size);
        QueryWrapper<Message> w = new QueryWrapper<>();
        return messageService.page(page,w.eq("firm_name",firmName).like("job",job));
    }
    @GetMapping("/getLocation")
    public Set<Message> searchLocation() {
//        set 去除重复的
        Set<Message> set = new HashSet<>();
        QueryWrapper<Message> w = new QueryWrapper<>();
        w.select("location");
        set.addAll( messageService.list(w));
        return  set;
    }


    //通过location查询并分页 其实是通过前端传的id
    @GetMapping("/search/byLocation/{location}/{index}/{size}")
    public Page<Message> searchId(@PathVariable("location")String location,
                                  @PathVariable("index") Integer index,
                                  @PathVariable("size")Integer size){
        Page<Message> page = new Page<>(index,size);
        QueryWrapper<Message> w = new QueryWrapper<>();
        return messageService.page(page,w.in("location",location));
    }
    //    增
    @PostMapping("/add")
    public boolean addClazz(@RequestBody Message message){

        return  messageService.save(message);
    }
    //    批量删除
    @DeleteMapping("/{ids}")
    public boolean delClazz(@PathVariable("ids") List<Integer> ids){

        return messageService.removeBatchByIds(ids);
    }
    //    通过id查
    @GetMapping("/{id}")
    public Message findClazzById(@PathVariable("id") int id){
        return  messageService.getById(id);
    }
    //    修改
    @PutMapping("/update")
    public boolean updateCla(@RequestBody Message message){
        return  messageService.updateById(message);
    }
}
