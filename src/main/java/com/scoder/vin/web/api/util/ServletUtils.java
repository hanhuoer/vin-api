package com.scoder.vin.web.api.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public abstract class ServletUtils {

    private static final ObjectMapper jsonMapper = new ObjectMapper();
    protected static final char DIRECTORY_SEPARATOR = '/';
    public static final String ATTRIBUTE_REMOTE_ADDR = "RemoteAddr";
    public static final String ATTRIBUTE_REMOTE_HOST = "RemoteHost";
    public static final String ATTRIBUTE_REMOTE_PORT = "RemotePort";
    public static final String ATTRIBUTE_USER_AGENT = "User-Agent";
    public static final String HEADER_SEPARATOR = " \t\n\r\f,";

    public static String toString(HttpServletRequest request, HttpServletResponse response) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(toString(request));
        buffer.append(toString(response));
        buffer.append(toString(request.getSession()));
        buffer.append(toString(request.getCookies()));
        return buffer.toString();
    }

    public static String toString(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        sb.append("HttpServletRequest:   " + request.getClass().getName() + "\n");
        sb.append("  AuthType:           " + request.getAuthType() + "\n");
        sb.append("  CharacterEncoding:  " + request.getCharacterEncoding() + "\n");
        sb.append("  ContentLength:      " + request.getContentLength() + "\n");
        sb.append("  ContentType:        " + request.getContentType() + "\n");
        sb.append("  ContextPath:        " + request.getContextPath() + "\n");
        sb.append("  LocalAddr:          " + request.getLocalAddr() + "\n");
        sb.append("  LocalName:          " + request.getLocalName() + "\n");
        sb.append("  LocalPort:          " + request.getLocalPort() + "\n");
        sb.append("  Method:             " + request.getMethod() + "\n");
        sb.append("  PathInfo:           " + request.getPathInfo() + "\n");
        sb.append("  PathTranslated:     " + request.getPathTranslated() + "\n");
        sb.append("  Protocol:           " + request.getProtocol() + "\n");
        sb.append("  QueryString:        " + request.getQueryString() + "\n");
        sb.append("  RemoteAddr:         " + request.getRemoteAddr() + "\n");
        sb.append("  RemoteHost:         " + request.getRemoteHost() + "\n");
        sb.append("  RemotePort:         " + request.getRemotePort() + "\n");
        sb.append("  RemoteUser:         " + request.getRemoteUser() + "\n");
        sb.append("  RequestedSessionId: " + request.getRequestedSessionId() + "\n");
        sb.append("  RequestURI:         " + request.getRequestURI() + "\n");
        sb.append("  Scheme:             " + request.getScheme() + "\n");
        sb.append("  ServerName:         " + request.getServerName() + "\n");
        sb.append("  ServerPort:         " + request.getServerPort() + "\n");
        sb.append("  ServletPath:        " + request.getServletPath() + "\n");
        sb.append("  Headers:\n");
        Enumeration<String> headerNames = request.getHeaderNames();
        String h;
        Enumeration<String> headerValues;
        while (headerNames.hasMoreElements()) {
            h = headerNames.nextElement();
            headerValues = request.getHeaders(h);
            sb.append("    " + h + ":");
            while (headerValues.hasMoreElements()) {
                sb.append(" " + headerValues.nextElement());
            }
            sb.append("\n");
        }
        sb.append("  Parameters:\n");
        Enumeration<String> parameterNames = request.getParameterNames();
        String p;
        String[] parameters;
        while (parameterNames.hasMoreElements()) {
            p = parameterNames.nextElement();
            parameters = request.getParameterValues(p);
            sb.append("    " + p + ":");
            for (String parameter : parameters) {
                sb.append(" " + parameter);
            }
            sb.append("\n");
        }
        sb.append("  Content:\n");
        try {
            int readlimit = 100;
            if (request.getInputStream().markSupported()) {
                request.getInputStream().mark(readlimit);
                int c;
                int read = 0;
                while ((c = request.getInputStream().read()) != -1) {
                    read++;
                    sb.append((char) c);
                    if (read >= readlimit) {
                        sb.append("[...]");
                        break;
                    }
                }
                request.getInputStream().reset();
                if (read == 0) {
                    sb.append("[--no content--]");
                }
            }
        } catch (IOException e) {
            sb.append("[--content unreadable: exception occurred--]\n");
        }
        return sb.toString();
    }

    public static String toString(HttpServletResponse response) {
        StringBuilder sb = new StringBuilder();
        sb.append("HttpServletResponse:  " + response.getClass().getName() + "\n");
        sb.append("  BufferSize:         " + response.getBufferSize() + "\n");
        sb.append("  CharacterEncoding:  " + response.getCharacterEncoding() + "\n");
        sb.append("  ContentType:        " + response.getContentType() + "\n");
        sb.append("  Locale:             " + response.getLocale() + "\n");
        sb.append("  Status:             " + response.getStatus() + "\n");
        sb.append("  Headers:\n");
        Collection<String> headerNames = response.getHeaderNames();
        for (String h : headerNames) {
            sb.append("    " + h + ": " + response.getHeader(h) + "\n");
        }
        return sb.toString();
    }

    public static String toString(Cookie[] cookies) {
        if (cookies == null || cookies.length == 0)
            return "";
        StringBuilder sb = new StringBuilder();
        sb.append("Cookie:  " + Cookie.class.getName() + "\n");
        for (int i = 0; i < cookies.length; i++) {
            Cookie cookie = cookies[i];
            sb.append("  Name:    " + cookie.getName() + "\n");
            sb.append("  Value:   " + cookie.getValue() + "\n");
            sb.append("  MaxAge:  " + cookie.getMaxAge() + "\n");
            sb.append("  Domain:  " + cookie.getDomain() + "\n");
            sb.append("  Path:    " + cookie.getPath() + "\n\n");
        }
        return sb.toString();
    }

    public static String toString(HttpSession session) {
        StringBuilder sb = new StringBuilder();
        sb.append("HttpSession:            " + session.getClass().getName() + "\n");
        sb.append("  CreationTime          " + session.getCreationTime() + "\n");
        sb.append("  Id:                   " + session.getId() + "\n");
        sb.append("  LastAccessedTime:     " + session.getLastAccessedTime() + "\n");
        sb.append("  MaxInactiveInterval:  " + session.getMaxInactiveInterval() + "\n");
        sb.append("  ServletContext:       " + session.getServletContext() + "\n");
        sb.append("  Attributes:\n");
        Enumeration<String> attributeNames = session.getAttributeNames();
        String a;
        while (attributeNames.hasMoreElements()) {
            a = attributeNames.nextElement();
            sb.append("    " + a + ": " + session.getAttribute(a) + "\n");
        }
        return sb.toString();
    }

    public static String insertDirectory(String path, String directory, int directoryLevel) throws ServletException {
        int insertIndex = 0;
        for (int i = 0; i < directoryLevel && insertIndex >= 0; i++) {
            insertIndex = path.indexOf(DIRECTORY_SEPARATOR, insertIndex + 1);
        }
        if (insertIndex == -1) {
            throw new ServletException("directoryLevel (" + directoryLevel + ")to high for given url: " + path);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(path.substring(0, insertIndex));
        sb.append(DIRECTORY_SEPARATOR);
        sb.append(directory);
        sb.append(path.substring(insertIndex));
        return sb.toString();
    }

    public static int countDirectories(String path) {
        int dirs = 0;
        for (int i = 1; i < path.length(); i++) {
            if (path.charAt(i) != DIRECTORY_SEPARATOR && path.charAt(i - 1) == DIRECTORY_SEPARATOR) {
                dirs++;
            }
        }
        return dirs;
    }

    public static void copyRequestClientInfo(HttpServletRequest request, HttpSession session) {
        if (request.getHeader(ATTRIBUTE_USER_AGENT) != null) {
            session.setAttribute(ATTRIBUTE_USER_AGENT, request.getHeader(ATTRIBUTE_USER_AGENT));
        }
        if (request.getRemoteAddr() != null) {
            session.setAttribute(ATTRIBUTE_REMOTE_ADDR, request.getRemoteAddr());
        }
        if (request.getRemoteHost() != null) {
            session.setAttribute(ATTRIBUTE_REMOTE_HOST, request.getRemoteHost());
        }
        session.setAttribute(ATTRIBUTE_REMOTE_PORT, request.getRemotePort());
    }

    public static String getRemoteAddr(HttpSession session) {
        if (session.getAttribute(ATTRIBUTE_REMOTE_ADDR).equals(session.getAttribute(ATTRIBUTE_REMOTE_HOST))) {
            return (String) session.getAttribute(ATTRIBUTE_REMOTE_ADDR);
        } else {
            return session.getAttribute(ATTRIBUTE_REMOTE_ADDR) + " (" + session.getAttribute(ATTRIBUTE_REMOTE_HOST) + ")";
        }
    }

    public static String getUserAgent(HttpSession session) {
        return (String) session.getAttribute(ATTRIBUTE_USER_AGENT);
    }

    public static boolean headerContainsValue(HttpServletRequest request, String headerName, String requiredValue, boolean ignoreCase) {
        Enumeration<String> headerValues = request.getHeaders(headerName);
        String value;
        StringTokenizer st;
        String valueToken;
        while (headerValues.hasMoreElements()) {
            value = headerValues.nextElement();
            if (ignoreCase && requiredValue.equalsIgnoreCase(value)) {
                return true;
            } else if (!ignoreCase && requiredValue.equals(value)) {
                return true;
            }
            st = new StringTokenizer(value, HEADER_SEPARATOR);
            if (st.countTokens() > 1) {
                while (st.hasMoreTokens()) {
                    valueToken = st.nextToken();
                    if (ignoreCase && requiredValue.equalsIgnoreCase(valueToken)) {
                        return true;
                    } else if (!ignoreCase && requiredValue.equals(valueToken)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static <T> T readObject(HttpServletRequest request, Class<T> clazz) throws IOException {
        InputStream in = request.getInputStream();
        String charset = request.getCharacterEncoding();
        if (charset == null) {
            charset = "UTF-8";
        }
        T pro = null;
        try {
            pro = readObject(in, charset, clazz);
            return pro;
        } catch (IOException e) {
            throw e;
        }
    }

    public static <T> T readObject(InputStream in, String charset, Class<T> clazz) throws IOException, JsonProcessingException {
        synchronized (jsonMapper) {
            String content = readFullString(in, charset);
            return readObjectFromString(content, clazz);
        }
    }

    public static <T> T readObjectFromString(String content, Class<T> clazz) throws IOException, JsonProcessingException {
        if (content == null || "".equals(content.trim())) {
            return null;
        }
        synchronized (jsonMapper) {
            return jsonMapper.readValue(content, clazz);
        }
    }

    private static String readFullString(InputStream in, String charset) throws IOException {
        byte[] buf = new byte[1024];
        ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
        int len = in.read(buf);
        while (len >= 0) {
            baos.write(buf, 0, len);
            len = in.read(buf);
        }
        buf = baos.toByteArray();
        String str = new String(buf, charset);
        return str;

    }

    public static String writeJson(Object reply, HttpServletResponse response) throws IOException {
        String replyStr = jsonString(reply);
        httpResponse(200, replyStr, "application/json", "utf-8", response);
        return replyStr;
    }

    public static void writeText(String reply, HttpServletResponse response) throws IOException {
        httpResponse(200, reply, "text/plain", "utf-8", response);
    }

    public static void writeJsonCallBack(Object reply, HttpServletResponse response) throws IOException {
        String replyStr = jsonString(reply);
        replyStr = "JSON_CALLBACK(" + replyStr + ")";
        ServletUtils.httpResponse(200, replyStr, "application/json", "utf-8", response);
    }

    private static String jsonString(Object obj) throws IOException, JsonProcessingException {
        synchronized (jsonMapper) {
            return jsonMapper.writeValueAsString(obj);
        }
    }

    private static void httpResponse(int code, String msg, String contentType, String charset, HttpServletResponse response) throws IOException {
        response.setStatus(code);
        if (contentType != null) {
            response.setContentType(contentType);
        }
        response.setCharacterEncoding(charset);
        byte[] bs = msg.getBytes(charset);
        response.setContentLength(bs.length);
        response.getOutputStream().write(bs);
        response.getOutputStream().flush();
    }

    public static Map<String, String> getSearchMap(HttpServletRequest request) {
        String searchKey = request.getParameter("searchKey");
        String searchText = request.getParameter("searchText");
        Map<String, String> searchMap = new HashMap<String, String>();
        if (!StringUtils.isBlank(searchKey) && !StringUtils.isBlank(searchText)) {
            String[] keyArr = StringUtils.split(searchKey, ",");
            for (String key : keyArr) {
                searchMap.put(key.trim(), searchText.trim());
            }
        }
        return searchMap;
    }

}
