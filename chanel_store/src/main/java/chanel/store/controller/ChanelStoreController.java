package chanel.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import chanel.store.controller.model.ChanelStoreData;
import chanel.store.controller.model.ChanelStoreEmployee;
import chanel.store.service.ChanelStoreService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/chanel_store")
@Slf4j

public class ChanelStoreController {

		@Autowired
		private ChanelStoreService chanelStoreService; 
		
		
	

	    @PostMapping
	    public ChanelStoreData createChanelStore(@RequestBody ChanelStoreData chanelStoreData) {
	       log.info("Creating {}", chanelStoreData);
	        return  chanelStoreService.saveChanelStore(chanelStoreData);
		
	    }
	    
	    
	    @PutMapping("/{chanelStoreId}")
	    public ChanelStoreData updateChanelStore(@RequestBody ChanelStoreData chanelStoreData, @PathVariable Long chanelStoreId) {
	    	chanelStoreData.setChanelStoreId(chanelStoreId);
	       log.info("Creating {}", chanelStoreData);
	        return  chanelStoreService.saveChanelStore(chanelStoreData);
		
	    }
	    
	    @RestController
	    @RequestMapping("/chanel_store")
	    public class EmployeeController {

	        @Autowired
	        private ChanelStoreService chanelStoreService;

	        @PostMapping("/chanel_store/{chanelStoreId}/employee")
	        @ResponseStatus(HttpStatus.CREATED)
	        public ChanelStoreEmployee addEmployeeToStore(@PathVariable Long chanelStoreId, @RequestBody ChanelStoreEmployee employee) {
	           
	            System.out.println("Employee details: " + employee.toString());
	            
	     
	            return chanelStoreService.saveEmployee(chanelStoreId, employee);
	        }
	    }
}

	    
