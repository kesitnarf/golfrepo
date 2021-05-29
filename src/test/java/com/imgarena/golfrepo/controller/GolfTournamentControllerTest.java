package com.imgarena.golfrepo.controller;

import com.imgarena.golfrepo.helper.GolfTournamentEntryHelper;
import com.imgarena.golfrepo.model.GolfTournament;
import com.imgarena.golfrepo.model.datasources.SourceOneGolfTournament;
import com.imgarena.golfrepo.model.datasources.SourceTwoGolfTournament;
import com.imgarena.golfrepo.service.GolfTournamentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class GolfTournamentControllerTest {

    @Mock
    private GolfTournamentService golfTournamentService;

    @InjectMocks
    private GolfTournamentController controller;

    @Test
    public void should_create_source_one_tournament() {
        //Given
        SourceOneGolfTournament sourceOneGolfTournament = GolfTournamentEntryHelper.creeatSourceOneTournament();
        GolfTournament tournament = GolfTournamentEntryHelper.createUnpersistedGolfTournamentFromSourceOne();
        when(golfTournamentService.create(eq(sourceOneGolfTournament))).thenReturn(tournament);

        //When
        GolfTournament golfTournament = controller.create(sourceOneGolfTournament);

        //Then
        assertThat(golfTournament).isNotNull().isEqualTo(tournament);
    }

    @Test
    public void should_create_source_two_tournaments() {
        //Given
        SourceTwoGolfTournament sourceTwoGolfTournament = GolfTournamentEntryHelper.createSourceTwoTournament("United States Of America");
        GolfTournament tournament = GolfTournamentEntryHelper.createUnpersistedGolfTournamentFromSourceTwo();
        when(golfTournamentService.create(eq(sourceTwoGolfTournament))).thenReturn(tournament);

        //When
        GolfTournament golfTournament = controller.create(sourceTwoGolfTournament);

        //Then
        assertThat(golfTournament).isNotNull().isEqualTo(tournament);
    }

    @Test
    public void should_handler_exception_return_conflict_response() {
        //When
        ResponseEntity<String> responseEntity = controller.handleDataIntegrityViolationException(new DataIntegrityViolationException("Cannot do that"));

        //Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        assertThat(responseEntity.getBody()).isEqualTo("Data integrity violation.");
    }
}
