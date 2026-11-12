package ar.com.liftmanager.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity @Table(name="claims",indexes=@Index(name="idx_claim_status",columnList="status"))
public class Claim {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @ManyToOne(fetch=FetchType.EAGER,optional=false) @JoinColumn(name="elevator_id",nullable=false) private Elevator elevator;
    @ManyToOne(fetch=FetchType.EAGER,optional=false) @JoinColumn(name="created_by_id",nullable=false) private AppUser createdBy;
    @ManyToOne(fetch=FetchType.EAGER) @JoinColumn(name="assigned_to_id") private AppUser assignedTo;
    @Column(nullable=false,length=140) private String title;
    @Column(nullable=false,length=3000) private String description;
    @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private Priority priority=Priority.NORMAL;
    @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private ClaimStatus status=ClaimStatus.OPEN;
    @Column(nullable=false) private LocalDateTime createdAt=LocalDateTime.now();
    private LocalDateTime resolvedAt;
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public Elevator getElevator(){return elevator;} public void setElevator(Elevator v){elevator=v;}
    public AppUser getCreatedBy(){return createdBy;} public void setCreatedBy(AppUser v){createdBy=v;}
    public AppUser getAssignedTo(){return assignedTo;} public void setAssignedTo(AppUser v){assignedTo=v;}
    public String getTitle(){return title;} public void setTitle(String v){title=v;}
    public String getDescription(){return description;} public void setDescription(String v){description=v;}
    public Priority getPriority(){return priority;} public void setPriority(Priority v){priority=v;}
    public ClaimStatus getStatus(){return status;} public void setStatus(ClaimStatus v){status=v;}
    public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime v){createdAt=v;}
    public LocalDateTime getResolvedAt(){return resolvedAt;} public void setResolvedAt(LocalDateTime v){resolvedAt=v;}
}
