package com.imgarena.golfrepo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"source", "externalId"}))
public class GolfTournament {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Source source;
    private String externalId;
    private String name;
    private String forecast;
    private String course;
    private String country;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long rounds;
    private Long playerCount;

    public GolfTournament() {
    }

    public GolfTournament(Source source, String externalId, String name, String forecast, String course,
                          String country, LocalDateTime startDate, LocalDateTime endDate, Long rounds, Long playerCount) {
        this.source = source;
        this.externalId = externalId;
        this.name = name;
        this.forecast = forecast;
        this.course = course;
        this.country = country;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rounds = rounds;
        this.playerCount = playerCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getForecast() {
        return forecast;
    }

    public void setForecast(String forecast) {
        this.forecast = forecast;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Long getRounds() {
        return rounds;
    }

    public void setRounds(Long rounds) {
        this.rounds = rounds;
    }

    public Long getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(Long playerCount) {
        this.playerCount = playerCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GolfTournament that = (GolfTournament) o;
        return source == that.source && Objects.equals(externalId, that.externalId) && Objects.equals(name, that.name) && Objects.equals(forecast, that.forecast) && Objects.equals(course, that.course) && Objects.equals(country, that.country) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && Objects.equals(rounds, that.rounds) && Objects.equals(playerCount, that.playerCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, externalId, name, forecast, course, country, startDate, endDate, rounds, playerCount);
    }
}
