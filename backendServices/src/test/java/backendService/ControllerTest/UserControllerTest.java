package backendService.ControllerTest;

import com.backendService.controller.UserController;
import com.backendService.entity.User;
import com.backendService.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserControllerTest {
	
	@InjectMocks
	UserController userController;
	@Mock
	UserService userService;
	Long id =1l;
	
	
	 @Before
	 public void setUp() {
		 MockitoAnnotations.initMocks(this);
	 }
	 
@Test
	public void getAllUsersTest() {
	userController.getAllUsers();
	}
@Test
public void getUserByUserId() {
	userController.getUserByUserId("LIB001");
}
@Test
public void createUsersTest() {
	User userDetails=new User("LIB001", "apple", "siri", "siri", "siri", "siri", null, "siri", "siri", "siri", "siri", "siri", "siri");
	userController.createUser(userDetails);
}
@Test
public void updateUserTest() {
	User userDetails=new User("Sirisha Bejawada", "apple", "siri", "siri", "siri", "siri", null, "siri", "siri", "siri", "siri", "siri", "siri");
	userController.updateUserByUserName("Sirisha Bejawada", userDetails);
}
@Test
public void deleteUserTest() {
	userController.deleteUser("LIB001");
}


}
