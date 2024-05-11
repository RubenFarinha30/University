package com.seekartist.server;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name="donation")
public class Donation_Entity {

    @Id
    private Integer artistid;
    private String username;
    private Integer donationamount;
    private String date;

    public Donation_Entity(Integer artistid, String username, Integer donationamount, String date) {
        super();
        this.artistid = artistid;
        this.username = username;
        this.donationamount = donationamount;
        this.date = date;
    }
    
    public Donation_Entity() {
        this.artistid = 0;
        this.username = "";
        this.donationamount = 0;
        this.date = "";
    }

    public Integer getArtistid() {
        return artistid;
    }

    public void setArtistid(Integer artistid) {
        this.artistid = artistid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getDonationamount() {
        return donationamount;
    }

    public void setDonationamount(Integer donationamount) {
        this.donationamount = donationamount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}