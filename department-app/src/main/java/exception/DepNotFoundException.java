package exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such Department") 
public class DepNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -1216499010355374130L;
	
}
