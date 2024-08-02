package br.com.java.tcc.exceptions;

import br.com.java.tcc.configuration.MessageCodeEnum;
import br.com.java.tcc.configuration.MessageConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestException extends ResponseEntityExceptionHandler {

    @Autowired
    MessageConfiguration messageConfiguration;

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException methodArgumentNotValidException, HttpHeaders httpHeaders, HttpStatus httpStatus, WebRequest webRequest) {
        List<ErrorObject> errors = getErrors(methodArgumentNotValidException);
        CustomErrorResponse customErrorResponse = getErrorResponse(httpStatus, errors);
        return new ResponseEntity<>(customErrorResponse, httpStatus);
    }

    private CustomErrorResponse getErrorResponse(HttpStatus httpStatus, List<ErrorObject> listErrorObject) {
        return CustomErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .message(messageConfiguration.getMessageByCode(MessageCodeEnum.INVALID_REQUEST_DEFAULT_MESSAGE))
                .code(httpStatus.value())
                .status(httpStatus.getReasonPhrase())
                .errors(listErrorObject)
                .build();
    }

    private List<ErrorObject> getErrors(MethodArgumentNotValidException methodArgumentNotValidException) {
        return methodArgumentNotValidException.getBindingResult().getFieldErrors().stream().map(
                error -> ErrorObject.builder()
                        .message(error.getDefaultMessage())
                        .field(error.getField())
                        .parameter(error.getRejectedValue())
                        .build()
                ).collect(Collectors.toList());
    }
}