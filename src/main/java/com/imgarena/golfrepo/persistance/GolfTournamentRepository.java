package com.imgarena.golfrepo.persistance;

import com.imgarena.golfrepo.model.GolfTournament;
import com.imgarena.golfrepo.model.Source;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource
public interface GolfTournamentRepository extends CrudRepository<GolfTournament, Long> {

    @Override
    @RestResource(exported = false)
    <S extends GolfTournament> S save(S s);

    @Override
    @RestResource(exported = false)
    <S extends GolfTournament> Iterable<S> saveAll(Iterable<S> iterable);

    @Override
    @RestResource(exported = false)
    void deleteById(Long id);

    @Override
    @RestResource(exported = false)
    void delete(GolfTournament entity);

    @Override
    @RestResource(exported = false)
    void deleteAllById(Iterable<? extends Long> iterable);

    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends GolfTournament> iterable);

    @Override
    @RestResource(exported = false)
    void deleteAll();

    List<GolfTournament> findByCountry(String country);
    List<GolfTournament> findByName(String name);
    List<GolfTournament> findByNameAndCountry(String name, String country);
    List<GolfTournament> findByRounds(Long rounds);
    List<GolfTournament> findBySource(Source source);
}
