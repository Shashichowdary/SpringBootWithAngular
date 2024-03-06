package com.backendService.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backendService.entity.Orders;
import com.backendService.exception.ResourceNotFoundException;
import com.backendService.repository.OrdersRepository;
import com.backendService.service.OrdersService;

@Service
public class OrdersServiceImpl implements OrdersService {
	
	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


	@Autowired
	OrdersRepository ordersRepository;
	
	@Override
	public List<Orders> getAllOrders() {
		return ordersRepository.findAll();
	
	}

	@Override
	public Orders createOrder(Orders orders, String userName) {
		orders.setUserName(userName);
		return ordersRepository.save(orders);
	}

	@Override
	public Orders UpdateOrders(Orders orders, long orderId) {
		Orders order = ordersRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("User not exist with userId : "+orderId));
		try {
			order.setItemName(orders.getItemName());
			order.setPrice(orders.getPrice());
			order.setQuantity(orders.getQuantity());
			
		}catch(Exception e) {
			logger.error("Exception Occurred while Updating the order", e);
		}
		return ordersRepository.save(orders);
	}

	@Override
	public Map<String, Boolean> deleteOrder(long orderId) {
		Map<String, Boolean> response = new HashMap<>();
		try {
		Orders order = ordersRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("user not exist with userId :"+orderId));
		ordersRepository.delete(order);
		response.put("deleted", Boolean.TRUE);
		}catch(Exception e) {
			logger.error("Exception Occurred while deleting the Order", e);
		}
		return response;
	}

	@Override
	public Optional<Orders> getOrderById(long orderId) {
		return ordersRepository.findById(orderId);
	}

	@Override
	public List<Orders> getAllOrdersByUserName(String userName) {
		return ordersRepository.findByUserName(userName);
	}
}
	


