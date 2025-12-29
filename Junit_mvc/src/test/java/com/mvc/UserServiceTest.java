package com.mvc;

import com.mvc.entity.User;
import com.mvc.repository.UserRepository;
import com.mvc.service.UserService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Enables Mockito in JUnit 5
class UserServiceTest {

    @Mock
    private UserRepository repository; // Mock dependency

    @InjectMocks
    private UserService service; // The class we're testing

    @Test
    void testGetUserName_Found() {
        // Arrange
        when(repository.findById(1)).thenReturn(Optional.of(new User(1, "Alice")));

        // Act
        String name = service.getUserName(1);

        // Assert
        assertEquals("Alice", name);

        // Verify that repository.findById() was called exactly once with argument 1
        verify(repository, times(1)).findById(1);
    }

    @Test
    void testGetUserName_NotFound() {
        // Arrange
        when(repository.findById(2)).thenReturn(Optional.empty());

        // Act
        String name = service.getUserName(2);

        // Assert
        assertEquals("Unknown User", name);

        // Verify that repository.findById() was called exactly once with argument 2
        verify(repository, times(1)).findById(2);
    }
}
