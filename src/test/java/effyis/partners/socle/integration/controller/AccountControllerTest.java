package effyis.partners.socle.integration.controller;

import effyis.partners.socle.dto.RegisterDTO;
import effyis.partners.socle.dto.RegisterValidationDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import effyis.partners.socle.SocleEffyisApplication;
import effyis.partners.socle.dto.AuthenticationDTO;
import effyis.partners.socle.dto.JwtDTO;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SocleEffyisApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AccountControllerTest {
	@Autowired
	MockMvc mvc;

	@Test
	@Order(1)
	public void registerTest() throws Exception {
		RegisterDTO validRegister = new RegisterDTO();
		validRegister.setLogin("nacer@effyis-partners.com");
		validRegister.setPassword("password12345");
		validRegister.setConfirm_password("password12345");

		RegisterDTO invalidRegister = new RegisterDTO();
		invalidRegister.setLogin("nacereffyis-partners...com22"); // invalid email
		invalidRegister.setPassword("pass");                      // pass min than 8
		invalidRegister.setConfirm_password("pass2");             // confirm pass defferent than pass

		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter writer = mapper.writer();

		String requestBody = writer.writeValueAsString(validRegister);
		MvcResult result = this.mvc.perform(MockMvcRequestBuilders.post("/effyis/api/accounts/register")
				.contentType("application/json").content(requestBody)).andReturn();
		JwtDTO resultDto = mapper.readValue(result.getResponse().getContentAsString(), JwtDTO.class);

		String requestBody2 = writer.writeValueAsString(invalidRegister);
		MvcResult result2 = this.mvc.perform(MockMvcRequestBuilders.post("/effyis/api/accounts/register")
				.contentType("application/json").content(requestBody2)).andReturn();
		RegisterValidationDto registerValidationDto = mapper.readValue(result2.getResponse().getContentAsString(), RegisterValidationDto.class);

		Assert.assertNotNull(resultDto.getJwt());
		Assert.assertNotNull(registerValidationDto.getPassword());
		Assert.assertNotNull(registerValidationDto.getOk());
		Assert.assertNotNull(registerValidationDto.getConfirm_password());
		Assert.assertNotNull(registerValidationDto.getLogin());
		Assert.assertEquals(result2.getResponse().getStatus(),400);
	}

	@Test
	@Order(2)
	public void authenticationTest() throws Exception {
		AuthenticationDTO authenticationDTO = new AuthenticationDTO();
		authenticationDTO.setLogin("nacer@indatacore.com");
		authenticationDTO.setPassword("password12345");
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter writer = mapper.writer();
		String requestBody = writer.writeValueAsString(authenticationDTO);
		MvcResult result = this.mvc.perform(MockMvcRequestBuilders.post("/indatacore/api/accounts/login")
				.contentType("application/json").content(requestBody)).andReturn();
		JwtDTO resultDto = mapper.readValue(result.getResponse().getContentAsString(), JwtDTO.class);
		Assert.assertNotNull(resultDto.getJwt());
	}

}
