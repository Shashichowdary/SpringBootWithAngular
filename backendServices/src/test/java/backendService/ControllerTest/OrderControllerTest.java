package backendService.ControllerTest;

import com.backendService.controller.OrdersController;
import com.backendService.entity.Orders;
import com.backendService.service.OrdersService;
import com.backendService.utils.ServiceUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

	private static final HttpServletRequest httpServletRequest = null;
	@InjectMocks
	OrdersController orderController;
	@Mock
	OrdersService orderService;
	@Mock
	ServiceUtils serviceUtils;

	
	//@Before
	// public void setUp() {
		// MockitoAnnotations.initMocks(this);
	// }
	
	@Test
	public void getAllOrdersTest() {

		List<Orders> Orders = new ArrayList<Orders>();
		Orders order1 = new Orders(1, "chips", 239, 3, "sirisha");
		Orders order2 = new Orders(2, "Dosa", 567, 9, "priyanka");
		Orders.add(order1);
		Orders.add(order2);
		when(orderService.getAllOrders()).thenReturn(Orders);
		List<Orders> result = orderService.getAllOrders();
		assertEquals(Orders, result);

	}
	
	@Test
	public void CreateOrdersTest() {
		Orders Order = new Orders(1, "chips", 239, 3, "sirisha");
		String mockCookie = "mockCookie";
		when(serviceUtils.getCookie(httpServletRequest)).thenReturn(mockCookie);
		when(orderService.createOrder(any(Orders.class), eq(mockCookie))).thenReturn(Order);
		ResponseEntity<Orders> result = orderController.createOrders(Order, httpServletRequest);
		assertEquals(ResponseEntity.ok(Order), result);

	}

	@Test
	public void UpdateOrderTest() {
		long orderId = 123L;
		Orders Order = new Orders(1, "chips", 239, 3, "sirisha");
		when(orderService.UpdateOrders(Order, orderId)).thenReturn(Order);
		ResponseEntity<Orders> result = orderController.updateOrder(orderId, Order);
		assertEquals(ResponseEntity.ok(Order), result);

	}

}
