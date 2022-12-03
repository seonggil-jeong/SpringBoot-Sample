package com.example.spring.app.controller.support;

import com.example.spring.security.impl.JwtAuthTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;


@Slf4j
public class ControllerSupport {


    /**
     * get userName from Authentication
     * @return
     */
    public String getUserId() {
        final String userId = this.getAuthentication().getName();

        if (userId == null) {
            return "";
        }

        return userId;
    }


    /**
     * get Authentication from token
     * @return
     */
    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}
