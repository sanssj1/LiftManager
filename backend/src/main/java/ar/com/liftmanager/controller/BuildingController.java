package ar.com.liftmanager.controller;
import ar.com.liftmanager.dto.ApiDtos.*;import ar.com.liftmanager.service.*;import jakarta.validation.Valid;import org.springframework.web.bind.annotation.*;import java.util.*;
@RestController @RequestMapping("/api/buildings") public class BuildingController {private final BuildingService service;public BuildingController(BuildingService s){service=s;}@GetMapping public List<BuildingView> list(){return service.list();}@PostMapping public BuildingView create(@Valid @RequestBody BuildingRequest r){return service.create(r);}}
