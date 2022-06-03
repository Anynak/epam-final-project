package com.epam.amazingServlet.controller.filters;

import com.epam.amazingServlet.controller.filters.security.SecurityUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        if (SecurityUtils.hasPublicPermission(req)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            if (!SecurityUtils.isAuthorized(req)) {
                resp.sendRedirect(req.getContextPath() + "/auth");
            } else {
                if (!SecurityUtils.hasInternalPermission(req)) {
                    resp.sendError(403);
                } else {
                    filterChain.doFilter(servletRequest, servletResponse);
                }
            }
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
