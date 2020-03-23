package ru.site.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.site.ResponseTemplate.AuditEmployee;
import ru.site.ResponseTemplate.OneDay;
import ru.site.model.Employee;
import ru.site.model.Passage;
import ru.site.repository.EmployeeRepository;
import ru.site.repository.PassageRepository;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuditService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PassageRepository passageRepository;

    public List<AuditEmployee> getAuditStaff(LocalDate from, LocalDate to) {
        List<AuditEmployee> staff = new ArrayList<>();
        employeeRepository.findAll().forEach(employee ->
                // TODO Переделать т.к. при таком подходе очень много летит запросов в БД.
                staff.add(getAuditEmployee(employee.getId(), from, to))
        );
        return staff;
    }

    public AuditEmployee getAuditEmployee(Long employeeId, LocalDate from, LocalDate to) {
        // TODO сделать проверку на Null
        Employee employee = employeeRepository.findById(employeeId).get();
        List<OneDay> calendar = getCalendarEmployee(employeeId, from, to);
        return AuditEmployee
                .builder()
                .employeeId(employeeId)
                .employeeName(employee.getName())
                .calendar(calendar)
                .totalDuration(getTotalDuration(calendar))
                .totalLateness(getTotalLateness(calendar))
                .totalOverwork(getTotalOverwork(calendar))
                .average(getAverageDuration(calendar))
                .build();
    }

    private List<OneDay> getCalendarEmployee(Long employeeId, LocalDate from, LocalDate to) {
        List<OneDay> employeePassages = getCalendar(from, to);
        List<Passage> passages = passageRepository.findByEmployeeIdAndDateBetween(employeeId,
                LocalDateTime.of(from.getYear(), from.getMonth(), from.getDayOfMonth(),
                        0, 0, 0, 0),
                LocalDateTime.of(to.getYear(), to.getMonth(), to.getDayOfMonth(),
                        0, 0, 0, 0));
        for (OneDay day : employeePassages) {
            fillOneDay(day, passages);
        }
        return employeePassages;
    }

    private void fillOneDay(OneDay day, List<Passage> passages) {
        LocalTime lastEntrance = null;
        // TODO order by date
        for (Passage passage : passages) {
            if (day.getDate().compareTo(passage.getDate().toLocalDate()) == 0) {
                if (passage.getType() == 0) {
                    // entrance
                    if (day.getStart() == null) {
                        // первый вход за день
                        day.setStart(passage.getDate().toLocalTime());
                    }
                    lastEntrance = passage.getDate().toLocalTime();
                } else {
                    // leave
                    // TODO проверка что нет выхода раньше входа
                    day.setFinish(passage.getDate().toLocalTime());
                    day.setDuration(day.getDuration()
                            .plus(Duration.between(lastEntrance, passage.getDate().toLocalTime()))
                    );
                }
            }
        }
        day.setLateness(getLateness(day));
        day.setOverwork(getOverwork(day));
    }
    private Duration getLateness(OneDay day) {
        Duration result = Duration.ofSeconds(0);
        if (day.getStart() != null && day.getFinish() != null) {
            if (day.getNormalStart() != null) {
                if (Duration.between(day.getNormalStart(), day.getStart()).toMinutes() > 0) {
                    result = Duration.between(day.getNormalStart(), day.getStart());
                }
            }
        }
        return result;
    }

    private Duration getOverwork(OneDay day) {
        Duration result = Duration.ofSeconds(0);
        if (day.getStart() != null && day.getFinish() != null) {
            if (day.getNormalFinish() == null) {
                result = Duration.between(day.getStart(), day.getFinish());
            } else {
                // Предпологается, что не может быть day.getNormalFinish() != null && day.getNormalStart() == null
                if (Duration.between(day.getStart(), day.getNormalStart()).toMinutes() > 0) {
                    result = result
                            .plus(Duration.between(day.getStart(), day.getNormalStart()));
                }
                if (Duration.between(day.getNormalFinish(), day.getFinish()).toMinutes() > 0) {
                    result = result
                            .plus(Duration.between(day.getNormalFinish(), day.getFinish()));
                }
            }
        }
        return result;
    }

    private List<OneDay> getCalendar(LocalDate from, LocalDate to) {
        List<OneDay> calendar = new ArrayList<>();
        LocalDate date = from;
        while (date.compareTo(to) <= 0) {
            calendar.add(OneDay.builder().date(date)
                    .normalStart(getStartFromWorkCalendar(date))
                    .normalFinish(getFinishFromWorkCalendar(date))
                    .duration(Duration.ofSeconds(0))
                    .build());
            date = date.plusDays(1);
        };
        return calendar;
    }

    private LocalTime getStartFromWorkCalendar(LocalDate date) {
        // TODO надо привязать производственный календарь, но тогда надо простроить бизнес процесс его заполнения
        return date.getDayOfWeek().getValue() < 6
                ? LocalTime.of(9,0,0)
                : null;
    }

    private LocalTime getFinishFromWorkCalendar(LocalDate date) {
        // TODO надо привязать производственный календарь, но тогда надо простроить бизнес процесс его заполнения
        return date.getDayOfWeek().getValue() < 6
                ? LocalTime.of(17,0,0)
                : null;
    }

    private Duration getTotalDuration(List<OneDay> calendar) {
        return calendar.stream()
                .map(OneDay::getDuration)
                .reduce(Duration::plus)
                .orElseGet(() -> Duration.ofSeconds(0));
    }

    private Duration getTotalLateness(List<OneDay> calendar) {
        return calendar.stream()
                .map(OneDay::getLateness)
                .reduce(Duration::plus)
                .orElseGet(() -> Duration.ofSeconds(0));
    }

    private Duration getTotalOverwork(List<OneDay> calendar) {
        return calendar.stream()
                .map(OneDay::getOverwork)
                .reduce(Duration::plus)
                .orElseGet(() -> Duration.ofSeconds(0));
    }

    private Duration getAverageDuration(List<OneDay> calendar) {
        return Duration.ofSeconds(Math.round(calendar.stream()
                .map(OneDay::getDuration)
                .mapToLong(Duration::toSeconds)
                .average().orElse(0.0)));
    }
}