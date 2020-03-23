package ru.site.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Employee {
	@Id
	@GeneratedValue
	private Long id;
	private String name;

	    //@OneToMany(mappedBy = "personId", cascade = CascadeType.ALL, orphanRemoval = true)
		//private List<Passage> passages;
		//public List<Passage> getPassages() {
		//	return passages;
		//}

		//public void setPassages(List<Passage> passages) {
		//	this.passages = passages;
		//}
}
