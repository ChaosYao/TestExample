/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2021 All Rights Reserved.
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author leping
 * @version $Id: WebAppConfigurer.java, v 0.1 2021-07-04 下午7:37 leping Exp $$
 */
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**");
    }
}
