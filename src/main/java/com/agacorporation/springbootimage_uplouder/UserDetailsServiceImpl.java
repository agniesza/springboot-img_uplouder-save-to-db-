package com.agacorporation.springbootimage_uplouder;

import com.agacorporation.springbootimage_uplouder.model.AppUser;
import com.agacorporation.springbootimage_uplouder.repo.AppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AppUserRepo appUserRepo;

    @Autowired
    private PasswordEncoder paswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserRepo.findByUsername(username);
    }


    @EventListener(ApplicationReadyEvent.class) //wywola sie zawsze gdy apka wystaruje
    public void addTestUser(){
        AppUser appUser=new AppUser("aga", paswordEncoder.encode("aga"), "user");
        appUserRepo.save(appUser);
    }
}
