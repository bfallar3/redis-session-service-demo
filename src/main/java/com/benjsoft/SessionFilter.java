package com.benjsoft;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

@Component
public class SessionFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        // Validate the Session-Id header is present in all request
        String sessionId = request.getHeader("Session-Id");
        if(sessionId == null || !sessionId.equalsIgnoreCase(request.getSession().getId())) {
            String errorMessage = "User is not authorized to consume this service, session is not valid";
            PrintWriter out = response.getWriter();
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            out.print(errorMessage);
            out.flush();
            return;
        }

        chain.doFilter(request, response);
    }
}
