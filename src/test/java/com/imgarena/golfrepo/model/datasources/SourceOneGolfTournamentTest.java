package com.imgarena.golfrepo.model.datasources;

import com.imgarena.golfrepo.helper.GolfTournamentEntryHelper;
import com.imgarena.golfrepo.model.GolfTournament;
import com.imgarena.golfrepo.model.Source;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class SourceOneGolfTournamentTest {

    @Test
    public void should_convert_to_golf_tournament() {
        //Given
        GolfTournamentDataSource sourceOneGolfTournament = GolfTournamentEntryHelper.creeatSourceOneTournament();

        //When
        GolfTournament convertedTournament = sourceOneGolfTournament.convertToGolfTournament();

        //Then
        assertThat(convertedTournament.getId()).isNull();
        assertThat(convertedTournament.getSource()).isEqualTo(Source.SOURCE_1);
        assertThat(convertedTournament.getExternalId()).isEqualTo("174638");
        assertThat(convertedTournament.getName()).isEqualTo("Women's Open Championship");
        assertThat(convertedTournament.getForecast()).isEqualTo("fair");
        assertThat(convertedTournament.getCourse()).isEqualTo("Sunnydale Golf Course");
        assertThat(convertedTournament.getCountry()).isEqualTo("GB");
        assertThat(convertedTournament.getStartDate()).isEqualTo(
                LocalDateTime.of(2021, 7, 9, 0,0));
        assertThat(convertedTournament.getEndDate()).isEqualTo(
                LocalDateTime.of(2021, 7, 13, 0,0));
        assertThat(convertedTournament.getRounds()).isEqualTo(4);
        assertThat(convertedTournament.getPlayerCount()).isNull();
    }

}
