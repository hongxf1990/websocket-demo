package com.petter.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author hongxf
 * @since 2017-05-10 10:56
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("hongxf").password("123456").roles("USER")
                .and()
                .withUser("ww").password("123456").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**").permitAll()
             .and()
                //开启默认登录页面
                .formLogin().loginPage("/login")
                .defaultSuccessUrl("/chat")
             .and()
                //设置注销
                .logout()
             .and()
                .csrf().disable();
    }
}
