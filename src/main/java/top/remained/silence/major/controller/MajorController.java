package top.remained.silence.major.controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import top.remained.silence.major.bean.Major;
import top.remained.silence.major.service.MajorService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Project：silence
 * Date：2022/9/10
 * Time：15:02
 * Description：TODO
 *
 * @author lmk
 * @version 1.0
 */
@RestController
@RequestMapping("major")
@Slf4j
public class MajorController {
    @Autowired
    MajorService majorService;
    @Autowired
    StringRedisTemplate redisTemplate;
    @GetMapping("/getAll/{index}/{size}")
    public Map<String,Object> getAll( @PathVariable("index") Integer index,
                                     @PathVariable("size") Integer size,
                                    @RequestParam(required = false) String name){
        if ( index == 0) {
            index=1;
        }
        if (size == 0) {
            size = 3;
        }
        long count = majorService.count();
        long finalPage =  count%size==0 ? count/size: count/size +1;
        List<Major> majors = majorService.getAll(index, size,name);
        System.out.println(majors.toString());
        Map<String,Object> map =  new HashMap<>();
         map.put("index",index);
         map.put("size",size);
         map.put("count",count);
         map.put("finalPage",finalPage);
         map.put("majors",majors);
        return map;
    }
    @GetMapping("/redis/getAll/{index}/{size}")
    public Map<String,Object> getAll( @PathVariable("index") Integer index,
                                      @PathVariable("size") Integer size){
        String s = redisTemplate.opsForValue().get("major_list");
//        缓存中存在
        if (!StringUtils.isEmpty(s)) {
//            string ->json
//            new TypeReference<Page<Student>>(){}) Page<Student>我们定义的复杂类型
            Map<String,Object>  map1 = JSONObject.parseObject(s, new TypeReference<Map<String,Object> >(){});
//            返回
            return map1;
        }

        if ( index == 0) {
            index=1;
        }
        if (size == 0) {
            size = 3;
        }
        long count = majorService.count();
        long finalPage =  count%size==0 ? count/size: count/size +1;
        List<Major> majors = majorService.getAll(index, size,null);
        System.out.println(majors.toString());
        Map<String,Object> map =  new HashMap<>();
        map.put("index",index);
        map.put("size",size);
        map.put("count",count);
        map.put("finalPage",finalPage);
        map.put("majors",majors);
        String s1 = JSON.toJSONString(map);
        redisTemplate.opsForValue().set("major_list", s1, 100, TimeUnit.SECONDS);
        return map;
    }


}
