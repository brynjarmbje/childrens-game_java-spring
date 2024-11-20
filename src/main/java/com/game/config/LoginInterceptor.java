package com.game.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();

        // Log the incoming request details
        logger.info("Incoming request: Method={} URI={}", request.getMethod(), request.getRequestURI());

        // Validate username
        String username = (String) session.getAttribute("username");
        if (username == null || username.isEmpty()) {
            logger.warn("Unauthorized access attempt: No username in session. Redirecting to login.");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.sendRedirect("/login");
            return false;
        }

        // Log the user information
        logger.info("User logged in: username={}", username);

        // Get session attributes
        Boolean isSupervisor = (Boolean) session.getAttribute("isSupervisor");
        Long adminId = (Long) session.getAttribute("adminId");

        // Log role details
        logger.info("Session attributes: isSupervisor={} adminId={}", isSupervisor, adminId);

        // Validate access for specific paths
        String path = request.getRequestURI();

        // Allow access to /admin/** for regular admins or supervisors
        if (path.startsWith("/admin")) {
            if (adminId == null) {
                logger.warn("Forbidden access attempt to /admin/**: Missing adminId.");
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.sendRedirect("/login");
                return false;
            }
            logger.info("Access granted to /admin/** for username={}", username);
            return true;
        }

        // Allow access to /supervisor/** only for supervisors
        if (path.startsWith("/supervisor") || path.startsWith("/api/supervisor")) {
            if (isSupervisor == null || !isSupervisor) {
                logger.warn("Forbidden access attempt to /supervisor/** or /api/supervisor/** by username={}", username);
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return false;
            }
            logger.info("Access granted to /supervisor/** for supervisor={}", username);
            return true;
        }

        logger.info("Access granted to general path for username={}", username);
        return true; // Allow the request
    }
}