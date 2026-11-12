package ar.com.liftmanager.service;
import java.time.*;
public final class SchedulingRules {
 private SchedulingRules() {}
 public static boolean validInterval(LocalDateTime start,LocalDateTime end){return start!=null&&end!=null&&end.isAfter(start);}
 public static boolean overlaps(LocalDateTime startA,LocalDateTime endA,LocalDateTime startB,LocalDateTime endB){return startA.isBefore(endB)&&endA.isAfter(startB);}
 public static boolean dateCovered(LocalDate date,LocalDate contractStart,LocalDate contractEnd){return date!=null&&contractStart!=null&&contractEnd!=null&&!date.isBefore(contractStart)&&!date.isAfter(contractEnd);}
}
