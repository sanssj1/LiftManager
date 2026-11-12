package ar.com.liftmanager.config;
import ar.com.liftmanager.service.BusinessException; import ar.com.liftmanager.dto.ApiDtos.MessageResponse;
import org.springframework.http.*; import org.springframework.web.bind.MethodArgumentNotValidException; import org.springframework.web.bind.annotation.*;
@RestControllerAdvice
public class ApiExceptionHandler {
 @ExceptionHandler(BusinessException.class) ResponseEntity<MessageResponse> business(BusinessException ex){return ResponseEntity.status(ex.getStatus()).body(new MessageResponse(ex.getMessage()));}
 @ExceptionHandler(MethodArgumentNotValidException.class) ResponseEntity<MessageResponse> validation(MethodArgumentNotValidException ex){String msg=ex.getBindingResult().getFieldErrors().stream().findFirst().map(e->e.getField()+": "+e.getDefaultMessage()).orElse("Datos inválidos");return ResponseEntity.badRequest().body(new MessageResponse(msg));}
 @ExceptionHandler(Exception.class) ResponseEntity<MessageResponse> other(Exception ex){return ResponseEntity.status(500).body(new MessageResponse("Error interno. Revisá los datos e intentá nuevamente."));}
}
