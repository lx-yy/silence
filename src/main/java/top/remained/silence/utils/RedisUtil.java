package top.remained.silence.utils;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * Project：silence
 * Date：2022/9/13
 * Time：22:27
 * Description：TODO
 *
 * @author lmk
 * @version 1.0
 */
public class RedisUtil {

    public static Page<?> redis(StringRedisTemplate redisTemplate,int index,int size,
                                     IService service,String key){
        Page<?> studentPage = new Page<>(index, size);
        String s = redisTemplate.opsForValue().get(key);
//        缓存中存在
        if (!StringUtils.isEmpty(s)) {
//            string ->json
//            new TypeReference<Page<Student>>(){}) Page<Student>我们定义的复杂类型
            Page<?> page1 = JSONObject.parseObject(s, new TypeReference<Page<?>>(){});
//            返回
            return page1;
        }
//        查询所有数据
        Page<?> page = (Page<?>) service.page(studentPage);
        String s1 = JSON.toJSONString(page);
        redisTemplate.opsForValue().set(key, s1, 100, TimeUnit.SECONDS);
        return page;
    }
}
