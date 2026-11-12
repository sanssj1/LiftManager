package ar.com.liftmanager.repository;
import ar.com.liftmanager.model.MaintenanceContract; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface ContractRepository extends JpaRepository<MaintenanceContract,Long> { List<MaintenanceContract> findByElevatorIdOrderByEndDateDesc(Long elevatorId); }
