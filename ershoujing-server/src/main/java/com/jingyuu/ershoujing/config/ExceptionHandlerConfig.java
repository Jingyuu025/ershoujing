package com.jingyuu.ershoujing.config;

import com.jingyuu.ershoujing.common.exception.JyuException;
import com.jingyuu.ershoujing.common.statics.enums.ErrorEnum;
import com.jingyuu.ershoujing.web.response.BaseResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.OK;

/**
 * @author owen
 * @date 2017-08-11
 */
@Slf4j
@ControllerAdvice
public class ExceptionHandlerConfig extends ResponseEntityExceptionHandler {
    @ExceptionHandler({
            JyuException.class,
            MultipartException.class
    })
    public final ResponseEntity<Object> handleBizException(Exception ex, WebRequest request) {
        if (ex instanceof JyuException) {
            return handleExceptionInternal(
                    ex,
                    BaseResp.fail(((JyuException) ex).getErrCode(), ex.getMessage()),
                    null,
                    OK,
                    request
            );
        } else if (ex instanceof MultipartException) {
            return handleExceptionInternal(
                    ex,
                    BaseResp.fail(ErrorEnum.UPLOAD_SIZE_EXCEEDED.getErrCode(), ErrorEnum.UPLOAD_SIZE_EXCEEDED.getErrMessage()),
                    null,
                    OK,
                    request
            );
        }
        return handleException(ex, request);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(
                ex,
                BaseResp.fail(ErrorEnum.ARGS_IS_ERROR.getErrCode(),
                        ex.getFieldError().getDefaultMessage()),
                headers,
                OK,
                request
        );
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(
                ex,
                BaseResp.fail(ErrorEnum.ARGS_IS_ERROR.getErrCode(),
                        ex.getBindingResult().getAllErrors().get(0).getDefaultMessage()),
                headers,
                OK,
                request
        );
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(
                ex,
                BaseResp.fail(ErrorEnum.HTTP_MESSAGE_NOT_READABLE.getErrCode(),
                        ErrorEnum.HTTP_MESSAGE_NOT_READABLE.getErrMessage()
                ),
                headers,
                OK,
                request
        );
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }
}
