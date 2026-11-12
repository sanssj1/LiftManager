package ar.com.liftmanager.controller;
import ar.com.liftmanager.dto.ApiDtos.*;import ar.com.liftmanager.service.AuthService;import jakarta.validation.Valid;import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/auth") public class AuthController {private final AuthService auth;public AuthController(AuthService a){auth=a;}@PostMapping("/login") public LoginResponse login(@Valid @RequestBody LoginRequest r){return auth.login(r);}}
