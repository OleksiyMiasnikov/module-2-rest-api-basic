package com.epam.esm.controllers;

import com.epam.esm.config.SpringConfig;
import com.epam.esm.models.Certificate;
import com.epam.esm.services.CertificateService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { SpringConfig.class })
@WebAppConfiguration

class CertificateControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private CertificateService service;
    private MockMvc mockMvc;
    @BeforeEach
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void givenWac_whenServletContext_thenItProvidesGreetController() {
        ServletContext servletContext = webApplicationContext.getServletContext();

        assertNotNull(servletContext);
        assertTrue(servletContext instanceof MockServletContext);
        assertNotNull(webApplicationContext.getBean("certificateController"));
    }

    @Test
    public void givenHomePageURI_whenMockMVC_thenReturnsIndexJSPViewName() throws Exception {
//        this.mockMvc.perform(get("/"))
//                .andDo(print())
//                .andExpect(service.create());
    }
    @Test
    void create() {
    }

    @Test
    void findAll() throws Exception {
        List<Certificate> list = new LinkedList<>();
        this.mockMvc.perform(get("/certificates"))
                .andDo(print())
                .andExpect(status().isOk());
                //.andExpect(MockMvcResultMatchers.jsonPath("$.certificates").exists())
                //.andExpect(MockMvcResultMatchers.jsonPath("$.certificates[*].id").isNotEmpty());
    }

    @Test
    void findById() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}