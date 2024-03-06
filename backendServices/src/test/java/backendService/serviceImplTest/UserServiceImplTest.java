package backendService.serviceImplTest;

import com.backendService.entity.User;
import com.backendService.exception.ResourceNotFoundException;
import com.backendService.repository.UserRespository;
import com.backendService.serviceImpl.UserServiceImpl;
import com.backendService.utils.ServiceUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

public class UserServiceImplTest {
	@InjectMocks
	UserServiceImpl userServiceImpl;
	@Mock
	UserRespository userRespository;
	@InjectMocks
	ServiceUtils serviceUtils;
	@InjectMocks	
	User user;
	
	String userName ="Siri" ;
	Long id ;
	 long userId;
	//private String siri;
	 
	 @Before
	 public void setUp() {
		 MockitoAnnotations.initMocks(this);
	 }
	
	 
	 
	  
	@Test
	public void getAllUsersTest() {
		List<User> users=new ArrayList<User>();
		User user1=new User("LIB001", "apple", "siri", "siri", "siri", "siri", null, "siri", "siri", "siri", "siri", "siri", "siri");
		User user2=new User("LIB001", "apple", "siri", "siri", "siri", "siri", null, "siri", "siri", "siri", "siri", "siri", "siri");
		users.add(user1);
		users.add(user2);
		Mockito.when(userRespository.findAll()).thenReturn(users);
		//userServiceImpl.getAllUsers();
		assertEquals(users,	userServiceImpl.getAllUsers());
	}
	
	@Test
	public void getUserByIdTest() {
		String userId="LIB001";
		Optional<User> userDetails=Optional.of(new User("LIB001", "apple", "siri", "siri", "siri", "siri", null, "siri", "siri", "siri", "siri", "siri", "siri"));
        Mockito.when(userRespository.findById(userId)).thenReturn(userDetails);
        Optional<User> userById = userServiceImpl.getUserById(userId);
		assertEquals(userDetails, userById);
	}
	@Test
	public void updateUserTest() {
		String userId="LIB001";
		User userDetails=new User("LIB001", "apple", "siri", "siri", "siri", "siri", new Date(), "siri", "siri", "siri", "siri", "siri", "siri");
		Mockito.when(userRespository.findById(userId)).thenReturn(Optional.of(userDetails));
		//Mockito.when(userRespository.save(any(user)))
		//Mockito.when(userRespository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not exist with userId : "+userId)));
		User updateUser = userServiceImpl.updateUser(userDetails, userId);
		//assertEquals(userDetails, updateUser);
	}
	
	
	
	@Test
	public void deleteUser() {
		String userId="LIB001";
			User userDetailsExist=new User("LIB001", "apple", "siri", "siri", "siri", "siri", null, "siri", "siri", "siri", "siri", "siri", "siri");
			Mockito.when(userRespository.findById(userId)).thenReturn(Optional.of(userDetailsExist));
			userRespository.delete(user);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			userServiceImpl.deleteUser(userId);

		}
	
	@Test
	public void createUser() {
		User userDetails=new User("LIB001", "apple", "siri", "siri", "siri", "siri", null, "siri", "siri", "siri", "siri", "siri", "siri");
		Mockito.when(serviceUtils.generateUserName(anyString(),anyString())).thenReturn(userName);
		Mockito.when(userName.startsWith("Error")).thenThrow(ResourceNotFoundException.class);
		userServiceImpl.createUser(userDetails);


	}
	
	
		}


