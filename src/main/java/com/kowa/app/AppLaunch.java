package com.kowa.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kowa.app.sessionutils.MyFiter;

/**声明po路径 */
@EntityScan("com.kowa.app.po")
/**声明dao层路径*/
@EnableJpaRepositories(basePackages={"com.kowa.app.dao"})
/**SpringBoot启动类注解*/
@SpringBootApplication
public class AppLaunch         {

    public static void main(String[] args) {
        SpringApplication.run(AppLaunch.class, args);
    }
  
    @Bean
    public FilterRegistrationBean testFilterRegistration() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new MyFiter());
       
        return registration;
    }
}
