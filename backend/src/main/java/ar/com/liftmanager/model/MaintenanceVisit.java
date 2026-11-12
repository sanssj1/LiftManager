package ar.com.liftmanager.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity @Table(name="maintenance_visits", indexes={@Index(name="idx_visit_start",columnList="scheduledStart"),@Index(name="idx_visit_tech",columnList="technician_id")})
public class MaintenanceVisit {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @ManyToOne(fetch=FetchType.EAGER,optional=false) @JoinColumn(name="elevator_id",nullable=false) private Elevator elevator;
    @ManyToOne(fetch=FetchType.EAGER,optional=false) @JoinColumn(name="technician_id",nullable=false) private AppUser technician;
    @Column(nullable=false) private LocalDateTime scheduledStart;
    @Column(nullable=false) private LocalDateTime scheduledEnd;
    @Enumerated(EnumType.STRING) @Column(nullable=false,length=30) private VisitStatus status=VisitStatus.SCHEDULED;
    @Column(length=3000) private String workPerformed;
    @Column(length=2000) private String notes;
    @Column(nullable=false) private LocalDateTime createdAt=LocalDateTime.now();
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public Elevator getElevator(){return elevator;} public void setElevator(Elevator elevator){this.elevator=elevator;}
    public AppUser getTechnician(){return technician;} public void setTechnician(AppUser technician){this.technician=technician;}
    public LocalDateTime getScheduledStart(){return scheduledStart;} public void setScheduledStart(LocalDateTime v){scheduledStart=v;}
    public LocalDateTime getScheduledEnd(){return scheduledEnd;} public void setScheduledEnd(LocalDateTime v){scheduledEnd=v;}
    public VisitStatus getStatus(){return status;} public void setStatus(VisitStatus status){this.status=status;}
    public String getWorkPerformed(){return workPerformed;} public void setWorkPerformed(String v){workPerformed=v;}
    public String getNotes(){return notes;} public void setNotes(String notes){this.notes=notes;}
    public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime v){createdAt=v;}
}
