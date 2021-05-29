package com.imgarena.golfrepo.service;

import com.imgarena.golfrepo.model.GolfTournament;
import com.imgarena.golfrepo.model.datasources.GolfTournamentDataSource;
import com.imgarena.golfrepo.persistance.GolfTournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GolfTournamentService {

    private final GolfTournamentRepository golfTournamentRepository;

    @Autowired
    public GolfTournamentService(GolfTournamentRepository golfTournamentRepository) {
        this.golfTournamentRepository = golfTournamentRepository;
    }

    public GolfTournament create(GolfTournamentDataSource golfTournamentDataSource) {
        return golfTournamentRepository.save(golfTournamentDataSource.convertToGolfTournament());
    }

}
