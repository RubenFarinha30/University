package com.roytuts.spring.boot.security.form.based.jdbc.userdetailsservice.auth.model;
import java.util.*;
import java.text.SimpleDateFormat;

public class Evento {

    private Integer eID;
    private String ename;
    private Date edate;
    private Float eprice;
    private String edescription;

    public Evento() {
    }

    public Evento(Integer eID, String ename, Date edate, Float eprice, String edescription) {
    	this.eID = eID;
        this.ename = ename;
        this.edate = edate;
        this.eprice = eprice;
        this.edescription = edescription;
    }

	public Integer geteID() {
		return eID;
	}

	public void seteID(Integer eID) {
		this.eID = eID;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public Date getEdate() {
		return edate;
	}

	public void setEdate(Date edate) {
		this.edate = edate;
	}

	public Float getEprice() {
		return eprice;
	}

	public void setEprice(Float eprice) {
		this.eprice = eprice;
	}

	public String getEdescription() {
		return edescription;
	}

	public void setEdescription(String edescription) {
		this.edescription = edescription;
	}
    
	@Override
    public String toString() {
        String formattedDate = new SimpleDateFormat("dd/MM/yyyy").format(edate);
        return "Event:[" + eID + ", " + ename + ", " + formattedDate + ", " + eprice + ", " + edescription + "]";
    }
}