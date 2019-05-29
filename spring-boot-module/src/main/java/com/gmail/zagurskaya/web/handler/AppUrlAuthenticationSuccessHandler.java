package com.gmail.zagurskaya.web.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

import static com.gmail.zagurskaya.web.constant.RolesConstant.ADMIN;
import static com.gmail.zagurskaya.web.constant.RolesConstant.CONTROLLER;
import static com.gmail.zagurskaya.web.constant.RolesConstant.KASSIR;

public class AppUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger logger = LoggerFactory.getLogger(AppUrlAuthenticationSuccessHandler.class);
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        handle(request, response, authentication);
        clearAuthenticationAttributes(request);
    }

    private void handle(HttpServletRequest request,
                        HttpServletResponse response,
                        Authentication authentication) throws IOException {

        String url = determineTargetUrl(authentication);
        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to" + url);
        }
        redirectStrategy.sendRedirect(request, response, url);
    }

    private String determineTargetUrl(Authentication authentication) {
        boolean isKassir = false;
        boolean isController = false;
        boolean isAdministrator = false;
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals(KASSIR)) {
                isKassir = true;
                break;
            } else if (authority.getAuthority().equals(CONTROLLER)) {
                isController = true;
                break;
            } else if (authority.getAuthority().equals(ADMIN)) {
                isAdministrator = true;
                break;
            }
        }
        if (isKassir) {
            return "/cash";
        } else if (isController) {
            return "/controller";
        } else if (isAdministrator) {
            return "/admin";
        } else {
            throw new IllegalStateException();
        }
    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
