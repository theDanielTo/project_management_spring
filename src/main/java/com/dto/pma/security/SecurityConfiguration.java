package com.dto.pma.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    BCryptPasswordEncoder bCryptEncoder;

    // Authentication
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "SELECT username, password, enabled " +
                        "FROM user_accounts WHERE username = ?")
                .authoritiesByUsernameQuery(
                        "SELECT username, authority " +
                        "FROM user_accounts WHERE username = ?")
                .passwordEncoder(bCryptEncoder);
//                .withDefaultSchema()
//                .withUser("myuser")
//                    .password("pass")
//                    .roles("USER")
//                .and()
//                .withUser("daniel")
//                    .password("pass2")
//                    .roles("USER")
//                .and()
//                .withUser("managerUser")
//                    .password("pass3")
//                    .roles("ADMIN");
    }

    // Authorization
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/projects/new").hasRole("ADMIN")
                .antMatchers("/projects/save").hasRole("ADMIN")
                .antMatchers("/employees/new").hasRole("ADMIN")
                .antMatchers("/employees/save").hasRole("ADMIN")
                .antMatchers("/", "/**").permitAll()
                .and()
                .formLogin();
        
    }
}
