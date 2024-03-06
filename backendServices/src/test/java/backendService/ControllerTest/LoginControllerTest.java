package backendService.ControllerTest;

import com.backendService.controller.LoginController;
import com.backendService.entity.LoginUser;
import com.backendService.serviceImpl.LoginServiceImpl;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.Cookie;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class LoginControllerTest {
	
	@InjectMocks
	LoginController loginController;
	
	@Mock
	LoginUser  login;
	@Mock
	LoginServiceImpl loginService;
	
	@Test
    public void validateUserTest() {
        LoginUser loginUser = new LoginUser("testUser", "password");
        when(loginService.validateUser("testUser", "password")).thenReturn(true);

        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();
        boolean result = loginController.validateUser(loginUser, mockHttpServletResponse);
        assertTrue(result);
        Cookie[] cookies = mockHttpServletResponse.getCookies();
        assertNotNull(cookies);
        assertEquals("username", cookies[0].getName());
        assertEquals("testUser", cookies[0].getValue());
        assertEquals("/", cookies[0].getPath());
       

    }


}
