package com.imgarena.golfrepo.service;


import com.imgarena.golfrepo.helper.GolfTournamentEntryHelper;
import com.imgarena.golfrepo.model.GolfTournament;
import com.imgarena.golfrepo.model.datasources.SourceTwoGolfTournament;
import com.imgarena.golfrepo.persistance.GolfTournamentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class GolfTournamentServiceTest {

    @Mock
    private GolfTournamentRepository golfTournamentRepository;

    @InjectMocks
    private GolfTournamentService service;

    @Test
    public void should_create_tournament() {
        //Given
        GolfTournament tournament = GolfTournamentEntryHelper.createUnpersistedGolfTournamentFromSourceTwo();
        GolfTournament persistedTournament =
                GolfTournamentEntryHelper.createPersistedGolfTournamentFromSourceTwo(1L);
        SourceTwoGolfTournament sourceTwoGolfTournament = mock(SourceTwoGolfTournament.class);
        when(sourceTwoGolfTournament.convertToGolfTournament()).thenReturn(tournament);
        when(golfTournamentRepository.save(eq(tournament))).thenReturn(persistedTournament);

        //When
        GolfTournament createdTournament = service.create(sourceTwoGolfTournament);

        //Then
        assertThat(createdTournament).isEqualTo(persistedTournament);
        assertThat(createdTournament.getId()).isEqualTo(1L);
    }
}
