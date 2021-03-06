package me.vimt.book;

import me.vimt.book.interceptor.AdminInterceptor;
import me.vimt.book.interceptor.UserInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * User: Tao
 * Email: 562593188@qq.com
 * Time: 2017/4/6 14:49
 * Description:
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        super.addViewControllers(registry);
        registry.addViewController("/").setViewName("forward: /index.html");
        registry.addViewController("/login").setViewName("forward: /login.html");
        registry.addViewController("/register").setViewName("forward: /register.html");
        registry.addViewController("/admin").setViewName("forward: /admin.html");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserInterceptor()).addPathPatterns("/api/**");
        registry.addInterceptor(new AdminInterceptor()).addPathPatterns("/api/admin/**");
        super.addInterceptors(registry);
    }
}
