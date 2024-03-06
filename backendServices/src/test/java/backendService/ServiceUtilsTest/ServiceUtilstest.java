package backendService.ServiceUtilsTest;

import com.backendService.entity.User;
import com.backendService.repository.UserRespository;
import com.backendService.utils.ServiceUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class ServiceUtilstest {
	@InjectMocks
	ServiceUtils serviceUtils;
	@Mock
	UserRespository respository;

	 @Before
	 public void setUp() {
		 MockitoAnnotations.initMocks(this);
	 }

	@Test
	public void fetchUserName() {
		String firstName="siri";
		String lastName="Bejawada";
		serviceUtils.fetchUserName(firstName, lastName);

	}
	
	
	@Test
	public void generateUserNameTest() {
		Long id = null ;
		String firstName="siri";
		String lastName="Bejawada";
		String userName="siriBejawada";
		//User userDetails=new User(id, "apple", "siri", "siri", "siri", "siri", null, "siri", "siri", "siri", "siri", "siri", "siri");
		Mockito.when(respository.findByUserName(userName)).thenReturn(new User());
		serviceUtils.generateUserName(firstName, lastName);
	}
	
	
	}



