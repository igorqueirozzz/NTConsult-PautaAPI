package br.com.ntcunsult.NTConsult.exception.api;

import br.com.ntcunsult.NTConsult.exception.PautaException;
import br.com.ntcunsult.NTConsult.exception.SessaoException;
import br.com.ntcunsult.NTConsult.exception.VotoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApplicationControllerAdvice {



    @ExceptionHandler(PautaException.class)
    public ResponseEntity<ApiError> handlerPautaException(PautaException exception){
        ApiError error = new ApiError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(error.getHttpStatus()).body(error);
    }

    @ExceptionHandler(SessaoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> handlerSessaoException(SessaoException exception){
        ApiError error = new ApiError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(error.getHttpStatus()).body(error);
    }

    @ExceptionHandler(VotoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> handlerVotoException(VotoException exception){
        ApiError error = new ApiError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(error.getHttpStatus()).body(error);
    }
}
