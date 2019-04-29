package com.gmail.zagurskaya.web.handler;

import com.gmail.zagurskaya.web.exception.AccessDeniedException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface AccessDeniedHandler {
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException ex) throws IOException;
}
