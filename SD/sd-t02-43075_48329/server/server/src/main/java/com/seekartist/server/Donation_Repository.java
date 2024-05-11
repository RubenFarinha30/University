package com.seekartist.server;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface Donation_Repository extends JpaRepository<Donation_Entity, Integer> {

    Optional<List<Donation_Entity>> getAllByArtistid(Integer artistid);

}