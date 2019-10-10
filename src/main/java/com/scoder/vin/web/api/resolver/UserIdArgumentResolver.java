package com.scoder.vin.web.api.resolver;

import com.scoder.vin.web.api.annotation.UserId;
import com.scoder.vin.web.api.security.JwtHelper;
import com.scoder.vin.web.api.util.CookieUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * @author H
 */
@Component
@Slf4j
public class UserIdArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(UserId.class) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {

        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        String token = request.getHeader("X-Auth-Token");
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter("X-Auth-Token");
        }
        if (StringUtils.isEmpty(token)) {
            token = new CookieUtils(request, null).getCookie("X-Auth-Token");
        }
        if (StringUtils.isEmpty(token)) {
            token = request.getHeader("token");
        }
        if (!StringUtils.isEmpty(token)) {
            try {
                Jws<Claims> claimsJws = JwtHelper.tokenParse(token);
                log.info("[vin-resolver] token parse result: [{}]", claimsJws.getBody());
                return Long.valueOf(String.valueOf(claimsJws.getBody().get("userId")));
            } catch (Exception e) {
                log.info("[vin-resolver] error: [{}]", e.getMessage());
                return null;
            }
        }
        return null;
    }

}