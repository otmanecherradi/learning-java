package me.otmane.assignments.springboot.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.otmane.assignments.springboot.SpringBootAssignmentApplication;
import me.otmane.assignments.springboot.controllers.dto.CategoryDto;
import me.otmane.assignments.springboot.controllers.dto.ProductDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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
class ProductsControllerTest {

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
    void createProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/products/")
                        .content(objectMapper.writeValueAsString(new ProductDto("Macbook Pro", 1L)))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.pk").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Macbook Pro"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.category.name").value("Laptops"));
    }

    @Test
    @Order(3)
    void createAnotherProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/products/")
                        .content(objectMapper.writeValueAsString(new ProductDto("Macbok Air", 1L)))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.pk").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Macbok Air"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.category.name").value("Laptops"));
    }

    @Test
    @Order(4)
    void listProducts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products/"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }

    @Test
    @Order(5)
    void getProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products/1/"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.pk").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Macbook Pro"));
    }


    @Test
    @Order(6)
    void updateProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/products/%s/".formatted(2))
                        .content(objectMapper.writeValueAsString(new ProductDto("Macbook Air", 1L)))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.jsonPath("$.pk").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Macbook Air"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.category.name").value("Laptops"));
    }

    @Test
    @Order(7)
    void deleteProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/products/%s/".formatted(2)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isAccepted());
    }

    @Test
    @Order(8)
    void listProductsAfterDelete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products/"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1));
    }

}