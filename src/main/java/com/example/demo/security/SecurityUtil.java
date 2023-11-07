package com.example.demo.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    public static String getCurrentUserEmail(){
        // 토큰을 맏았을 때 처리하는 클래스, 해당 클래스에서 authentication이 없거나 정보가 없으면 에러를 내보냄.
        // 정보가 있으면? 이름을 받아냄.
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || authentication.getName() == null){
            throw new RuntimeException("No authentication information.");
        }

        return authentication.getName();
    }
}
