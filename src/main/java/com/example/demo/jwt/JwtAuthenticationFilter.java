package com.example.demo.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// JWT 인증을 위해 설치하는 Filter
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_TYPE = "Bearer";

    final JwtTokenProvider jwtTokenProvider;
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 헤더에서 JWT를 받아옴.
        String token = resolveToken((HttpServletRequest) request);

        // 유효한 토큰인지 확인
        if(token != null && jwtTokenProvider.validateToken(token)){
            // 토큰이 유효할 경우 토큰에서 Authentication 객체를 가지고 와서 SecurityContext에 저장
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }

    // Request Header에서 토큰 정보 추출
    private String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        // 헤더 형식에 "bearer 토큰"형태에 맞는지에 따라서 인 듯.
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_TYPE)){
            return bearerToken.substring(7);
        }
        return null;
    }
}
