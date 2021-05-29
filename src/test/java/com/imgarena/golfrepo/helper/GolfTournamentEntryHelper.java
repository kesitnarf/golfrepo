package com.imgarena.golfrepo.helper;

import com.imgarena.golfrepo.model.GolfTournament;
import com.imgarena.golfrepo.model.Source;
import com.imgarena.golfrepo.model.datasources.SourceOneGolfTournament;
import com.imgarena.golfrepo.model.datasources.SourceTwoGolfTournament;

import java.time.LocalDateTime;

public abstract class GolfTournamentEntryHelper {
    public static SourceOneGolfTournament creeatSourceOneTournament() {
        return new SourceOneGolfTournament("174638", "Women's Open Championship",
                "fair", "Sunnydale Golf Course", "GB",
                "09/07/21", "13/07/21", 4L);
    }

    public static SourceTwoGolfTournament createSourceTwoTournament(String country) {
        return new SourceTwoGolfTournament("southWestInvitational", "Happy Days Golf Club",
                "South West Invitational", country,
                1638349200L, 1638468000L, 2L, 35L);
    }

    public static GolfTournament createUnpersistedGolfTournamentFromSourceOne() {
        return new GolfTournament(Source.SOURCE_1, "174638", "Women's Open Championship",
                "fair", "Sunnydale Golf Course", "GB",
                LocalDateTime.of(2021, 7, 9, 0, 0),
                LocalDateTime.of(2021, 7, 13, 0, 0),
                4L, null);
    }

    public static GolfTournament createUnpersistedGolfTournamentFromSourceOne(Long id) {
        GolfTournament golfTournament = new GolfTournament(Source.SOURCE_1, "174638",
                "Women's Open Championship", "fair", "Sunnydale Golf Course", "GB",
                LocalDateTime.of(2021, 7, 9, 0, 0),
                LocalDateTime.of(2021, 7, 13, 0, 0),
                4L, null);
        golfTournament.setId(id);
        return golfTournament;
    }

    public static GolfTournament createUnpersistedGolfTournamentFromSourceTwo() {
        return new GolfTournament(
                Source.SOURCE_2, "southWestInvitational", "South West Invitational",
                null, "Happy Days Golf Club", "US",
                LocalDateTime.of(2021, 12, 1, 9, 0),
                LocalDateTime.of(2021, 12, 2, 18, 0),
                2L, 35L);
    }

    public static GolfTournament createPersistedGolfTournamentFromSourceTwo(Long id) {
        GolfTournament golfTournament = new GolfTournament(
                Source.SOURCE_2, "southWestInvitational", "South West Invitational",
                null, "Happy Days Golf Club", "US",
                LocalDateTime.of(2021, 12, 1, 9, 0),
                LocalDateTime.of(2021, 12, 2, 18, 0),
                2L, 35L);
        golfTournament.setId(id);
        return golfTournament;
    }

}
