package edu.zju.ccnt.health.admin.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //允许全部请求跨域
        registry.addMapping("/**")
                .allowedMethods("POST", "GET","PUT","OPTIONS","DELETE")
                .allowedHeaders("*")
                .allowCredentials(true)
                .allowedOrigins("http://localhost:8080");
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加拦截器
        registry.addInterceptor(new JwtInterceptor()).excludePathPatterns("/admin/login");
    }
}
