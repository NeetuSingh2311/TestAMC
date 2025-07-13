package com.demo.testamc.service;

import com.demo.testamc.enums.UserType;
import com.demo.testamc.models.User;
import com.demo.testamc.repository.UserRepository;
import com.demo.testamc.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUsers_ReturnsUserList() {
        List<User> users = Arrays.asList(
                User.builder().userId(1L).email("user1@example.com").role(UserType.USER.toString()).build(),
                User.builder().userId(2L).email("user2@example.com").role(UserType.USER.toString()).build()
        );
        when(userRepository.findByRole(UserType.USER.toString())).thenReturn(users);

        List<User> result = userService.getUsers();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("user1@example.com", result.get(0).getEmail());
        assertEquals("user2@example.com", result.get(1).getEmail());
    }

    @Test
    void testGetUsers_ReturnsEmptyList() {
        when(userRepository.findByRole(UserType.USER.toString())).thenReturn(Collections.emptyList());

        List<User> result = userService.getUsers();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetUserByEmail_Found() {
        String email = "user@example.com";
        User user = User.builder().userId(1L).email(email).role(UserType.USER.toString()).build();
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        User result = userService.getUserByEmail(email);

        assertNotNull(result);
        assertEquals(email, result.getEmail());
    }

    @Test
    void testGetUserByEmail_NotFound() {
        String email = "notfound@example.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        User result = userService.getUserByEmail(email);

        assertNull(result);
    }
}
