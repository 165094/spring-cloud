package com.test.auth.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.DefaultThrowableAnalyzer;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InsufficientScopeException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.web.util.ThrowableAnalyzer;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.io.IOException;
import java.util.Collections;

public class WebResposeExceptionTranslator implements WebResponseExceptionTranslator {

    private ThrowableAnalyzer throwableAnalyzer = new DefaultThrowableAnalyzer();

    private static final Logger LOGGER = LoggerFactory.getLogger(WebResposeExceptionTranslator.class);

    @Override
    public ResponseEntity translate(Exception e) throws Exception {
        LOGGER.error("认证异常", e);
        // 从异常堆栈中提取 SpringSecurityException
        Throwable[] causeChain = throwableAnalyzer.determineCauseChain(e);

        Exception ase = (InvalidGrantException) throwableAnalyzer.getFirstThrowableOfType(InvalidGrantException.class, causeChain);

        if (ase != null) {
            return handleOAuth2Exception(new WebResposeExceptionTranslator
                    .UnauthorizedUserException(e.getMessage(), e));
        }

        ase = (OAuth2Exception) throwableAnalyzer.getFirstThrowableOfType(InvalidTokenException.class, causeChain);

        if (ase != null) {
            return handleOAuth2Exception(new WebResposeExceptionTranslator
                    .ToolsInvalidTokenException(e.getMessage(), e));
        }
        ase = (OAuth2Exception) throwableAnalyzer.getFirstThrowableOfType(OAuth2Exception.class, causeChain);

        if (ase != null) {
            return handleOAuth2Exception(new WebResposeExceptionTranslator
                    .ToolsOAuth2Exception(e.getMessage(), e));
        }

        ase = (AuthenticationException) throwableAnalyzer.getFirstThrowableOfType(AuthenticationException.class,
                causeChain);
        if (ase != null) {
            return handleOAuth2Exception(new WebResposeExceptionTranslator
                    .UnauthorizedClientException(e.getMessage(), e));
        }

        ase = (AccessDeniedException) throwableAnalyzer
                .getFirstThrowableOfType(AccessDeniedException.class, causeChain);
        if (ase != null) {
            return handleOAuth2Exception(new WebResposeExceptionTranslator
                    .ForbiddenException(ase.getMessage(), ase));
        }

        ase = (HttpRequestMethodNotSupportedException) throwableAnalyzer.getFirstThrowableOfType(
                HttpRequestMethodNotSupportedException.class, causeChain);
        if (ase != null) {
            return handleOAuth2Exception(new WebResposeExceptionTranslator
                    .MethodNotAllowed(ase.getMessage(), ase));
        }

        return handleOAuth2Exception(new WebResposeExceptionTranslator
                .ServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e));

    }

    private ResponseEntity handleOAuth2Exception(OAuth2Exception e) throws IOException {

        int status = e.getHttpErrorCode();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cache-Control", "no-store");
        headers.set("Pragma", "no-cache");
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON_UTF8));
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (status == HttpStatus.UNAUTHORIZED.value() || (e instanceof InsufficientScopeException)) {
            headers.set("WWW-Authenticate", String.format("%s %s", OAuth2AccessToken.BEARER_TYPE, e.getSummary()));
        }
        return new ResponseEntity<>(e.getOAuth2ErrorCode(), headers, HttpStatus.valueOf(status));
    }

    @SuppressWarnings("serial")
    private static class ToolsOAuth2Exception extends OAuth2Exception {

        public ToolsOAuth2Exception(String msg, Throwable t) {
            super(msg, t);
        }

        @Override
        public String getOAuth2ErrorCode() {
            return "非法请求";
        }

        @Override
        public int getHttpErrorCode() {
            return 400;
        }

    }

    @SuppressWarnings("serial")
    private static class ToolsInvalidTokenException extends OAuth2Exception {

        public ToolsInvalidTokenException(String msg, Throwable t) {
            super(msg, t);
        }

        @Override
        public String getOAuth2ErrorCode() {
            return "无效令牌";
        }

        @Override
        public int getHttpErrorCode() {
            return 401;
        }

    }

    @SuppressWarnings("serial")
    private static class ForbiddenException extends OAuth2Exception {

        public ForbiddenException(String msg, Throwable t) {
            super(msg, t);
        }

        @Override
        public String getOAuth2ErrorCode() {
            return "禁止访问";
        }

        @Override
        public int getHttpErrorCode() {
            return 403;
        }

    }


    @SuppressWarnings("serial")
    private static class ServerErrorException extends OAuth2Exception {

        public ServerErrorException(String msg, Throwable t) {
            super(msg, t);
        }

        @Override
        public String getOAuth2ErrorCode() {
            return "服务异常";
        }

        @Override
        public int getHttpErrorCode() {
            return 500;
        }

    }

    @SuppressWarnings("serial")
    private static class UnauthorizedClientException extends OAuth2Exception {

        public UnauthorizedClientException(String msg, Throwable t) {
            super(msg, t);
        }

        @Override
        public String getOAuth2ErrorCode() {
            return "客户端认证失败";
        }

        @Override
        public int getHttpErrorCode() {
            return 401;
        }

    }

    @SuppressWarnings("serial")
    private static class UnauthorizedUserException extends OAuth2Exception {

        public UnauthorizedUserException(String msg, Throwable t) {
            super(msg, t);
        }

        @Override
        public String getOAuth2ErrorCode() {
            return "用户认证失败";
        }

        @Override
        public int getHttpErrorCode() {
            return 401;
        }

    }

    @SuppressWarnings("serial")
    private static class MethodNotAllowed extends OAuth2Exception {

        public MethodNotAllowed(String msg, Throwable t) {
            super(msg, t);
        }

        @Override
        public String getOAuth2ErrorCode() {
            return "不支持的请求方法";
        }

        @Override
        public int getHttpErrorCode() {
            return 405;
        }

    }
}
