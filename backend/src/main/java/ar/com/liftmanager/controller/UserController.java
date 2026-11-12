package ar.com.liftmanager.controller;
import ar.com.liftmanager.dto.ApiDtos.*;import ar.com.liftmanager.service.UserService;import jakarta.validation.Valid;import org.springframework.web.bind.annotation.*;import java.util.*;
@RestController @RequestMapping("/api/users") public class UserController {private final UserService service;public UserController(UserService s){service=s;}@GetMapping public List<UserView> list(){return service.list();}@PostMapping public UserView create(@Valid @RequestBody UserRequest r){return service.create(r);}}
