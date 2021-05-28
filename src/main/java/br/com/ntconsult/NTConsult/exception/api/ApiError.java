package br.com.ntconsult.NTConsult.exception.api;

import org.springframework.http.HttpStatus;

public class ApiError {

    private Long timeStamp;
    private HttpStatus httpStatus;
    private String erroMessage;

    public ApiError() {
        super();
    }

    public ApiError(Long timeStamp, HttpStatus code, String erroMessage) {
        super();
        this.timeStamp = timeStamp;
        this.httpStatus = code;
        this.erroMessage = erroMessage;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus code) {
        this.httpStatus = code;
    }

    public String getErroMessage() {
        return erroMessage;
    }

    public void setErroMessage(String erroMessage) {
        this.erroMessage = erroMessage;
    }

}
