package com.benjsoft;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {
    @GetMapping("/getUserData")
    public String getUserData(HttpServletRequest request, HttpSession session) {
        String sessionId = request.getSession().getId();

        String username = (String) session.getAttribute("username");
        return "User data for session ID " + sessionId + ": " + username;
    }
}
