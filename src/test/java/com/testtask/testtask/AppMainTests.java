package com.testtask.testtask;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testtask.testtask.controller.UserController;
import com.testtask.testtask.model.User;
import com.testtask.testtask.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.Collections;
import static org.mockito.Mockito.when;

@WebMvcTest(UserController.class)
public class AppMainTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testGetAllUsers() throws Exception {
        when(userService.getAllUsers()).thenReturn(Collections.singletonList(new User()));

        mockMvc.perform(MockMvcRequestBuilders.get("/users/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetUserById() throws Exception {
        Long userId = 1L;
        when(userService.getUserById(userId)).thenReturn(new User());

        mockMvc.perform(MockMvcRequestBuilders.get("/users")
                        .param("id", userId.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testCreateNewUser() throws Exception {
        User newUser = new User();
        newUser.setEmail("test@example.com");
        newUser.setFirstName("John");
        newUser.setLastName("Doe");

        mockMvc.perform(MockMvcRequestBuilders.post("/users/new")
                        .content(asJsonString(newUser))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testUpdateUserById() throws Exception {
        Long userId = 1L;
        User updatedUser = new User();
        updatedUser.setFirstName("UpdatedFirstName");
        updatedUser.setLastName("UpdatedLastName");

        mockMvc.perform(MockMvcRequestBuilders.post("/users/update/{id}", userId)
                        .content(asJsonString(updatedUser))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDeleteUserById() throws Exception {
        Long userId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/users/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}