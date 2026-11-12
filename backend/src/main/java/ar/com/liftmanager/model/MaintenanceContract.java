package ar.com.liftmanager.model;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
@Entity @Table(name="maintenance_contracts")
public class MaintenanceContract {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @ManyToOne(fetch=FetchType.EAGER,optional=false) @JoinColumn(name="elevator_id",nullable=false) private Elevator elevator;
    @Column(nullable=false) private LocalDate startDate;
    @Column(nullable=false) private LocalDate endDate;
    @Column(nullable=false,precision=12,scale=2) private BigDecimal monthlyFee;
    @Column(nullable=false) private boolean active=true;
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public Elevator getElevator(){return elevator;} public void setElevator(Elevator elevator){this.elevator=elevator;}
    public LocalDate getStartDate(){return startDate;} public void setStartDate(LocalDate startDate){this.startDate=startDate;}
    public LocalDate getEndDate(){return endDate;} public void setEndDate(LocalDate endDate){this.endDate=endDate;}
    public BigDecimal getMonthlyFee(){return monthlyFee;} public void setMonthlyFee(BigDecimal monthlyFee){this.monthlyFee=monthlyFee;}
    public boolean isActive(){return active;} public void setActive(boolean active){this.active=active;}
}
