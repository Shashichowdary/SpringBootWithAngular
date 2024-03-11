package com.backendService.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendService.entity.Orders;
import com.backendService.entity.User;
import com.backendService.service.OrdersService;
import com.backendService.utils.ServiceUtils;
import com.google.gson.Gson;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/ordersService/api")
public class OrdersController {
	
	@Autowired
	OrdersService orderService;
	
	
	Logger logger = LoggerFactory.getLogger(OrdersController.class);

	@GetMapping("/getAllOrders")
	public List<Orders> getAllOrders(){
		List<Orders> response=orderService.getAllOrders();
		logger.info("getOrders of  API call with Response: {}", new Gson().toJson(response));
		return response; 
	}
	
	@PostMapping("/createOrder")
	public ResponseEntity<Orders> createOrders(@RequestBody Orders order, HttpServletRequest httpServletRequest) {
		String cookie = ServiceUtils.getCookie(httpServletRequest);
		logger.info("createOrder of  API called with userName stored in cookie: {} and request {}", cookie, new Gson().toJson(order));
		Orders response = orderService.createOrder(order, cookie);
		logger.info("createNewOrders API is called and response is : {}", new Gson().toJson(response));
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/updateOrder/{orderId}")
	public ResponseEntity<Orders> updateOrder(@PathVariable long orderId,@RequestBody Orders order) {
		Orders response = orderService.UpdateOrders(order, orderId);
		logger.info("updateOrders API is called and response is : {}", new Gson().toJson(response));
		return ResponseEntity.ok(response);
	}
	
	
	
	@DeleteMapping("/deleteOrder/{orderId}")
	public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable long orderId){
		Map<String, Boolean> response = orderService.deleteOrder(orderId);
		logger.info("deleteOrder API is called with orderId and response is : {}", new Gson().toJson(response));
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/getOrderById/{orderId}")
	public ResponseEntity<Optional<Orders>> getOrderById(@PathVariable long orderId) {
		Optional<Orders> response = orderService.getOrderById(orderId);
		logger.info("getUserById API is called with userId {} and response is : {}", orderId, response.toString());
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/getMyOrders")
	public List<Orders> getAllOrdersByUserName(@CookieValue(name = "username", defaultValue = "") String userName){
		logger.info("getMyOrders of  API called with userName stored in cookie: {}", userName);
		List<Orders> response=orderService.getAllOrdersByUserName(userName);
		logger.info("getMyOrders of  API call with Response: {}", new Gson().toJson(response));
		return response; 
	}
}