package ar.com.liftmanager.dto;
import jakarta.validation.constraints.*; import java.math.BigDecimal; import java.time.*; import ar.com.liftmanager.model.*;
public final class ApiDtos {
 private ApiDtos() {}
 public record LoginRequest(@NotBlank @Email String email,@NotBlank String password) {}
 public record LoginResponse(String token,Long id,String name,String email,Role role) {}
 public record UserRequest(@NotBlank @Size(max=120) String name,@NotBlank @Email String email,@NotBlank @Size(min=12,max=100) String password,@NotNull Role role,Long buildingId) {}
 public record UserView(Long id,String name,String email,Role role,boolean active,Long buildingId) {}
 public record BuildingRequest(@NotBlank String name,@NotBlank String address,@NotBlank String city,String contactName,String contactPhone) {}
 public record BuildingView(Long id,String name,String address,String city,String contactName,String contactPhone) {}
 public record ElevatorRequest(@NotBlank String code,String brand,String model) {}
 public record ElevatorView(Long id,String code,String brand,String model,ElevatorStatus status,Long buildingId,String buildingName) {}
 public record ElevatorStatusRequest(@NotNull ElevatorStatus status) {}
 public record ContractRequest(@NotNull LocalDate startDate,@NotNull LocalDate endDate,@NotNull @DecimalMin("0.01") BigDecimal monthlyFee) {}
 public record ContractView(Long id,Long elevatorId,LocalDate startDate,LocalDate endDate,BigDecimal monthlyFee,boolean active) {}
 public record VisitRequest(@NotNull Long elevatorId,@NotNull Long technicianId,@NotNull LocalDateTime scheduledStart,@NotNull LocalDateTime scheduledEnd,String notes) {}
 public record CompleteVisitRequest(@NotBlank @Size(max=3000) String workPerformed,String notes) {}
 public record VisitView(Long id,Long elevatorId,String elevatorCode,String buildingName,Long technicianId,String technicianName,LocalDateTime scheduledStart,LocalDateTime scheduledEnd,VisitStatus status,String workPerformed,String notes) {}
 public record ClaimRequest(@NotNull Long elevatorId,@NotBlank @Size(max=140) String title,@NotBlank @Size(max=3000) String description,@NotNull Priority priority) {}
 public record AssignClaimRequest(@NotNull Long technicianId) {}
 public record ClaimStatusRequest(@NotNull ClaimStatus status) {}
 public record ClaimView(Long id,Long elevatorId,String elevatorCode,String buildingName,Long createdById,String createdBy,Long assignedToId,String assignedTo,String title,String description,Priority priority,ClaimStatus status,LocalDateTime createdAt,LocalDateTime resolvedAt) {}
 public record MessageResponse(String message) {}
}
