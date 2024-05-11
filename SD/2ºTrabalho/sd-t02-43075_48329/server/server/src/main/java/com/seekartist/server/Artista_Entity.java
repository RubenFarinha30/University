package com.seekartist.server;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="artista")
public class Artista_Entity{

    private @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    Integer id;
    private String name;
    private String type;
    private Integer latitude;
    private Integer longitude;
    private String acting;
    private String approval;
    
    public Artista_Entity() {
		this.name = "";
		this.type = "";
		this.latitude = 0;
		this.longitude = 0;
		this.acting = "";
		this.approval = "inativo"; 	
    }

	public Artista_Entity(String name, String type, Integer latitude, Integer longitude,
			String acting, String approval) {
		super();
		this.name = name;
		this.type = type;
		this.latitude = latitude;
		this.longitude = longitude;
		this.acting = acting;
		this.approval = "inativo";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public String gettype() {
		return type;
	}

	public void settype(String type) {
		this.type = type;
	}

	public Integer getLatitude() {
		return latitude;
	}

	public void setLatitude(Integer latitude) {
		this.latitude = latitude;
	}

	public Integer getLongitude() {
		return longitude;
	}

	public void setLongitude(Integer longitude) {
		this.longitude = longitude;
	}

	public String getActing() {
		return acting;
	}

	public void setActing(String acting) {
		this.acting = acting;
	}

	public String getApproval() {
		return approval;
	}

	public void setApproval(String approval) {
		this.approval = approval;
	}
      
}
