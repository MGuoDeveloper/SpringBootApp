package employee.respository;

import java.util.ArrayList;

import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

@Transactional
public interface DepartmentRepository extends CrudRepository<Department, Integer> {
	public Department findById(int id);
	public ArrayList<Department> findAll();
}
