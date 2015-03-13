package funmarket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.Locale;

@ControllerAdvice
public class ValidationAdvice {

    private static final String ERROR_TEMPLATE = "'%s' %s";

    private MessageSource messageSource;

    @Autowired
    public ValidationAdvice(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public void onValidationError(ConstraintViolationException ex, HttpServletResponse response) throws IOException {
        /*
        BindingResult result = ex.getBindingResult();
        FieldError fieldError = result.getFieldError();
        if (fieldError != null) {
            sendError(response, fieldError.getField(), fieldError);
            return;
        }
        ObjectError objectError = result.getGlobalError();
        if (objectError != null) {
            sendError(response, objectError.getObjectName(), objectError);
            return;
        }
        */
        ConstraintViolation<?> next = ex.getConstraintViolations().iterator().next();
        response.sendError(HttpStatus.BAD_REQUEST.value(), next.getPropertyPath() + " - " + next.getMessage());
    }

    private void sendError(HttpServletResponse response, String name, MessageSourceResolvable source) throws IOException {
        String message = messageSource.getMessage(source, Locale.US);
        response.sendError(HttpStatus.BAD_REQUEST.value(), String.format(ERROR_TEMPLATE, name, message));
    }
}