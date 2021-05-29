package com.imgarena.golfrepo.model.datasources;

import com.imgarena.golfrepo.helper.GolfTournamentEntryHelper;
import com.imgarena.golfrepo.model.GolfTournament;
import com.imgarena.golfrepo.model.Source;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class SourceTwoGolfTournamentTest {

    @Test
    public void should_convert_to_golf_tournament() {
        //Given
        GolfTournamentDataSource sourceTwoGolfTournament = GolfTournamentEntryHelper.createSourceTwoTournament("United States");

        //When
        GolfTournament convertedTournament = sourceTwoGolfTournament.convertToGolfTournament();

        //Then
        assertThat(convertedTournament.getId()).isNull();
        assertThat(convertedTournament.getSource()).isEqualTo(Source.SOURCE_2);
        assertThat(convertedTournament.getExternalId()).isEqualTo("southWestInvitational");
        assertThat(convertedTournament.getName()).isEqualTo("South West Invitational");
        assertThat(convertedTournament.getForecast()).isNull();
        assertThat(convertedTournament.getCourse()).isEqualTo("Happy Days Golf Club");
        assertThat(convertedTournament.getCountry()).isEqualTo("US");
        assertThat(convertedTournament.getStartDate()).isEqualTo(LocalDateTime.of(2021, 12, 1, 9, 0));
        assertThat(convertedTournament.getEndDate()).isEqualTo(LocalDateTime.of(2021, 12, 2, 18, 0));
        assertThat(convertedTournament.getRounds()).isEqualTo(2);
        assertThat(convertedTournament.getPlayerCount()).isEqualTo(35);
    }

    @Test
    public void should_convert_country_code_properly_us_exception() {
        //Given
        GolfTournamentDataSource sourceTwoGolfTournament = GolfTournamentEntryHelper.createSourceTwoTournament("United States Of America");

        //When
        GolfTournament convertedTournament = sourceTwoGolfTournament.convertToGolfTournament();

        //Then
        assertThat(convertedTournament.getCountry()).isEqualTo("US");
    }

    @Test
    public void should_convert_country_code_properly() {
        //Given
        GolfTournamentDataSource sourceTwoGolfTournament = GolfTournamentEntryHelper.createSourceTwoTournament("Germany");

        //When
        GolfTournament convertedTournament = sourceTwoGolfTournament.convertToGolfTournament();

        //Then
        assertThat(convertedTournament.getCountry()).isEqualTo("DE");
    }

}
