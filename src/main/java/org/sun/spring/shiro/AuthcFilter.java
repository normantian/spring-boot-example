package org.sun.spring.shiro;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by tianfei on 17/3/16.
 */
public class AuthcFilter extends FormAuthenticationFilter {

    private static final String MESSAGE = "Access denied.";


    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
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
