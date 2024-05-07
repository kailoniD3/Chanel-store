package chanel.store.controller.model;

import java.util.HashSet;
import java.util.Set;

import chanel.store.entity.Customer;
import chanel.store.entity.Employee;
import chanel.store.entity.ChanelStore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data 
@NoArgsConstructor 

public class ChanelStoreData {
	
	 private Long chanelStoreId;  
	 private String storeName;
	 private String storeAddress;
	 private String storeCity;
	 private String storeZip;
	 private String storePhone;
	 private Set<ChanelStoreEmployee> employees = new HashSet<>();;  
	 private Set<ChanelStoreCustomer> customers = new HashSet<>();   
	   
	public ChanelStoreData(ChanelStore chanelStore) {
		chanelStoreId = chanelStore.getChanelStoreId();
		storeName = chanelStore.getStoreName();
		storeAddress = chanelStore.getStoreAddress();
		storeCity = chanelStore.getStoreCity();
		storeZip = chanelStore.getStoreZip();
		storePhone = chanelStore.getStorePhone();
	
		  for (Employee employee : chanelStore.getEmployees()) {
	            employees.add(new ChanelStoreEmployee(employee));
	        }

	        for (Customer customer : chanelStore.getCustomers()) {
	            customers.add(new ChanelStoreCustomer(customer));
	        }
	    }
		
		
	 @Data
	    @NoArgsConstructor
	    public class ChanelStoreEmployee {
	        
		 	private Long employeeId;
	        private String employeeName;
	        private String employeeFirstName;
	        private String employeeLastName;
	        private String employeePhone;
	        private String employeeJobTitle;

	        public ChanelStoreEmployee(Employee employee) {
	            this.employeeId = employee.getEmployeeId();
	            this.employeeFirstName = employee.getEmployeeFirstName();
	            this.employeeLastName = employee.getEmployeeLastName();
	            this.employeePhone = employee.getEmplyeePhone();
	            this.employeeJobTitle = employee.getEmployeeJobTitle();
	        }
	    }

	  
	    @Data
	    @NoArgsConstructor
	    public static class ChanelStoreCustomer {
	    
	        private Long customerId;
	        private String customerFirstName;
	        private String customerLastName;
	        private String customerEmail;
	        
	        
	        public ChanelStoreCustomer(Customer customer) {
	            this.customerId = customer.getCustomerId();
	            this.customerFirstName = customer.getCustomerFirstName();
	            this.customerLastName = customer.getCustomerLastName();
	            this.customerEmail = customer.getCustomerEmail();
	        }
	}
	    
	 
}

