package com.gmail.zagurskaya.web;
import com.gmail.zagurskaya.service.UserService;
import com.gmail.zagurskaya.service.model.RoleDTO;
import com.gmail.zagurskaya.service.model.UserDTO;
import com.gmail.zagurskaya.web.controler.UserController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;


import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    private UserController controller;

    private MockMvc mockMvc;

    private List<UserDTO> users = asList(
            new UserDTO(null, "admin", "admin",Long.parseLong(1)),
            new UserDTO(null, "user", "user",Long.parseLong(2));

    @Before
    public void init() {
        controller = new UserController(userService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        when(userService.getUsers()).thenReturn(users);
    }

    @Test
    public void allUsersAreAddedToModelForUsersView() {
        Model model = new ExtendedModelMap();
        String users = controller.getUsers(model);
        assertThat(users, equalTo("users"));
        assertThat(model.asMap(), hasEntry("users", this.users));
    }
}
