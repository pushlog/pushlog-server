package eu.gebes.pushlogBackend.routes;

import eu.gebes.pushlogBackend.response.AuthException;
import eu.gebes.pushlogBackend.response.BadRequestException;
import eu.gebes.pushlogBackend.response.ErrorResponse;
import eu.gebes.pushlogBackend.response.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public Object processValidationError(NotFoundException ex) {
        return new ErrorResponse(ex.getError());
    }

    @ExceptionHandler(AuthException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public Object processValidationError(AuthException ex) {
        return new ErrorResponse(ex.getError());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Object processValidationError(BadRequestException ex) {
        return new ErrorResponse(ex.getError());
    }

}
