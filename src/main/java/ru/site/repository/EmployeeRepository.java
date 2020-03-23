	package ru.site.repository;
	
	import ru.site.model.Employee;
	import org.springframework.data.repository.CrudRepository;

	public interface EmployeeRepository extends CrudRepository<Employee, Long> {
		Employee save(Employee employee);
	}