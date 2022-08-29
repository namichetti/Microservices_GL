package com.userservice.feignclients;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class FeingInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        template.header("Authorization", "Bearer " + jwt.getTokenValue());
    }
}
