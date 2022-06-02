package com.kaibatech.gamecatalog.api.v1;

import com.kaibatech.gamecatalog.api.v1.resource.ErrorDetail;
import com.kaibatech.gamecatalog.api.v1.resource.ErrorResponse;
import com.kaibatech.gamecatalog.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    private static final String GAME_SERVICE = "[game-service]";

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ErrorResponse> internalServerErrorHandler(RuntimeException re, HttpServletRequest request) {
        log.error(GAME_SERVICE, re);
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(status).body(buildSimpleErrorResponse(re, status, request));
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ErrorResponse> notFoundErrorHandler(NotFoundException ne, HttpServletRequest request) {
        log.error(GAME_SERVICE, ne);
        HttpStatus status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(buildSimpleErrorResponse(ne, status, request));
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> methodArgumentNotValidExceptionHandle(MethodArgumentNotValidException ex, HttpServletRequest request) {
        log.error(GAME_SERVICE, ex);
        return buildRequestParameterExceptionResponse(ex.getBindingResult(), request);
    }

    @ExceptionHandler({BindException.class})
    public ResponseEntity<ErrorResponse> bindExceptionHandle(BindException ex, HttpServletRequest request) {
        log.error(GAME_SERVICE, ex);
        return buildRequestParameterExceptionResponse(ex, request);
    }

    private ResponseEntity<ErrorResponse> buildRequestParameterExceptionResponse(BindingResult bindingResult, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponse response = ErrorResponse.builder()
                .status(status.value())
                .url(request.getRequestURL().toString())
                .timestamp(Instant.now())
                .errors(buildErrors(bindingResult))
                .build();
        return ResponseEntity.status(status)
                .body(response);
    }

    private List<ErrorDetail> buildErrors(BindingResult bindingResult) {
        if(!bindingResult.getAllErrors().isEmpty()){
            return bindingResult.getFieldErrors().stream().map(fe ->
                    ErrorDetail.builder()
                            .field(fe.getField())
                            .message(fe.getDefaultMessage())
                            .build()).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    private ErrorResponse buildSimpleErrorResponse(RuntimeException re, HttpStatus status, HttpServletRequest request) {
        return ErrorResponse.builder()
                .timestamp(Instant.now())
                .url(request.getRequestURL().toString())
                .status(status.value())
                .message(re.getMessage())
                .build();
    }

}
