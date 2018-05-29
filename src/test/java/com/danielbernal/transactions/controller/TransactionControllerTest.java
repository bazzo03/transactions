package com.danielbernal.transactions.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
public class TransactionControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void createTransaction_shouldSuccess() throws Exception {

		long now = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) * 1000;
		String jsonBody = "{\n" + "	\"amount\":\"1\",\n" + "	\"timestamp\":\"" + now + "\"\n" + "}";
		this.mockMvc.perform(post("/transactions").contentType("application/json;charset=UTF-8").content(jsonBody))
				.andDo(print()).andExpect(status().isCreated());
	}

	@Test
	public void createTransaction_minus59seconds_shouldSuccess() throws Exception {

		long now = LocalDateTime.now().plusSeconds(-59L).toEpochSecond(ZoneOffset.UTC) * 1000;
		String jsonBody = "{\n" + "	\"amount\":\"1\",\n" + "	\"timestamp\":\"" + now + "\"\n" + "}";
		this.mockMvc.perform(post("/transactions").contentType("application/json;charset=UTF-8").content(jsonBody))
				.andDo(print()).andExpect(status().isCreated());
	}

	@Test
	public void createTransaction_shouldFail() throws Exception {

		long now = (LocalDateTime.now().plusSeconds(-70L).toEpochSecond(ZoneOffset.UTC) * 1000);
		String jsonBody = "{\n" + "	\"amount\":\"1\",\n" + "	\"timestamp\":\"" + now + "\"\n" + "}";
		this.mockMvc.perform(post("/transactions").contentType("application/json;charset=UTF-8").content(jsonBody))
				.andDo(print()).andExpect(status().isNoContent());
	}

}
