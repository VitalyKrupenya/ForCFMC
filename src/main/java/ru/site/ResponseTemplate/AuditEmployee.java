package ru.site.ResponseTemplate;

import lombok.*;

import java.time.Duration;
import java.util.List;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AuditEmployee {
    private Long employeeId;
    private String employeeName;
    private Duration totalLateness;
    private Duration totalOverwork;
    private Duration totalDuration;
    private Duration average;
    List<OneDay> calendar;
}
