package com.backendService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backendService.entity.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {

	List<Orders> findByUserName(String userName);

}
