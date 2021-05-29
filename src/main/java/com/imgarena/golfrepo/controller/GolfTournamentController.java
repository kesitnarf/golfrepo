package com.imgarena.golfrepo.controller;

import com.imgarena.golfrepo.model.GolfTournament;
import com.imgarena.golfrepo.model.datasources.SourceOneGolfTournament;
import com.imgarena.golfrepo.model.datasources.SourceTwoGolfTournament;
import com.imgarena.golfrepo.service.GolfTournamentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tournaments")
public class GolfTournamentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GolfTournamentController.class);

    private final GolfTournamentService golfTournamentService;

    @Autowired
    public GolfTournamentController(GolfTournamentService golfTournamentService) {
        this.golfTournamentService = golfTournamentService;
    }

    @PostMapping(headers = "SOURCE=SOURCE_1")
    @ResponseStatus(HttpStatus.CREATED)
    public GolfTournament create(@RequestBody SourceOneGolfTournament sourceOneGolfTournament) {
        return golfTournamentService.create(sourceOneGolfTournament);
    }

    @PostMapping(headers = "SOURCE=SOURCE_2")
    @ResponseStatus(HttpStatus.CREATED)
    public GolfTournament create(@RequestBody SourceTwoGolfTournament sourceTwoGolfTournament) {
        return golfTournamentService.create(sourceTwoGolfTournament);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException dataIntegrityViolationException) {
        LOGGER.error("Data integrity violation.", dataIntegrityViolationException);
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body("Data integrity violation.");
    }

}
