package top.remained.silence.student.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONString;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import top.remained.silence.firm.bean.Firm;
import top.remained.silence.student.bean.Student;
import top.remained.silence.student.service.StudentService;
import top.remained.silence.system.bean.Ech;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Project：silence
 * Date：2022/9/2
 * Time：13:14
 * Description：TODO
 *
 * @author lmk
 * @version 1.0
 */
@RestController
@RequestMapping("student")
@Slf4j
public class StudentController {
    @Autowired
    StudentService studentService;
    @Autowired
    StringRedisTemplate redisTemplate;

//    查询所有信息并分页
    @GetMapping("/getAllStudents/{index}/{size}")
    public    Page<Student>   getAllStudents(
            @PathVariable("index") Integer index,
            @PathVariable("size") Integer size
            ){

        Page<Student> page = new Page<>(index,size);
//   studentService.page(page) 获取的全部信息
    return studentService.page(page);
    }

//  查    搜索中的根据id查询 返回Page
    @GetMapping("/{id}/{index}/{size}")
    public Page<Student> findStudentById1(@PathVariable("id")Integer id,
                                          @PathVariable("index") Integer index,
                                          @PathVariable("size") Integer size) {
        Page<Student> page = new Page<>(index,size);
        QueryWrapper<Student> w = new QueryWrapper<>();
        return studentService.page(page,w.in("id",id));

    }
    //  查    搜索中的根据id查询 返回Student
    @GetMapping("/student/{id}")
    public Student findStudentById2(@PathVariable("id")Integer id){

        return studentService.getById(id);
    }

//    根据名字查 并分页
    @GetMapping("/name/{name}/{index}/{size}")
    public Page<Student> findStudentByName(@PathVariable("name") String name,
                                           @PathVariable("index")Integer index,
                                           @PathVariable("size")Integer size){
        Page<Student> page = new Page<>(index,size);
        QueryWrapper<Student> w = new QueryWrapper<>();
        w.like("name",name);
        return studentService.page(page,w);
    }
//    根据class_id查
    @GetMapping("/getStudentsByClazz/{clazzId}/{index}/{size}")
    public Page<Student> getStudentsByClazz(@PathVariable("clazzId") Integer clazzId,
                                            @PathVariable("index")Integer index,
                                            @PathVariable("size")Integer size){
        QueryWrapper<Student> w = new QueryWrapper<>();

        Page<Student> page = new Page<>(index,size);
        return studentService.page(page,w.in("clazz_id",clazzId));

    }
//
    @GetMapping("/getStudentsByYear/{year}/{index}/{size}")
    public Page<Student> getStudentsByYear(@PathVariable("year") Integer year,
                                            @PathVariable("index")Integer index,
                                            @PathVariable("size")Integer size){
        QueryWrapper<Student> w = new QueryWrapper<>();

        Page<Student> page = new Page<>(index,size);
        return studentService.page(page,w.in("year",year));

    }
//    二者共同作用下进行查询
    @GetMapping("/{clazzId}/{year}/{index}/{size}")
    public Page<Student> getStuByYAndC( @PathVariable("clazzId") Integer clazzId,
                                        @PathVariable("year") Integer year,
                                        @PathVariable("index")Integer index,
                                        @PathVariable("size")Integer size){
        QueryWrapper<Student> w = new QueryWrapper<>();

        Page<Student> page = new Page<>(index,size);
        return studentService.page(page,w.in("year",year).in("clazz_id",clazzId));

    }
//    查询单个学生的全部的信息
    @GetMapping("/detail/{id}")
    public Student getDetail(@PathVariable("id") Integer id){

        return studentService.getDetail(id);
    }

//    增 post请求方式
    @PostMapping("/student")
    public boolean add( @RequestBody Student student){
        redisTemplate.opsForValue().set("student_list","");
        return studentService.save(student);

    }
//    批量增加
    @PostMapping("/students")
    public boolean adds(@RequestBody Student[] students){
        redisTemplate.opsForValue().set("student_list","");
      return   studentService.saveBatch(Arrays.asList(students),100);
    }
//   批量 删
    @DeleteMapping("/{ids}")
    public boolean delStudent(@PathVariable("ids") List<Integer> ids){

            redisTemplate.opsForValue().set("student_list","");
        return studentService.removeBatchByIds(ids);
    }

//    改 put
    @PutMapping("/student")
    public boolean updateStudent(@RequestBody Student student){
        redisTemplate.opsForValue().set("student_list","");
     return    studentService.saveOrUpdate(student);
    }



//    根据信息获取clazz id

