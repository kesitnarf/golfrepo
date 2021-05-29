package com.imgarena.golfrepo.model.datasources;

import com.imgarena.golfrepo.model.GolfTournament;
import com.imgarena.golfrepo.model.Source;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class SourceOneGolfTournament implements GolfTournamentDataSource {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yy");

    private final String tournamentId;
    private final String tournamentName;
    private final String forecast;
    private final String courseName;
    private final String countryCode;
    private final String startDate;
    private final String endDate;
    private final long roundCount;

    public SourceOneGolfTournament(String tournamentId, String tournamentName, String forecast, String courseName,
                                   String countryCode, String startDate, String endDate, long roundCount) {
        this.tournamentId = tournamentId;
        this.tournamentName = tournamentName;
        this.forecast = forecast;
        this.courseName = courseName;
        this.countryCode = countryCode;
        this.startDate = startDate;
        this.endDate = endDate;
        this.roundCount = roundCount;
    }

    public GolfTournament convertToGolfTournament() {
        return new GolfTournament(
                Source.SOURCE_1, tournamentId, tournamentName, forecast, courseName, countryCode,
                convertDate(startDate), convertDate(endDate), roundCount, null);
    }

    private static LocalDateTime convertDate(CharSequence date) {
        LocalDate localDate = LocalDate.parse(date, DATE_TIME_FORMATTER);
        return LocalDateTime.of(localDate, LocalTime.MIN);
    }
}
