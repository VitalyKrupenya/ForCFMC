package ru.site;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.site.model.Employee;
import ru.site.model.Passage;
import ru.site.repository.EmployeeRepository;
import ru.site.repository.PassageRepository;

import java.time.LocalDateTime;

@Component
public class InitData implements ApplicationRunner {

    private EmployeeRepository employeeRepository;
    private PassageRepository passageRepository;

    @Autowired
    public InitData(EmployeeRepository employeeRepository,
                    PassageRepository passageRepository) {
        this.employeeRepository = employeeRepository;
        this.passageRepository = passageRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        {
            Long id = employeeRepository.save(Employee.builder().name("John").build()).getId();
            passageRepository.save(Passage.builder()
                    .employeeId(id)
                    .date(LocalDateTime.of(2020, 3 ,24,
                            8, 0, 0))
                    .type(0L)
                    .build());
            passageRepository.save(Passage.builder()
                    .employeeId(id)
                    .date(LocalDateTime.of(2020, 3 ,24,
                            12, 0, 0))
                    .type(1L)
                    .build());
            passageRepository.save(Passage.builder()
                    .employeeId(id)
                    .date(LocalDateTime.of(2020, 3 ,24,
                            13, 0, 0))
                    .type(0L)
                    .build());
            passageRepository.save(Passage.builder()
                    .employeeId(id)
                    .date(LocalDateTime.of(2020, 3 ,24,
                            17, 0, 0))
                    .type(1L)
                    .build());
            passageRepository.save(Passage.builder()
                    .employeeId(id)
                    .date(LocalDateTime.of(2020, 3 ,25,
                            8, 10, 0))
                    .type(0L)
                    .build());
            passageRepository.save(Passage.builder()
                    .employeeId(id)
                    .date(LocalDateTime.of(2020, 3 ,25,
                            17, 10, 0))
                    .type(1L)
                    .build());
        }
        {
            Long id = employeeRepository.save(Employee.builder().name("Mary").build()).getId();
            passageRepository.save(Passage.builder()
                    .employeeId(id)
                    .date(LocalDateTime.of(2020, 3 ,24,
                            9, 30, 0))
                    .type(0L)
                    .build());
            passageRepository.save(Passage.builder()
                    .employeeId(id)
                    .date(LocalDateTime.of(2020, 3 ,24,
                            17, 0, 0))
                    .type(1L)
                    .build());
        }
        {
            Long id = employeeRepository.save(Employee.builder().name("Joe").build()).getId();
            passageRepository.save(Passage.builder()
                    .employeeId(id)
                    .date(LocalDateTime.of(2020, 3 ,24,
                            8, 0, 0))
                    .type(0L)
                    .build());
            passageRepository.save(Passage.builder()
                    .employeeId(id)
                    .date(LocalDateTime.of(2020, 3 ,24,
                            18, 0, 0))
                    .type(1L)
                    .build());
        }
        {
            Long id = employeeRepository.save(Employee.builder().name("Alice").build()).getId();
            passageRepository.save(Passage.builder()
                    .employeeId(id)
                    .date(LocalDateTime.of(2020, 3 ,24,
                            9, 0, 0))
                    .type(0L)
                    .build());
            passageRepository.save(Passage.builder()
                    .employeeId(id)
                    .date(LocalDateTime.of(2020, 3 ,24,
                            17, 0, 0))
                    .type(1L)
                    .build());
        }
    }
}
