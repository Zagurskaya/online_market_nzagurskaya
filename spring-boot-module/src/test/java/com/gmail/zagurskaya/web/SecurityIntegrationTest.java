package com.gmail.zagurskaya.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SecurityIntegrationTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void shouldSucceedWith200ForAboutPage() throws Exception {
       mvc.perform(get("/about"))
               .andExpect(status().isOk());
    }

    @Test
    public void shouldSucceedWith200ForLoginPage() throws Exception {
        mvc.perform(get("/login"))
                .andExpect(status().isOk());
    }

    @WithMockUser(roles = {"Administrator"})
    @Test
    public void shouldSucceedWith200ForUsersPage() throws Exception {
        mvc.perform(get("/users"))
                .andExpect(status().isOk());
    }

    @WithMockUser(roles = {"Customer"})
    @Test
    public void shouldSucceedWith200ForItemsPage() throws Exception {
        mvc.perform(get("/items"))
                .andExpect(status().isOk());
    }

}
