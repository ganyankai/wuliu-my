package com.zry.framework.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zry.framework.entity.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
