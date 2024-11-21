package com.game.config;

import com.game.errors.ForbiddenAccessException;
import com.game.errors.UnauthorizedAccessException;
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
            logger.warn("Unauthorized access attempt: No username in session.");
            throw new UnauthorizedAccessException("Notandi ekki innskráður");
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
                logger.warn("Óleyfilegt að fara inn á /admin/**: Finn ekki admin adminId.");
                throw new ForbiddenAccessException("Það þarf admin leyfi til að komast hingað.");
            }
            logger.info("Access granted to /admin/** for username={}", username);
            return true;
        }

        // Allow access to /supervisor/** only for supervisors
        if (path.startsWith("/supervisor") || path.startsWith("/api/supervisor")) {
            if (isSupervisor == null || !isSupervisor) {
                logger.warn("Það er verið að reyna að komast inn á /supervisor/** eða /api/supervisor/** af username={}", username);
                throw new ForbiddenAccessException("Það þarf að vera skólastjóri til að fara hingað inn.");
            }
            logger.info("leyfi gefið inn á /supervisor/** fyrir supervisor={}", username);
            return true;
        }

        logger.info("Access granted to general path for username={}", username);
        return true; // Allow the request
    }
}