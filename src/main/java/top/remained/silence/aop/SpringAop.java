package top.remained.silence.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.remained.silence.student.service.StudentService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Project：silence
 * Date：2022/10/13
 * Time：19:53
 * Description：TODO
 *
 * @author lmk
 * @version 1.0
 */

@Component
@Aspect
@Slf4j
public class SpringAop {
//    第一个* 权限修饰符 类 .*(..) 所有方法
   Date date = new Date();
//    "execution(public int com.atguigu.spring.aop.annotation.CalculatorImpl.add(int,int))"
    @Pointcut("execution(* top.remained.silence.firm.controller.FirmController.*(..))")
    public void cut1(){};
    //使用环绕通知
    @Around("cut1()")
    public Object myLogger(ProceedingJoinPoint pjp) throws Throwable {
        date.setTime(System.currentTimeMillis());
        //使用ServletRequestAttributes请求上下文获取方法更多
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String className = pjp.getSignature().getDeclaringTypeName();
        String methodName = pjp.getSignature().getName();
        //使用数组来获取参数
        Object[] array = pjp.getArgs();
        ObjectMapper mapper = new ObjectMapper();
        //执行函数前打印日志
        log.info("调用前：{}：{},传递的参数为：{}", className, methodName, mapper.writeValueAsString(array));
        log.info("URL:{}", request.getRequestURL().toString());
        log.info("IP地址：{}", request.getRemoteAddr());
        //调用整个目标函数执行
        Object obj = pjp.proceed();
        //执行函数后打印日志
        log.info("调用后：{}：{},返回值为：{}", className, methodName, mapper.writeValueAsString(obj));
        log.info("耗时：{}ms", System.currentTimeMillis() - date.getTime());
        return obj;
    }

}
