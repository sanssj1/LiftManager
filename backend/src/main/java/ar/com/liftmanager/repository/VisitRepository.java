package ar.com.liftmanager.repository;
import ar.com.liftmanager.model.*; import org.springframework.data.jpa.repository.JpaRepository; import java.time.LocalDateTime; import java.util.*;
public interface VisitRepository extends JpaRepository<MaintenanceVisit,Long> {
 List<MaintenanceVisit> findByTechnicianId(Long technicianId); List<MaintenanceVisit> findByElevatorBuildingId(Long buildingId);
 boolean existsByTechnicianIdAndStatusInAndScheduledStartLessThanAndScheduledEndGreaterThan(Long technicianId,Collection<VisitStatus> statuses,LocalDateTime end,LocalDateTime start);
 boolean existsByElevatorIdAndStatusInAndScheduledStartLessThanAndScheduledEndGreaterThan(Long elevatorId,Collection<VisitStatus> statuses,LocalDateTime end,LocalDateTime start);
}
