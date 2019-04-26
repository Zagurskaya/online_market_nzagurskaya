package com.gmail.zagurskaya.web.handler;

import com.gmail.zagurskaya.web.exception.AccessDeniedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import sun.plugin.liveconnect.SecurityContextHelper;
import sun.reflect.generics.repository.AbstractRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginAccessDeniedHandler implements AccessDeniedHandler {

    private static final Logger logger = LogManager.getLogger(AbstractRepository.class);

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException ex) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            logger.info(String.format("%s was trying to access protected resource: % s",
                    auth.getName(),
                    request.getRequestURL()));
        }
        response.sendRedirect(request.getContextPath() + "/403");
    }
}
