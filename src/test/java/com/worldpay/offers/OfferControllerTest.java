package com.worldpay.offers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OfferControllerTest {

	
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void offers_should_be_return_as_list() throws Exception {

		this.mockMvc.perform(get("/offers")).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$").isArray());
	}

	@Test
	public void offer_should_be_return() throws Exception {

		this.mockMvc.perform(get("/offers/5")).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$").isMap());
	}

	@Test
	public void offer_should_not_be_return() throws Exception {
		this.mockMvc.perform(get("/offers/1000")).andDo(print()).andExpect(status().is4xxClientError());
	}
	
	@Test
	public void register_offer() {
		
		try {
			mockMvc.perform(post("/offers").contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.param("productName","productName")
					.param("productDescription","productDescription")
					.param("price","234.56")
					.param("currency","EUR")
					).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$").isMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Test
	public void update_offer() throws Exception {

		try {
			mockMvc.perform(put("/offers")
					.param("productId","1")
					.param("productName","productName")
					.param("productDescription","productDescription")
					.param("price","234.56")
					.param("currency","EUR")
					).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$").exists()).andExpect(jsonPath("$.price").value("234.56"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.mockMvc.perform(get("/offers")).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$").isArray()).andExpect(jsonPath("$[0].price").value("234.56"));
	}
	
	@Test
	public void update_offer_with_invalid_id() throws Exception {

		try {
			mockMvc.perform(put("/offers")
					.param("productId","999999")
					.param("productName","productName")
					.param("productDescription","productDescription")
					.param("price","234.56")
					.param("currency","EUR")
					).andDo(print()).andExpect(status().is4xxClientError());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Test
	public void delete_offer() throws Exception {
		this.mockMvc.perform(delete("/offers/1")).andDo(print()).andExpect(status().isOk());
		this.mockMvc.perform(get("/offers/1")).andDo(print()).andExpect(status().is4xxClientError());
	}
}
