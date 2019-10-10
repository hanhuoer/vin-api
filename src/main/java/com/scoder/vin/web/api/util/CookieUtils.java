package com.scoder.vin.web.api.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class CookieUtils {

    private HttpServletResponse response;
    private HttpServletRequest request;
    private String comment;
    private String domain;
    private int maxAge;
    private String path;
    private boolean secure;
    private String encodeCharSet;
    private String decodeCharSet;

    public CookieUtils(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        comment = null;
        domain = null;
        maxAge = 365 * 24 * 60 * 60 * 1000;
        path = "/";
        secure = false;
        encodeCharSet = "8859_1";
        decodeCharSet = "EUC-KR";
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public boolean isSecure() {
        return secure;
    }

    public void setSecure(boolean secure) {
        this.secure = secure;
    }

    public String getEncodeCharSet() {
        return encodeCharSet;
    }

    public void setEncodeCharSet(String encodeCharSet) {
        this.encodeCharSet = encodeCharSet;
    }

    public String getDecodeCharSet() {
        return decodeCharSet;
    }

    public void setDecodeCharSet(String decodeCharSet) {
        this.decodeCharSet = decodeCharSet;
    }

    public void setCookie(String name, String value, Integer maxAge) {
        try {
            value = URLEncoder.encode(value, encodeCharSet);
        } catch (UnsupportedEncodingException e) {
        }
        if (name != null && value != null) {
            Cookie[] c = request.getCookies();
            if (c != null) {
                for (Cookie cookie : c) {
                    String cName = cookie.getName();
                    if (cName.equalsIgnoreCase(name)) {
                        cookie.setMaxAge(0);
                        cookie.setValue(null);
                        response.addCookie(cookie);
                    }
                }
            }
            Cookie cookie = new Cookie(name, String.valueOf(value));
            if (domain != null) {
                cookie.setDomain(domain);
            }
            if (comment != null) {
                cookie.setComment(comment);
            }
            if (maxAge == null) {
                cookie.setMaxAge(this.maxAge);
            } else {
                cookie.setMaxAge(maxAge);
            }
            if (path != null) {
                cookie.setPath(path);
            }
            if (secure) {
                cookie.setSecure(secure);
            }
            response.addCookie(cookie);
        }
    }

    public String getCookie(String name) {
        Cookie cookies[] = request.getCookies();
        if (cookies == null) {
            return "";
        }
        String value = "";
        int i = 0;
        do {
            if (i >= cookies.length) {
                break;
            }
            if (name.equals(cookies[i].getName())) {
                try {
                    value = URLDecoder.decode(cookies[i].getValue(), decodeCharSet);
                } catch (UnsupportedEncodingException e) {
                }
                break;
            }
            i++;
        } while (true);
        return value;
    }

    public Map<String, String> toMap() {
        HashMap<String, String> cookieMap = new HashMap<String, String>();
        Cookie cookies[] = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            return cookieMap;
        }
        for (int idx = 0; idx < cookies.length; idx++) {
            cookieMap.put(cookies[idx].getName(), cookies[idx].getValue());
        }
        return cookieMap;
    }

    public int removeCookie(String name) {
        int i = 0;
        if (request == null) {
            return i;
        }
        Cookie acookie[] = request.getCookies();
        if (acookie == null) {
            return i;
        }
        if (name == null) {
            for (int j = 0; j < acookie.length; j++) {
                if (domain != null) {
                    acookie[j].setDomain(domain);
                }
                acookie[j].setMaxAge(0);
                acookie[j].setPath("/");
                response.addCookie(acookie[j]);
                i++;
            }
        } else {
            for (int k = 0; k < acookie.length; k++) {
                Cookie cookie = acookie[k];
                if (!name.equalsIgnoreCase(cookie.getName())) {
                    continue;
                }
                if (domain != null) {
                    cookie.setDomain(domain);
                }
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
                i++;
            }
        }
        return i;
    }

}