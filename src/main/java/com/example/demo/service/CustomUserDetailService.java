package com.example.demo.service;

import com.example.demo.entity.User.Users;
import com.example.demo.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    final LoginRepository loginRepository;

    // 유저 이름으로 데이터 가져오고 있는지 없는지에 따라서 반환 값이 다름.
    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {

        Users users = loginRepository.findByUserEmail(userEmail);

        if(users == null)
            throw new UsernameNotFoundException("계정을 찾지 못했습니다.");

        return loginRepository.findByUserEmail(userEmail);
    }

    // 해당하는 User의 데이터가 존재하면 UserDetials 객체로 만들어서 리턴
    // 여기서 UserDetail은 저희가 만든 User랑 완전히 다른 Spring security 내에서 제공하는 user 객체입니다.
    // 해당에는 Authorities, Username, Password가 들어가 있습니다. user에 권한을 위해서
    // 일단 USER랑 ADMIN이란 이름으로 Enum 하나를 팠고요.
    // 해당 권한 설정을 Collection에 담아서 Entity에 userDetails를 Extends하면 그 권한이 들어가요.
    private UserDetails createUserDetails(Users users){
        return new User(users.getUsername(), users.getUserPassword(), users.getAuthorities());
    }
}
