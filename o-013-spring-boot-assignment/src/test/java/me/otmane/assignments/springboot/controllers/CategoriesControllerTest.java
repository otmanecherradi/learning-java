package me.otmane.assignments.springboot.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.otmane.assignments.springboot.SpringBootAssignmentApplication;
import me.otmane.assignments.springboot.controllers.dto.CategoryDto;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = SpringBootAssignmentApplication.class)
class CategoriesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @Order(1)
    void createCategory() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/categories/")
                        .content(objectMapper.writeValueAsString(new CategoryDto("Laptops")))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @Order(2)
    void createAnotherCategory() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/categories/")
                        .content(objectMapper.writeValueAsString(new CategoryDto("fmcj")))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.pk").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("fmcj"));
    }

    @Test
    @Order(3)
    void listCategories() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/categories/"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }

    @Test
    @Order(4)
    void getCategory() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/categories/1/"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.pk").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Laptops"));
    }


    @Test
    @Order(5)
    void updateCategory() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/categories/%s/".formatted(2))
                        .content(objectMapper.writeValueAsString(new CategoryDto("FMCG")))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.jsonPath("$.pk").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("FMCG"));
    }

    @Test
    @Order(6)
    void deleteCategory() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/categories/%s/".formatted(2)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isAccepted());
    }

    @Test
    @Order(7)
    void listCategoriesAfterDelete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/categories/"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1));
    }

}