package com.tutuka.reconciliation.test;

import static org.assertj.core.api.Assertions.assertThat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.tutuka.reconciliation.controller.FileController;

/**
 * @author Rishabh
 * 
 * Test class to validate context and controller loads as expected
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest
public class ApplicationTest {

	@Autowired
    private FileController controller;
	
	@Autowired
    private MockMvc mockMvc;
	
    @Test
    public void contextLoads() throws Exception {
    	assertThat(controller).isNotNull();
    }
    
    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/"))
        			.andExpect(status().isOk())
        			.andExpect(view().name("recon"));
    }

}
