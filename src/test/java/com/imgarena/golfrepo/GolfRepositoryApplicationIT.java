package com.imgarena.golfrepo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.imgarena.golfrepo.helper.GolfTournamentEntryHelper;
import com.imgarena.golfrepo.model.GolfTournament;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class GolfRepositoryApplicationIT {

	private static final String SOURCE_1_ENTRY = "{\"tournamentId\":\"174638\",\"tournamentName\":\"Women's Open Championship\",\"forecast\":\"fair\",\"courseName\":\"Sunnydale Golf Course\",\"countryCode\":\"GB\",\"startDate\":\"09/07/21\",\"endDate\":\"13/07/21\",\"roundCount\":\"4\"}";
	private static final String SOURCE_2_ENTRY = "{\"tournamentUUID\":\"southWestInvitational\",\"golfCourse\":\"Happy Days Golf Club\",\"competitionName\":\"South West Invitational\",\"hostCountry\":\"United States Of America\",\"epochStart\":\"1638349200\",\"epochFinish\":\"1638468000\",\"rounds\":\"2\",\"playerCount\":\"35\"}";
	private static final String UNKNOWN_SOURCE_ENTRY = "{\"exId\":\"1234\",\"venue\":\"Green club\",\"competitionName\":\"Green Cup in Green\",\"hostedBy\":\"Ireland\",\"startsOn\":\"May 4th 2021\",\"endsOn\":\"May 11th 2021\",\"revolutions\":\"7\"}";
	private static final String ANOTHER_SOURCE_1_ENTRY = "{\"tournamentId\":\"78\",\"tournamentName\":\"Women's Open Championship\",\"forecast\":\"fair\",\"courseName\":\"Sunnydale Golf Course\",\"countryCode\":\"GB\",\"startDate\":\"09/07/21\",\"endDate\":\"13/07/21\",\"roundCount\":\"4\"}";

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;
	private ObjectMapper mapper;

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
		mapper = new ObjectMapper().registerModule(new JavaTimeModule());
	}

	@Test
	public void should_controller_exist() {
		ServletContext servletContext = webApplicationContext.getServletContext();

		assertThat(servletContext).isNotNull();
		assertThat(servletContext instanceof MockServletContext).isTrue();
		assertThat(webApplicationContext.getBean("golfTournamentController")).isNotNull();
	}

	@Test
	public void should_return_golf_tournament_when_inserting_from_source_one() throws Exception {
		MvcResult mvcResult = mockMvc.perform(
					post("/tournaments")
							.content(SOURCE_1_ENTRY)
							.contentType(MediaType.APPLICATION_JSON)
							.header("SOURCE", "SOURCE_1"))
				.andDo(print())
				.andExpect(status().isCreated())
				.andReturn();

		GolfTournament response = mapper.readValue(mvcResult.getResponse().getContentAsString(), GolfTournament.class);

		GolfTournament expected = GolfTournamentEntryHelper.createUnpersistedGolfTournamentFromSourceOne(1L);
		assertThat(response).isNotNull().isEqualTo(expected);
		assertThat(response.getId()).isNotNull();
	}

	@Test
	public void should_return_golf_tournament_when_inserting_from_source_two() throws Exception {
		MvcResult mvcResult = mockMvc.perform(
					post("/tournaments")
							.content(SOURCE_2_ENTRY)
							.contentType(MediaType.APPLICATION_JSON)
							.header("SOURCE", "SOURCE_2"))
				.andDo(print())
				.andExpect(status().isCreated())
				.andReturn();

		GolfTournament response = mapper.readValue(mvcResult.getResponse().getContentAsString(), GolfTournament.class);

		GolfTournament expected = GolfTournamentEntryHelper.createPersistedGolfTournamentFromSourceTwo(1L);
		assertThat(response).isNotNull().isEqualTo(expected);
		assertThat(response.getId()).isNotNull();
	}

	@Test
	public void should_throw_conflict_error_when_trying_to_post_duplicate() throws Exception {
		mockMvc.perform(
				post("/tournaments")
						.content(ANOTHER_SOURCE_1_ENTRY)
						.contentType(MediaType.APPLICATION_JSON)
						.header("SOURCE", "SOURCE_1"))
				.andDo(print())
				.andExpect(status().isCreated())
				.andReturn();

		mockMvc.perform(
				post("/tournaments")
						.content(ANOTHER_SOURCE_1_ENTRY)
						.contentType(MediaType.APPLICATION_JSON)
						.header("SOURCE", "SOURCE_1"))
				.andDo(print())
				.andExpect(status().isConflict())
				.andExpect(content().string("Data integrity violation."))
				.andReturn();
	}


	@Test
	public void should_return_404_when_no_provided_source() throws Exception {
		mockMvc.perform(
					post("/tournaments")
							.content(SOURCE_2_ENTRY)
							.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isNotFound())
				.andReturn();
	}

	@Test
	public void should_return_404_when_provided_source_in_unknown() throws Exception {
		mockMvc.perform(
				post("/tournaments")
						.content(UNKNOWN_SOURCE_ENTRY)
						.contentType(MediaType.APPLICATION_JSON)
						.header("SOURCE", "NOBODY_KNOWS_ABOUT_ME"))
				.andDo(print())
				.andExpect(status().isNotFound())
				.andReturn();
	}

}
