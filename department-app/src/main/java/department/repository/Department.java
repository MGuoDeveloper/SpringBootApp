package department.repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "department")
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	@NotNull
	String name;
	BigDecimal salary_min_range;
	BigDecimal salary_max_range;

	public Department() {
		super();
	}
	
	public Department(int id, String name, BigDecimal salary_min_range, BigDecimal salary_max_range) {
		super();
		this.id = id;
		this.name = name;
		this.salary_min_range = salary_min_range;
		this.salary_max_range = salary_max_range;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public BigDecimal getSalary_min_range() {
		return salary_min_range;
	}
	
	public void setSalary_min_range(BigDecimal salary_min_range) {
		this.salary_min_range = salary_min_range;
	}

	public BigDecimal getSalary_max_range() {
		return salary_max_range;
	}

	public void setSalary_max_range(BigDecimal salary_max_range) {
		this.salary_max_range = salary_max_range;
	}

}
