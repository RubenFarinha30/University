package com.seekartist.server;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface Artista_Repository extends JpaRepository<Artista_Entity, Integer> {

    Optional<Artista_Entity> getArtistaById(Integer id);
    Optional<List<Artista_Entity>> getAllByType(String type);
    Optional<List<Artista_Entity>> getAllByLatitude(Integer latitude);
    Optional<List<Artista_Entity>> getAllByApproval(String approval);
    Optional<List<Artista_Entity>> getAllByActing(String acting);
    Optional<List<Artista_Entity>> getAllByLongitude(Integer longitude);
	Optional<Artista_Entity> getArtistaByName(String name);
}