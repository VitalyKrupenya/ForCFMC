package ru.site.repository;

import org.springframework.data.repository.CrudRepository;
import ru.site.model.Passage;

import java.time.LocalDateTime;
import java.util.List;

public interface PassageRepository extends CrudRepository<Passage, Long> {
    Passage save(Passage passage);
    List<Passage> findByEmployeeId(Long employeeId);
    List<Passage> findByEmployeeIdAndDateBetween(Long employeeId, LocalDateTime from , LocalDateTime to);
    //Person findPersonById(Long id);

    //@Query("SELECT coalesce(max(e.id), 0) FROM Employee e")
    //List<Passage> getPassageOneDay(LocalDate localDate);
}