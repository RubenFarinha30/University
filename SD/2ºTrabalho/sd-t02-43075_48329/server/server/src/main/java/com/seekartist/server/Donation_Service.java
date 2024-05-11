package com.seekartist.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.*;

@Service
public class Donation_Service {

    private Donation_Entity donation;
    private final Donation_Repository donationRepository;

    @Autowired
    public Donation_Service(Donation_Repository donationRepository){
        this.donationRepository = donationRepository;
    }

    @PostMapping
    public void addDonation(Integer artistid, String username, Integer donationamount, String date){
        donation = new Donation_Entity(artistid, username, donationamount, date);
        donationRepository.save(donation);
    }

    @GetMapping
    public List<Donation_Entity> getAllDonations(){
        return donationRepository.findAll();
    }

    public List<Donation_Entity> getAllByArtistid(Integer artistid) {
        List<Donation_Entity> list;
        list = donationRepository.getAllByArtistid(artistid).orElseThrow( () -> new IllegalStateException("Não existem donations a este artista."));

        return list;
    }

}