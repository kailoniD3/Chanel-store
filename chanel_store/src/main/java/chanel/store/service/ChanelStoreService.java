package chanel.store.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import chanel.store.controller.model.ChanelStoreData;
import chanel.store.controller.model.ChanelStoreEmployee;
import chanel.store.dao.CustomerDao;
import chanel.store.dao.EmployeeDao;
import chanel.store.dao.ChanelStoreDao;
import chanel.store.entity.Employee;
import chanel.store.entity.ChanelStore;

@Service


public class ChanelStoreService {
	
	@Autowired	
	private ChanelStoreDao chanelStoreDao;
	
	@Autowired 
	private EmployeeDao employeeDao;
	
	@Autowired 
	private CustomerDao customerDao;
	
	

	@Transactional(readOnly = false)
    public ChanelStoreData saveChanelStore(ChanelStoreData chanelStoreData) {
        ChanelStore chanelStore = findOrCreateChanelStore(chanelStoreData.getChanelStoreId());

        copyChanelStoreFields(chanelStore, chanelStoreData);
        
        
        return new ChanelStoreData(chanelStoreDao.save(chanelStore));
    }
	
	  private void copyChanelStoreFields(ChanelStore chanelStore, ChanelStoreData chanelStoreData) {
	        chanelStore.setStoreName(chanelStoreData.getStoreName());
	        chanelStore.setStoreAddress(chanelStoreData.getStoreAddress());
	        chanelStore.setStoreCity(chanelStoreData.getStoreCity());
	        chanelStore.setChanelStoreId(chanelStoreData.getChanelStoreId());
	        chanelStore.setStoreZip(chanelStoreData.getStoreZip());
	        chanelStore.setStorePhone(chanelStoreData.getStorePhone());
	      
	      
	    }

    private ChanelStore findOrCreateChanelStore(Long chanelStoreId) {
        if (Objects.isNull(chanelStoreId)) {
            return new ChanelStore(); 
        } else {
        	return findChanelStoreById(chanelStoreId);
           
        }
    }
    private ChanelStore findChanelStoreById(Long chanelStoreId) {
 
    	return chanelStoreDao.findById(chanelStoreId)
    	.orElseThrow(() -> new NoSuchElementException("ChanelStore with ID = "+ chanelStoreId + " was not found. "));
    	
    }
    @Transactional(readOnly = true)
	public ChanelStoreEmployee saveEmployee(Long chanelStoreId, ChanelStoreEmployee chanelStoreEmployee) {
		
		ChanelStore chanelStore = findChanelStoreById(chanelStoreId);
		Long employeeId = chanelStoreEmployee.getEmployeeId();
		Employee employee = findOrCreateEmployee(chanelStoreId, employeeId);
		
		copyEmployeeFields(employee, chanelStoreEmployee);
		employee.setChanelStore(chanelStore);
		chanelStore.getEmployees().add(employee);
		
		return new ChanelStoreEmployee(employeeDao.save(employee));
	}
	
    public Employee findOrCreateEmployee(Long chanelStoreId, Long employeeId) {
        if (employeeId == null) {
            return new Employee();
        } else {
            return findEmployeeById(chanelStoreId, employeeId);
        }
    }
    
        @Transactional(readOnly = true)
        public Employee findEmployeeById(Long chanelStoreId, Long employeeId) {
            Employee employee = employeeDao.findById(employeeId).orElseThrow(() -> new NoSuchElementException("Employee not found"));
            if (!employee.getChanelStore().getChanelStoreId().equals(chanelStoreId)) {
                throw new IllegalArgumentException("Employee does not belong to the given Chanel store");
            }
            return employee;
        }
        
        public void copyEmployeeFields(Employee employee, ChanelStoreEmployee chanelStoreEmployee) {
            employee.setEmployeeFirstName(chanelStoreEmployee.getEmployeeFirstName());
            employee.setEmployeeLastName(chanelStoreEmployee.getEmployeeLastName());
            employee.setEmplyeePhone(chanelStoreEmployee.getEmployeePhone());
            employee.setEmployeeJobTitle(chanelStoreEmployee.getEmployeeJobTitle());
            
        }
        public List<ChanelStoreData> retreiveAllChanelStores(){
        	List<ChanelStore> chanelStores = chanelStoreDao.findAll();
        	List<ChanelStoreData> result = new LinkedList<>();
       
       for(ChanelStore chanelStore : chanelStores) {
    	   ChanelStoreData psd = new ChanelStoreData(chanelStore);
    			   
    			   psd.getCustomers().clear();
    	   			psd.getEmployees().clear();
    	   			
    	   			result.add(psd);
       }   	
       return result;
       }


		@Transactional
		public void deleteChanelStoreById(Long chanelStoreId) {
			ChanelStore chanelStore = findChanelStoreById(chanelStoreId);
			chanelStoreDao.delete(chanelStore);
			
}
}
