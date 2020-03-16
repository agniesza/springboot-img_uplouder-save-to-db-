package com.agacorporation.springbootimage_uplouder;

import com.agacorporation.springbootimage_uplouder.model.AppUser;
import com.agacorporation.springbootimage_uplouder.repo.AppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private AppUserRepo appUserRepo;

    @Autowired
    public WebSecurityConfig(UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
     //   auth.inMemoryAuthentication().withUser(new User("test", passwordEncoder().encode("test"), Collections.singleton(new SimpleGrantedAuthority("user"))));
    auth.userDetailsService(userDetailsServiceImpl);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.authorizeRequests()
               .antMatchers("/test1").authenticated()
               .antMatchers("/uploadImage").authenticated()
               .and()
               .formLogin().permitAll()
                .and()
               .csrf().disable()
               .headers().disable();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
return new BCryptPasswordEncoder();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void users(){
        AppUser user=new AppUser("usertest", passwordEncoder().encode("usertest"), "user");
        AppUser admin=new AppUser("admintest", passwordEncoder().encode("admintest"), "admin");
            appUserRepo.save(user);
            appUserRepo.save(admin);
        }


}
