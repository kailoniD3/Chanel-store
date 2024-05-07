package chanel.store.controller.model;

import java.util.HashSet;
import java.util.Set;

import chanel.store.entity.Customer;
import chanel.store.entity.ChanelStore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data 
@NoArgsConstructor

public class ChanelStoreCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    
    private String customerFirstName;
    
    private String customerLastName;
    
    private String customerEmail;
    
    public ChanelStoreCustomer(Customer customer) {
    	customerFirstName = customer.getCustomerFirstName();
    	customerLastName = customer.getCustomerLastName();
    	customerEmail = customer.getCustomerEmail();
    }
    
    @ManyToMany(mappedBy = "customers", cascade = CascadeType.PERSIST)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<ChanelStoreCustomer> chanelStores = new HashSet<>();
}
	