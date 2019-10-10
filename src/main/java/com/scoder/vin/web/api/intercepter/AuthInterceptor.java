package com.scoder.vin.web.api.intercepter;

import com.scoder.vin.web.api.annotation.IgnoreAuth;
import com.scoder.vin.web.api.common.message.Response;
import com.scoder.vin.web.api.security.JwtHelper;
import com.scoder.vin.web.api.system.Constant;
import com.scoder.vin.web.api.system.Router;
import com.scoder.vin.web.api.util.CookieUtils;
import com.scoder.vin.web.api.util.ServletUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author H
 */
@Component
@Slf4j
public class AuthInterceptor extends HandlerInterceptorAdapter {

    public AuthInterceptor() {
        super();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            // uri
            String uri = request.getServletPath();


            if (!uri.contains(Router.API_VIN_WEB)) {
                log.info("[vin-interceptor] other-uri: [{}]", uri);
                return true;
            } else {
                log.info("[vin-interceptor] vin-uri: [{}]", uri);
                // @IgnoreAuth
                HandlerMethod methodHandler = (HandlerMethod) handler;
                IgnoreAuth ignoreAnnotation = methodHandler.getMethodAnnotation(IgnoreAuth.class);

                // get token by request
                String token = request.getHeader("X-Auth-Token");
                if (ignoreAnnotation == null && StringUtils.isEmpty(token)) {
                    token = request.getParameter("X-Auth-Token");
                }
                if (ignoreAnnotation == null && StringUtils.isEmpty(token)) {
                    token = new CookieUtils(request, null).getCookie("X-Auth-Token");
                }
                if (ignoreAnnotation == null && StringUtils.isEmpty(token)) {
                    System.out.println(String.format("Please carry token in the request. uri: [%s], token: [%s]", uri, token));
                    ServletUtils.writeJson(Response.success(
                            Constant.Auth_TOKEN_IS_NOT_EXISTS.code(),
                            Constant.Auth_TOKEN_IS_NOT_EXISTS.message()
                    ), response);
                    return false;
                }

                if (ignoreAnnotation == null && !StringUtils.isEmpty(token)) {
                    // jwt check
                    try {
                        Jws<Claims> claimsJws = JwtHelper.tokenParse(token);
                        log.info("[vin-interceptor] jwt token parse: [{}]", claimsJws);
                        return true;
                    } catch (Exception e) {
                        // exception
                        ServletUtils.writeJson(Response.success(
                                Constant.AUTH_TOKEN_EXCEPTION.code(),
                                Constant.AUTH_TOKEN_EXCEPTION.message()
                        ), response);
                        return false;
                    }
                }
            }

        }
        return true;
    }

    private void writeMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("sessionstatus", "timeout");
        ServletUtils.writeJson(40000, response);
    }

    private void writeMessage(HttpServletRequest request, HttpServletResponse response, Response responseData) throws IOException {
        response.setHeader("sessionstatus", "timeout");
        ServletUtils.writeJson(responseData, response);
    }
}
