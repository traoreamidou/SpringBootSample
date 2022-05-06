package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/webjars/**")
                .antMatchers("/css/**")
                .antMatchers("/js/**")
                .antMatchers("/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //Set of login unnecessary page
        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/user/signup").permitAll()
                .antMatchers("/user/signup/rest").permitAll()
                .antMatchers("/admin").hasAnyAuthority("ROLE_ADMIN")
                .anyRequest().authenticated();
        
        //Login process
        http.formLogin()
                .loginProcessingUrl("/login")
                .loginPage("/login")
                .failureUrl("/login?error")
                .usernameParameter("userId")
                .passwordParameter("password")
                .defaultSuccessUrl("/user/list", true); 
        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) //used it when you want to send logout request with GET method
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout");
        //http.csrf().disable();
    }

    /**In-memory authentication settings */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = passwordEncoder();
        /*auth.inMemoryAuthentication()
                .withUser("user")
                .password(encoder.encode("user"))
                .roles("GENERAL")
                .and()
                .withUser("admin")
                .password(encoder.encode("admin"))
                .roles("Admin");*/

        //User data unthentication
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
    }
}
