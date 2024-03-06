package backendService.serviceImplTest;

import com.backendService.entity.Orders;
import com.backendService.repository.OrdersRepository;
import com.backendService.serviceImpl.OrdersServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class OrdersServiceImplTest {
	
	@Mock
    private OrdersRepository ordersRepository;
	
		
	@InjectMocks
	OrdersServiceImpl orderServiceImpl;
	
	@InjectMocks	
	Orders order;
	
	@Before
	 public void setUp() {
		 MockitoAnnotations.initMocks(this);
	 }
	
	@Test
	public void testGetAllOrders() {
		List<Orders> Orders=new ArrayList<Orders>();
		Orders order1=new Orders(1, "chips", 239, 3, "sirisha");
		Orders order2=new Orders(2,"Dosa",567,9,"priyanka");
		Orders.add(order1);
		Orders.add(order2);
		Mockito.when(ordersRepository.findAll()).thenReturn(Orders);
		
		assertEquals(Orders,orderServiceImpl.getAllOrders());
	}
			
	@Test
	public void testcreateOrder() {
		String userName="sirisha";
		Orders Order = new Orders(1, "chips", 239, 3, "sirisha");
		Mockito.when(ordersRepository.save(any(Orders.class))).thenReturn(Order);
		Orders result = orderServiceImpl.createOrder(Order, userName);
        assertNotNull(result);
        assertEquals(userName, result.getUserName());
		
	}
	
	
	@Test
	public void getOrderByIdTest() {
		long orderId=12;
		Orders expectedOrder = new Orders(1, "chips", 239, 3, "sirisha");
        when(ordersRepository.findById(orderId)).thenReturn(Optional.of(expectedOrder));
        Optional<Orders> result = orderServiceImpl.getOrderById(orderId);
        assertTrue(result.isPresent());
        assertEquals(expectedOrder, result.get());
		
		
	}
	
	
	@Test
	public void getAllOrdersByUserName() {
		String userName="sirisha";
		List<Orders> Orders=new ArrayList<Orders>();
		Orders order1=new Orders(1, "chips", 239, 3, "sirisha");
		Orders order2=new Orders(2,"Dosa",567,9,"priyanka");
		Orders.add(order1);
		Orders.add(order2);
		Mockito.when(ordersRepository.findByUserName(userName)).thenReturn(Orders);
		assertEquals(Orders,orderServiceImpl.getAllOrdersByUserName(userName));
		
	}
	
	@Test
    public void updateOrdersTest() {
        long orderId = 1L;
        Orders Order = new Orders(1, "chips", 239, 3, "sirisha"); // Mock existing order
        when(ordersRepository.findById(orderId)).thenReturn(Optional.of(Order));

        Orders updatedOrder = new Orders();
        updatedOrder.setItemName("Biskets");
        updatedOrder.setPrice(20);
        updatedOrder.setQuantity(5);

        Orders result = orderServiceImpl.UpdateOrders(updatedOrder, orderId);
        assertNotNull(result);
        assertEquals(updatedOrder.getItemName(), result.getItemName());
        assertEquals(updatedOrder.getPrice(), result.getPrice());
        assertEquals(updatedOrder.getQuantity(), result.getQuantity());

       
    }

	
	
	
}
