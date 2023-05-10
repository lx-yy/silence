package top.remained.silence.log.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;
import top.remained.silence.log.bean.Log;
import top.remained.silence.log.service.LogService;

import java.sql.Date;
import java.util.List;

/**
 * Project：silence
 * Date：2022/9/16
 * Time：15:22
 * Description：TODO
 *
 * @author lmk
 * @version 1.0
 */
@RestController
@RequestMapping("log")
@Slf4j
public class LogController {
    @Autowired
    LogService logService;

    @PostMapping("/add")
    public boolean addLog(@RequestBody Log log) {
        QueryWrapper<Log> w = new QueryWrapper<>();
        w.eq("firm_name", log.getFirmName()).eq("student_id",log.getStudentId());

//      判断该公司是否投递过
        try {
            if (logService.getOne(w).getId() > 0) {
                return false;
            }
        } catch (Exception e) {
            log.setTime1(new Date(System.currentTimeMillis()));
            return logService.save(log);
        }
//        设置添加时间
        return true;

    }

    //    批量添加
    @Transactional
    @PostMapping("/adds")
    public String addLogs(@RequestBody List<Log> logs) {
        if (logs.size()==0) {
            return "你并没有选中！！";
        }
        for (Log log:logs
             ) {
            QueryWrapper<Log> w = new QueryWrapper<>();
            w.eq("firm_name", log.getFirmName()).eq("student_id",log.getStudentId());
            //      判断该公司是否投递过
            if (  logService.getOne(w) != null) {
                // 手动抛异常进行回滚 抛异常也能执行下面return
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return log.getFirmName()+"你已经投过简历";
                }
            //    设置添加时间
            log.setTime1(new Date(System.currentTimeMillis()));
            logService.save(log);
        }
        return "true";
    }

    //    获取某个学生的投递记录
    @GetMapping("/getAll/{studentId}/{index}/{size}")
    public Page<Log> getAll(@PathVariable("studentId") Long studentId,
                            @PathVariable("index") Integer index,
                            @PathVariable("size") Integer size) {
        Page<Log> page = new Page<>(index, size);
        QueryWrapper<Log> w = new QueryWrapper<>();
        w.eq("student_id", studentId);
        return logService.page(page, w);
    }

    //    某个学生的记录 并且通过公司名字进行筛选过
    @GetMapping("/getAll/byStudent/{studentId}/{firmName}/{index}/{size}")
    public Page<Log> getAllbyStudent(@PathVariable("studentId") Long studentId,
                                     @PathVariable("firmName") String firmName,
                                     @PathVariable("index") Integer index,
                                     @PathVariable("size") Integer size) {
        Page<Log> page = new Page<>(index, size);
        QueryWrapper<Log> w = new QueryWrapper<>();
        w.eq("student_id", studentId).like("firm_name", firmName);
        return logService.page(page, w);
    }
    //    某个学生的记录 并且通过学生名字进行筛选
    @GetMapping("/getAll/byFirm/{firmName}/{studentName}/{index}/{size}")
    public Page<Log> getAllbyFirm(@PathVariable("firmName") String firmName,
                                     @PathVariable("studentName") String studentName,
                                     @PathVariable("index") Integer index,
                                     @PathVariable("size") Integer size) {
        Page<Log> page = new Page<>(index, size);
        QueryWrapper<Log> w = new QueryWrapper<>();
        w.eq("student_name", studentName).like("firm_name", firmName);
        return logService.page(page, w);
    }

    //    获取某个公司的投递记录
    @GetMapping("/getAll/firm/{firmName}/{index}/{size}")
    public Page<Log> getAllByFirm(@PathVariable("firmName") String firmName,
                                  @PathVariable("index") Integer index,
                                  @PathVariable("size") Integer size) {
        Page<Log> page = new Page<>(index, size);
        QueryWrapper<Log> w = new QueryWrapper<>();
        w.eq("firm_name", firmName);
        return logService.page(page, w);
    }
//    获取所有记录
        @GetMapping("/getAll/{index}/{size}")
        public Page<Log> getAll(@PathVariable("index") Integer index,
                                @PathVariable("size") Integer size) {
            Page<Log> page = new Page<>(index, size);
            return logService.page(page);
        }
    //   通过学生名字进行搜索 获取所有记录
        @GetMapping("/getAll/firmName/{firmName}/{index}/{size}")
        public Page<Log> getAll(@PathVariable("firmName") String firmName,
                                @PathVariable("index") Integer index,
                                @PathVariable("size") Integer size) {
            QueryWrapper<Log> w = new QueryWrapper<>();
            w.like("firm_name", firmName);
            Page<Log> page = new Page<>(index, size);
            return logService.page(page,w);
        }
    //   通过学生名字进行搜索 获取所有记录
    @GetMapping("/getAll/studentName/{searchStudentName}/{index}/{size}")
    public Page<Log> getAllByStudentName(@PathVariable("searchStudentName") String searchStudentName,
                            @PathVariable("index") Integer index,
                            @PathVariable("size") Integer size) {
        QueryWrapper<Log> w = new QueryWrapper<>();
        w.like("student_name", searchStudentName);
        Page<Log> page = new Page<>(index, size);
        return logService.page(page,w);
    }


}
