package chanel.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import chanel.store.entity.Customer;

public interface CustomerDao extends JpaRepository<Customer, Long> {

}
