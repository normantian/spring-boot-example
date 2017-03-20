package com.example.config;

import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tianfei on 17/3/16.
 */
public class RoleAuthzFilter extends RolesAuthorizationFilter {

    private static final String MESSAGE = "role access denied.";


    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        HttpServletResponse httpResponse ;
        try {
            httpResponse = WebUtils.toHttp(response);
        }
        catch (ClassCastException ex) {
            // Not a HTTP Servlet operation
            return super.onAccessDenied(request, response) ;
        }

        if (MESSAGE == null) {

            httpResponse.sendError(HttpStatus.FORBIDDEN.value());
        } else {
            httpResponse.sendError(403, MESSAGE);
        }
        return false;  // No further processing.
    }
}
