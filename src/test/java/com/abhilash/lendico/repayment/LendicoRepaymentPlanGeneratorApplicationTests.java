package com.abhilash.lendico.repayment;

import com.abhilash.lendico.repayment.web.RepaymentPlanRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
class LendicoRepaymentPlanGeneratorApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	private static final ObjectMapper mapper = new ObjectMapper();

	@Test
	public void getRepaymentPlans() throws Exception {
		RepaymentPlanRequest request = new RepaymentPlanRequest();
		request.setLoanAmount(new BigDecimal(5000));
		request.setNominalRate(5.0);
		request.setDuration(24);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		try {
			request.setStartDate(formatter.parse("2018-01-01T00:00:01Z"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		mockMvc.perform(post("/generate-plan")
				.content(mapper.writeValueAsString(request))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(24)))
				.andExpect(jsonPath("$[0].borrowerPaymentAmount", is("219.36")))
				.andExpect(jsonPath("$[0].initialOutstandingPrincipal", is("5000.00")))
				.andExpect(jsonPath("$[0].interest", is("20.83")))
				.andExpect(jsonPath("$[0].principal", is("198.53")))
				.andExpect(jsonPath("$[0].remainingOutstandingPrincipal", is("4801.47")))
				.andExpect(jsonPath("$[1].borrowerPaymentAmount", is("219.36")))
				.andExpect(jsonPath("$[1].initialOutstandingPrincipal", is("4801.47")))
				.andExpect(jsonPath("$[1].interest", is("20.01")))
				.andExpect(jsonPath("$[1].principal", is("199.35")))
				.andExpect(jsonPath("$[1].remainingOutstandingPrincipal", is("4602.12")))
				.andExpect(jsonPath("$[23].borrowerPaymentAmount", is("219.28")))
				.andExpect(jsonPath("$[23].initialOutstandingPrincipal", is("218.37")))
				.andExpect(jsonPath("$[23].interest", is("0.91")))
				.andExpect(jsonPath("$[23].principal", is("218.37")))
				.andExpect(jsonPath("$[23].remainingOutstandingPrincipal", is("0.00")));
	}
}
