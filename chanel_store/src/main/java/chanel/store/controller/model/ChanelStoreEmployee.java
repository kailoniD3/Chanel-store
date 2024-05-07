package chanel.store.controller.model;

import chanel.store.entity.Employee;
import chanel.store.entity.ChanelStore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data 
@NoArgsConstructor 
public class ChanelStoreEmployee {

	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;
    
    private String employeeFirstName;
    
    private String employeeLastName;
    
    private String employeePhone;
    
    private String employeeJobTitle;
    
    public ChanelStoreEmployee(Employee employee) {
    	employeeFirstName = employee.getEmployeeFirstName();
    	employeeLastName = employee.getEmployeeLastName();
    	employeePhone = employee.getEmplyeePhone();
    	employeeJobTitle = employee.getEmployeeJobTitle();
    	
    }
    
    
    @ManyToOne(cascade = CascadeType.ALL)
  
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private ChanelStoreEmployee chanelStoreEmployee;
}
