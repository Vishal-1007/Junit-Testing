package com.mvc_unit_test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.mvc_unit_test.controller.UserController;
import com.mvc_unit_test.service.UserService;

@WebMvcTest(UserController.class) // loads only web layer (controller + MVC infra)
public class UserControllerWebMvcTest {
	
	 @Autowired
	    private MockMvc mockMvc;

	 @MockitoBean //MockitoBean, Deprecated in 3.2+ Spring version
	    private UserService service; // mocked dependency
	 
	 @Test
	    void getUser_found_returns200() throws Exception {
	        when(service.getUserName(1)).thenReturn("Alice");

	        mockMvc.perform(get("/users/1"))
	               .andExpect(status().isOk())
	               .andExpect(content().string("Alice"));
	    }
}