    public Integer[] getClazzId(){
        return  studentService.getClazzId();

    }
    //   测试Redis做缓存
    @GetMapping("/get/1/{index}/{size}")
    public    List<Map<String,Object>>  get(
            @PathVariable("index") Integer index,
            @PathVariable("size") Integer size){

        Page<Student> page = new Page<>(index,size);
//给缓存中放的字符串 还要逆转回来才能放进去
        Map<String,Object> map =new HashMap<String,Object>();
        map.put("index",index);
        map.put("size",size);
       List<Map<String,Object>> list= new ArrayList<>();

//        将存的学生集合 准换为学生集合  存为json数据   ： 跨语言 跨平台
//        1.得到缓存内容
        String s = redisTemplate.opsForValue().get("list");
        if (!StringUtils.isEmpty(s)) {
//            说明缓存中有数据 把数据转换为List<Student>
            List<Student> list1 = JSONArray.parseArray(s,Student.class);
            map.put("list",list1);
//            执行缓存

//            缓存[Student(id=7, name=嘻嘻, gender=null, age=null, state=null, clazzId=4, year=2018, tel=null, birth=null, img=null, job=null, clazz=null), Student(id=10, name=lx_yyds, gender=FEMALE, age=20, state=NO, clazzId=1, year=2020, tel=null, birth=null, img=null, job=null, clazz=null), Student(id=20, name=string, gender=MALE, age=0, state=NO, clazzId=1, year=0, tel=0, birth=2022-09-13, img=string, job=string, clazz=null)
            list.add(map);
//            [{size=3, index=1, list=[Student(id=7, name=嘻嘻, gender=null, age=null, state=null, clazzId=4, year=2018, tel=null, birth=null, img=null, job=null, clazz=null), Student(id=10, name=lx_yyds, gender=FEMALE, age=20, state=NO, clazzId=1, year=2020, tel=null, birth=null, img=null, job=null, clazz=null), Student(id=20, name=string, gender=MALE, age=0, state=NO, clazzId=1, year=0, tel=0, birth=2022-09-13, img=string, job=string, clazz=null)]}]:list
//            map.put("list",convert);
            return  list;
        }
//        说明缓存中没有数据
        List<Student> records = studentService.page(page).getRecords();

//       转换为json 放入缓存中
        String list2 = JSON.toJSONString(records);
        map.put("students",records);
        list.add(map);
        redisTemplate.opsForValue().set("students",list2,10, TimeUnit.SECONDS);
//        spring boot 默认是单例的  因为service只有一个实例对象，所以只有this是单例的 上来就锁
//    //   本地锁  synchronized JUC(lock) 只能锁一个进程
//        分布式锁 占位  加锁解锁都要原子性
//        1. 用redis里面的setNX if(lock) 获取缓存 else 休眠100ms重新进入
//        弊端 枷锁之后业务代码执行错误或者物理原因，导致锁没删除，，死锁。 设置过期时间
//        弊端  设置过期时间前挂了呢   设置的时候 NX和EX一起设置
//        弊端  第一个还未删锁 锁过期 第二个进来 然后第二个还没执行完毕 第一个把锁删除了 第三个进来。。。
//         存的值是uuid 删除的时候if一下 如果uuid是自己的则删除
//        UUID.randomUUID().toString(); 弊端 你if判断进去了还没删除，uuid过期了
//        lua脚本
        return list;
    }
    @GetMapping("/get/hutool")
    public List<Student> gethu(){

        String s = redisTemplate.opsForValue().get("list");
//        缓存中存在
        if (!StringUtils.isEmpty(s)) {
//            string ->json
            List<Student> students = (List<Student>) Convert.toList(s);
//            返回
            return  students;
        }
//        查询所有数据
        List<Student> list1 = studentService.list(null);
//        存入redis    json->string
        String s1 = Convert.toStr(list1);
        redisTemplate.opsForValue().set("list",s1,100, TimeUnit.SECONDS);
//        返回

        return list1;
    }
    @GetMapping("/redisStudent/{index}/{size}")
    public Page<Student> getRedis( @PathVariable("index") Integer index,
                                   @PathVariable("size") Integer size) {

        Page<Student> studentPage = new Page<>(index, size);
        String s = redisTemplate.opsForValue().get("student_list");
//        缓存中存在
        if (!StringUtils.isEmpty(s)) {
//            string ->json
//            new TypeReference<Page<Student>>(){}) Page<Student>我们定义的复杂类型
            Page<Student> page1 = JSONObject.parseObject(s, new TypeReference<Page<Student>>(){});
//            返回
            return page1;
        }
//        查询所有数据
        Page<Student> page = studentService.page(studentPage);
        String s1 = JSON.toJSONString(page);
        redisTemplate.opsForValue().set("student_list", s1, 100, TimeUnit.SECONDS);
        return page;

    }
//    获取echarts中的数据
    @GetMapping("/echarts")
    public List<Map<String,Object>> getEcharts(){
        return studentService.getEcharts();
    }
    @GetMapping("/echarts2")
    public List<Ech> getEcharts2(){

        return studentService.getEcharts2();
    }


}
