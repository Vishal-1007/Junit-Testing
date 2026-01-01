package com.mvc_unit_test;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.mvc_unit_test.controller.UserController;
import com.mvc_unit_test.entity.User;
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
	 
	 @Test
	    void getUser_notFound_returns404() throws Exception {
	        when(service.getUserName(99)).thenReturn("Unknown User");

	        mockMvc.perform(get("/users/99"))
	               .andExpect(status().isNotFound());
	    }
	 
	 @Test
	    void createUser_returns201_and_body() throws Exception {
	        when(service.createUser(any(User.class))).thenReturn(new User(10, "Bob"));

	        mockMvc.perform(post("/users")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content("{\"id\":10,\"name\":\"Bob\"}"))
	               .andExpect(status().isCreated())
	               .andExpect(jsonPath("$.id").value(10))
	               .andExpect(jsonPath("$.name").value("Bob"));
	    }
	 
	 @Test
	    void updateUser_found_returns200() throws Exception {
	        when(service.updateUser(eq(5), any(User.class)))
	                .thenReturn(Optional.of(new User(5, "Charlie")));

	        mockMvc.perform(put("/users/5")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content("{\"id\":5,\"name\":\"Charlie\"}"))
	               .andExpect(status().isOk())
	               .andExpect(jsonPath("$.id").value(5))
	               .andExpect(jsonPath("$.name").value("Charlie"));
	    }
}
