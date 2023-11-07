package lk.bitprojectsungam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeDao dao;
    
    @Autowired
    private EmployeeStatusDao employeeStatusDao;
  

    // public EmployeeController(EmployeeDao dao) {
    // this.dao = dao;
    // }
    @RequestMapping(value = "/employee")
    public ModelAndView employeeUI() {
        ModelAndView viewEmp = new ModelAndView();
        viewEmp.setViewName("employeeModal.html");
        return viewEmp;
    }

    // create get mapping for get empllyee all data --- [/employee/findall]
    @GetMapping(value = "/employee/findall", produces = "application/json")
    public List<Employee> findAll() {
        // login user authentication and authorization
        return dao.findAll(Sort.by(Direction.DESC, "id"));
    }

    // create post mapping for save employee
    @PostMapping(value = "/employee")
    public String save(@RequestBody Employee employee) {

        try {
         
            //check unique value
            Employee extEmployeeNic = dao.getEmployeeByNic(employee.getNic());
            if(extEmployeeNic != null){
                return "Save not completed : insert nic already exist";
            }

            Employee extEmployeeEmail = dao.getEmployeeByEmail(employee.getEmail() );
            if(extEmployeeEmail != null){
                return "Save not completed : insert email already exist";
            }

            employee.setAddeddatetime(LocalDateTime.now().toLocalDate());//set current date time
            String nextEmpNo = dao.getNextEmpNo();
            if (nextEmpNo.equals("") && nextEmpNo.equals(null)) {
                
                employee.setEmpno("00000001"); //emp no auto generated
            } else {
                employee.setEmpno(nextEmpNo); //emp no auto generated
                
            }

            dao.save(employee);
            return nextEmpNo;
        } catch (Exception e) {

            return "save Not Completed" + e.getMessage();
        }

    }
    //define service mapping for delete request
    @DeleteMapping(value = "/employee")
    public String delete(@RequestBody Employee employee){
        try {
            //delete
            
            Employee extEmp = dao.getReferenceById(employee.getId());
            if(extEmp == null){
                return "Delete Not Completed : no employee";  
            }
            // dao.delete(employee); 
            // dao.delete(dao.getReferenceById(employee.getId()));
            extEmp.setEmployeestatus_id(employeeStatusDao.getReferenceById(3));
            extEmp.setDeletedatetime(LocalDateTime.now().toLocalDate());
            dao.save(extEmp);
            return "OK";
        } catch (Exception e) {
     
            return "Delete not Completed"+ e.getMessage();
        }
    }

    //define mapping for update employee status
    @PutMapping(value = "/employee")
    public String update(@RequestBody Employee employee){
        try {
            employee.setLastmodifydatetime(LocalDateTime.now().toLocalDate());
            dao.save(employee);
            return "OK";
        } catch (Exception e) {
           
            return "Update Note complete"+ e.getMessage();
        }
    }

}
        
            