package ru.site.ResponseTemplate;

import lombok.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OneDay {
    private LocalDate date;
    private LocalTime start;
    private LocalTime finish;
    private LocalTime normalStart;
    private LocalTime normalFinish;
    private Duration lateness;
    private Duration overwork;
    private Duration duration;
}
