package com.seekartist.server;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface Utilizador_Repository extends JpaRepository<Utilizador_Entity, Integer> {

    Optional<Utilizador_Entity> getUtilizadorByUsername(String username);
    Optional<Utilizador_Entity> getUtilizadorByPassword(String password);
    Optional<Utilizador_Entity> getUtilizadorByEmail(String email);
    Optional<List<Utilizador_Entity>> getAllByType(String type);
}