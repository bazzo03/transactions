package com.danielbernal.transactions.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.danielbernal.transactions.TransactionsApplication;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@WebMvcTest(TransactionsApplication.class)
@RunWith(SpringRunner.class)
public class StatisticControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
    public void obtainStatistics_notValidTransaction_shouldSuccess () throws Exception {
		
		long now = LocalDateTime.now().plusSeconds(-61L).toEpochSecond(ZoneOffset.UTC) * 1000;
		String jsonBody = "{\n" + "	\"amount\":\"1\",\n" + "	\"timestamp\":\"" + now + "\"\n" + "}";
		this.mockMvc.perform(post("/transactions").contentType("application/json;charset=UTF-8").content(jsonBody));
		
		String responseJson = "{\"sum\":0.0,\"avg\":0.0,\"max\":0.0,\"min\":0.0,\"count\":0}";
		this.mockMvc.perform(get("/statistics"))
				.andDo(print()).andExpect(content().json(responseJson));
    }

	@Test
	public void obtainStatistics_validTransaction_shouldSuccess () throws Exception {
		
		long now = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) * 1000;
		String jsonBody = "{\n" + "	\"amount\":\"1\",\n" + "	\"timestamp\":\"" + now + "\"\n" + "}";
		this.mockMvc.perform(post("/transactions").contentType("application/json;charset=UTF-8").content(jsonBody));
		
		String responseJson = "{\"sum\":1.0,\"avg\":1.0,\"max\":1.0,\"min\":1.0,\"count\":1}";
		this.mockMvc.perform(get("/statistics"))
		.andDo(print()).andExpect(content().json(responseJson));
	}

	@Test
	public void obtainStatistics_noTransaction_shouldSuccess () throws Exception {
		
		String responseJson = "{\"sum\":0.0,\"avg\":0.0,\"max\":0.0,\"min\":0.0,\"count\":0}";
		this.mockMvc.perform(get("/statistics"))
		.andDo(print()).andExpect(content().json(responseJson));
	}
	
}
