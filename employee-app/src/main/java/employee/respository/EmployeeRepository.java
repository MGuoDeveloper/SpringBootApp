package employee.respository;

import java.math.BigDecimal;
import java.util.ArrayList;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

@Transactional
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
	public Employee findById(int id);
	public ArrayList<Employee> findAll();
	@Modifying
	@Query("UPDATE Employee e SET e.salary = :salary WHERE e.id = :id")
    public int updateSalary(@Param("id") int id, @Param("salary") BigDecimal salary);
}
