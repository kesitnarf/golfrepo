package com.imgarena.golfrepo.model.datasources;

import com.imgarena.golfrepo.model.GolfTournament;
import com.imgarena.golfrepo.model.Source;
import com.neovisionaries.i18n.CountryCode;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class SourceTwoGolfTournament implements GolfTournamentDataSource {
    private final String tournamentUUID;
    private final String golfCourse;
    private final String competitionName;
    private final String hostCountry;
    private final long epochStart;
    private final long epochFinish;
    private final long rounds;
    private final long playerCount;

    public SourceTwoGolfTournament(String tournamentUUID, String golfCourse, String competitionName,
                                   String hostCountry, long epochStart, long epochFinish, long rounds,
                                   long playerCount) {
        this.tournamentUUID = tournamentUUID;
        this.golfCourse = golfCourse;
        this.competitionName = competitionName;
        this.hostCountry = hostCountry;
        this.epochStart = epochStart;
        this.epochFinish = epochFinish;
        this.rounds = rounds;
        this.playerCount = playerCount;
    }

    public GolfTournament convertToGolfTournament() {
        return new GolfTournament(Source.SOURCE_2,
                tournamentUUID, competitionName, null, golfCourse, convertCountry(),
                LocalDateTime.ofEpochSecond(epochStart, 0, ZoneOffset.UTC),
                LocalDateTime.ofEpochSecond(epochFinish, 0, ZoneOffset.UTC),
                rounds, playerCount);
    }

    private String convertCountry() {
        if ("United States of America".equalsIgnoreCase(hostCountry)) {
            return "US";
        }
        return CountryCode.findByName(hostCountry).stream().findFirst().map(CountryCode::getAlpha2).orElse(hostCountry);
    }
}
