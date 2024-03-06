package backendService.serviceImplTest;

import com.backendService.entity.User;
import com.backendService.repository.UserRespository;
import com.backendService.serviceImpl.LoginServiceImpl;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LoginServiceImplTest {
	
	@InjectMocks
	UserRespository respository;
	@Mock
	LoginServiceImpl loginservice;
	@Mock
	User user;
	
	 @Test
	    public void validateUsertest() {
	        String userName = "testUser";
	        String password = "password123";
	        User User = new User(); // Mock existing user
	        when(respository.findByUserNameAndPassword(userName, password)).thenReturn(User);
	        boolean result = loginservice.validateUser(userName, password);
	        assertTrue(result);
	        verify(respository).findByUserNameAndPassword(userName, password);
	    }


}
