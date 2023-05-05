package uitc.com.plant.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import uitc.com.plant.exception.BasdRequestException;

// ru
@ControllerAdvice("uitc.com.plant.controller")
public class ExceptionHandleController {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> handleMethodArgumentNotValidException(
			MethodArgumentNotValidException e
	) {
		BindingResult result = e.getBindingResult();
		StringBuilder builder = new StringBuilder();

		for (FieldError error : result.getFieldErrors()) {

			builder.append(error.getField()).append(":").append(error.getDefaultMessage());
			builder.append(" ");
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(builder.toString());
	}

	@ExceptionHandler(BasdRequestException.class)
	public ResponseEntity<String> handleBadRequestException(
			BasdRequestException e
	) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}

}
