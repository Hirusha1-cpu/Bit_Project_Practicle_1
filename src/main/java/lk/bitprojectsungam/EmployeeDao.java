package lk.bitprojectsungam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeDao extends JpaRepository<Employee, Integer> {
    //define query for get next employee number
    @Query(value = "SELECT lpad(max(e.empno) + 1, 8, 0) as empno FROM bitproject123online.employee as e;", nativeQuery = true)
    public String getNextEmpNo();

    //define query for get employee by given nic number
    @Query(value = "SELECT e FROM Employee e WHERE e.nic = :nic")
    public Employee getEmployeeByNic(@Param("nic") String nic);

    //define query for get employee by given email
    @Query(value = "SELECT e FROM Employee e WHERE e.email = ?1")
    public Employee getEmployeeByEmail(String email);

}
