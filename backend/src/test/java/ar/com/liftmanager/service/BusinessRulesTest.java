package ar.com.liftmanager.service;
import org.junit.jupiter.api.Test;
import java.time.*;
import static org.junit.jupiter.api.Assertions.*;
class BusinessRulesTest {
 private final LocalDateTime start=LocalDateTime.parse("2026-11-12T10:00:00");
 private final LocalDateTime end=LocalDateTime.parse("2026-11-12T11:00:00");
 @Test void detectsOverlappingVisits(){assertTrue(SchedulingRules.overlaps(start,end,start.plusMinutes(30),end.plusMinutes(30)));}
 @Test void adjacentVisitsDoNotOverlap(){assertFalse(SchedulingRules.overlaps(start,end,end,end.plusHours(1)));}
 @Test void intervalMustEndAfterStart(){assertTrue(SchedulingRules.validInterval(start,end));assertFalse(SchedulingRules.validInterval(end,start));}
 @Test void contractIncludesItsFirstAndLastDay(){LocalDate first=LocalDate.of(2026,1,1),last=LocalDate.of(2026,12,31);assertTrue(SchedulingRules.dateCovered(first,first,last));assertTrue(SchedulingRules.dateCovered(last,first,last));}
 @Test void contractRejectsDatesOutsideItsTerm(){LocalDate first=LocalDate.of(2026,1,1),last=LocalDate.of(2026,12,31);assertFalse(SchedulingRules.dateCovered(first.minusDays(1),first,last));assertFalse(SchedulingRules.dateCovered(last.plusDays(1),first,last));}
 @Test void rejectsMissingSchedulingDates(){assertFalse(SchedulingRules.validInterval(null,end));assertFalse(SchedulingRules.validInterval(start,null));}
}
