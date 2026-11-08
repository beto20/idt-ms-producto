package org.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ProductControllerTest {
    @Autowired
    WebApplicationContext context;

    @Test
    void registeredProductAppearsInCatalog() throws Exception {
        MockMvc mvc = MockMvcBuilders.webAppContextSetup(context).build();

        mvc.perform(post("/api/v1/products/register")
                        .contentType("application/json")
                        .content("""
                                {"name":"Teclado","description":"Mecánico","stock":3,"price":45.5}
                                """))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        mvc.perform(get("/api/v1/products/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[8].name").value("Teclado"))
                .andExpect(jsonPath("$[8].id").value(1008));

        mvc.perform(options("/api/v1/products/register")
                        .header("Origin", "http://localhost:5500")
                        .header("Access-Control-Request-Method", "POST"))
                .andExpect(status().isOk())
                .andExpect(header().string("Access-Control-Allow-Origin", "http://localhost:5500"));
    }
}
