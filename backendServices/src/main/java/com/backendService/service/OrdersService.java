package com.backendService.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.backendService.entity.Orders;

public interface OrdersService {
	
	public List<Orders> getAllOrders();
	public Orders createOrder(Orders orders, String userName);
	public Optional<Orders> getOrderById(long orderId);
	public Orders UpdateOrders(Orders orders , long orderId);
	public Map<String ,Boolean> deleteOrder(long orderId);
	public List<Orders> getAllOrdersByUserName(String userName);

}
