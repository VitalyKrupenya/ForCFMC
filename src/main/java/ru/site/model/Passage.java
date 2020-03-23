package ru.site.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Passage {
    @Id
    @GeneratedValue
    private Long id;
    private Long employeeId;
    private LocalDateTime date;
    private Long type;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "PersonId", insertable = false, updatable = false)
    //private Person person;

    //public Person getPerson() {
    //    return person;
    //}

    //public void setPerson(Person person) {
    //    this.person = person;
    //}
}
