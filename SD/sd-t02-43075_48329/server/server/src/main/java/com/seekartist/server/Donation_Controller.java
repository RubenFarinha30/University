package com.seekartist.server;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping(value="/donation")
public class Donation_Controller {

    private Donation_Service donationService;

    @Autowired
    public Donation_Controller(Donation_Service donationService){
        this.donationService = donationService;
    }

    @PostMapping(value = "/addDonation", produces = "application/json")
    public void addDonation(@RequestParam("artistid") Integer artistid,
    						@RequestParam("username") String username,
    						@RequestParam("donationamount") Integer donationamount,
    						@RequestParam("date") String date) {
        donationService.addDonation(artistid, username, donationamount, date);
    }

    @GetMapping(value="/getAllDonations")
    public List<Donation_Entity> getAllDonations(){
        return donationService.getAllDonations();
    }


    @GetMapping(value="/getDonationsByArtistid")
    public List<Donation_Entity> getAllByArtistid(@PathParam("artistid") Integer artistid){
        return donationService.getAllByArtistid(artistid);
    }

}