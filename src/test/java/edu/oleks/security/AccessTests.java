package edu.oleks.security;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/*
@author   oleksandra
@project   security
@class  AccessTests
@version  1.0.0
@since 25.04.2025 - 15.58
*/
@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class AccessTests {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    void beforeAll() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }
    //Анонімний користувач не може отримати доступ до списку тварин
    @Test
    @WithAnonymousUser
    public void whenAnonymThenStatusUnautorized() throws Exception {

        mockMvc.perform(get("/api/v1/animals"))
                .andExpect(status().isUnauthorized());
    }
    //Admin отримує доступ до hello/admin
    @Test
    @WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
    void whenAuthenticatedThenStatusOk() throws Exception {
        mockMvc.perform(get("/api/v1/animals/hello/admin"))
                .andExpect(status().isOk());
    }
    //Admin не має доступу до hello/user
    @Test
    @WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
    void whenAuthenticatedThenStatus403() throws Exception {
        mockMvc.perform(get("/api/v1/animals/hello/user"))
                .andExpect(status().isForbidden());
    }
    //Анонімний користувач може зайти на hello/unknown
    @Test
    @WithAnonymousUser
    void whenAnonymousAccessHelloUnknown_thenStatusOk() throws Exception {
        mockMvc.perform(get("/api/v1/animals/hello/unknown"))
                .andExpect(status().isOk());
    }
    //USER має доступ до hello/user
    @Test
    @WithMockUser(username = "user", password = "user", roles = {"USER"})
    void whenUserAccessHelloUser_thenStatusOk() throws Exception {
        mockMvc.perform(get("/api/v1/animals/hello/user"))
                .andExpect(status().isOk());
    }
    //USER не має доступу до hello/admin
    @Test
    @WithMockUser(username = "user", password = "user", roles = {"USER"})
    void whenUserAccessHelloAdmin_thenStatusForbidden() throws Exception {
        mockMvc.perform(get("/api/v1/animals/hello/admin"))
                .andExpect(status().isForbidden());
    }
    // Admin має доступ до створення тварин
    @Test
    @WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
    void whenAdminCreatesAnimal_thenStatusCreated() throws Exception {
        String newAnimalJson = """
        {
                       "name": "name4",
                       "age": 2,
                       "sex": "M",
                       "description": "description3",
                       "location": "location3",
                       "type": "dog",
                       "photos": "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww",
                       "shelterId": "1",
                       "sterilization": true,
                       "availableForAdoption": false
                     }
    """;


        mockMvc.perform(post("/api/v1/animals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newAnimalJson))
                        .andExpect(status().isOk());

    }
    //USER має доступ до пошуку тварин за локацією
    @Test
    @WithMockUser(username = "user", password = "user", roles = {"USER"})
    void whenUserAccessAnimalsByLocation_thenStatusOk() throws Exception {
        mockMvc.perform(get("/api/v1/animals/location/location"))
                .andExpect(status().isOk());
    }
    //Admin має доступ до пошуку тварин за статтю
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void whenAdminAccessGetBySex_thenOk() throws Exception {
        mockMvc.perform(get("/api/v1/animals/sex/M"))
                .andExpect(status().isOk());
    }
    //Superadmin може видалити тварину
    @Test
    @WithMockUser(username = "superadmin", password = "superadmin", roles = {"SUPERADMIN"})
    void whenSuperAdminDeletesAnimal_thenStatusOk() throws Exception {
        mockMvc.perform(delete("/api/v1/animals/del/1"))
                .andExpect(status().isOk());
    }
}
