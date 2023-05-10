package top.remained.silence.configer;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Project：silence
 * Date：2022/9/1
 * Time：22:29
 * Description：TODO
 *
 * @author lmk
 * @version 1.0
 */
@Configuration
public class MyConfig {

//    配置分页插件
    @Bean
    @Order(2)
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
//    配置swagger
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).
//               RequestHandlerSelectors.basePackage 表明选择扫描那个包
        select().apis(RequestHandlerSelectors.basePackage("top.remained.silence"))
                .build();
    }
    private ApiInfo apiInfo(){
        //配置个人信息 url为访问swagger的地址
        Contact contact=new Contact("lx","http://localhost:8081/swagger-ui/index.html","123456@qq.com");
        return new ApiInfo(
                "lx的SwaggerAPI文档",//标题
                "Api Documentation",//描述
                "1.0", //版本
                "urn:tos",//邮箱
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }
//    获取配置文件里面的内容    低版本druid作数据源时 有时间问题 导致Sql查询异常
//    @ConfigurationProperties("spring.datasource")
//    @Bean
//    public DataSource dataSource() throws SQLException {
//        DruidDataSource druidDataSource = new DruidDataSource();
////        添加监控功能 stat 设置防火墙wall
//        druidDataSource.setFilters("stat,wall");
//
////
////  <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource" init-method="init" destroy-method="close">
////      ...
////      <property name="filters" value="wall"/>
////  </bean>
//
//        return druidDataSource;
//    }
////    给数据源添加监控页功能
//    @Bean
//    public ServletRegistrationBean<StatViewServlet> servletRegistrationBean(){
//        StatViewServlet statViewServlet = new StatViewServlet();
//        ServletRegistrationBean<StatViewServlet> statViewServletServletRegistrationBean = new ServletRegistrationBean<>(statViewServlet, "/druid/*");
//        return statViewServletServletRegistrationBean;
//    }
////    WebStatFilter用于采集web-jdbc关联监控的数据
//    @Bean
//    public FilterRegistrationBean<WebStatFilter>  webStatFilter(){
//        WebStatFilter webStatFilter = new WebStatFilter();
////  	<url-pattern>/*</url-pattern> <filter-name>DruidWebStatFilter</filter-name>
//        FilterRegistrationBean<WebStatFilter> filterRegistrationBean = new FilterRegistrationBean<WebStatFilter>(webStatFilter);
//        //      设置拦截路径
//        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
//        //        静态资源不需要拦截
//        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
//
//
//        return  filterRegistrationBean;
//    }
//    配置跨域
    @Bean
    @Order(1)
    public CorsFilter corsFilter() {
        //1.添加CORS配置信息
        CorsConfiguration config = new CorsConfiguration();
        //1) 允许的域,不要写*，否则cookie就无法使用了
        config.addAllowedOrigin("*");

        //2) 是否发送Cookie信息
//        config.setAllowCredentials(true);
        //3) 允许的请求方式
        config.addAllowedMethod("*");
        config.addAllowedOriginPattern("*");

        // 4）允许的头信息
        config.addAllowedHeader("*");
        //2.添加映射路径，我们拦截一切请求
        UrlBasedCorsConfigurationSource configSource = new
                UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);
        //3.返回新的CorsFilter.
        return new CorsFilter(configSource);
    }



}
