package com.roytuts.spring.boot.security.form.based.jdbc.userdetailsservice.auth.model;

import java.sql.Timestamp;

public class Atleta {

    private Integer dorsal;
    private Integer idEvento;
    private String username;
    private String nomeAtleta;
    private Integer nif;
    private String genero;
    private String escalao;
    private boolean paystatus;
    private Timestamp start;
    private Timestamp p1;
    private Timestamp p2;
    private Timestamp p3;
    private Timestamp finish;
    private long ttime;
    private Integer entity;
    private Integer ref;

    public Atleta() {
    }

    public Atleta(Integer dorsal, Integer idEvento, String username, String nomeAtleta, Integer nif, String genero,
                  String escalao, boolean paystatus, Timestamp start, Timestamp p1, Timestamp p2, Timestamp p3,
                  Timestamp finish, long ttime, Integer entity, Integer ref) {
        this.dorsal = dorsal;
        this.idEvento = idEvento;
        this.username = username;
        this.nomeAtleta = nomeAtleta;
        this.nif = nif;
        this.genero = genero;
        this.escalao = escalao;
        this.paystatus = paystatus;
        this.start = start;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.finish = finish;
        this.ttime = ttime;
        this.entity = entity;
        this.ref = ref;
    }

	public Integer getDorsal() {
		return dorsal;
	}

	public void setDorsal(Integer dorsal) {
		this.dorsal = dorsal;
	}

	public Integer getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(Integer idEvento) {
		this.idEvento = idEvento;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNomeAtleta() {
		return nomeAtleta;
	}

	public void setNomeAtleta(String nomeAtleta) {
		this.nomeAtleta = nomeAtleta;
	}

	public Integer getNif() {
		return nif;
	}

	public void setNif(Integer nif) {
		this.nif = nif;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getEscalao() {
		return escalao;
	}

	public void setEscalao(String escalao) {
		this.escalao = escalao;
	}

	public boolean isPaystatus() {
		return paystatus;
	}

	public void setPaystatus(boolean paystatus) {
		this.paystatus = paystatus;
	}

	public Timestamp getStart() {
		return start;
	}

	public void setStart(Timestamp start) {
		this.start = start;
	}

	public Timestamp getP1() {
		return p1;
	}

	public void setP1(Timestamp p1) {
		this.p1 = p1;
	}

	public Timestamp getP2() {
		return p2;
	}

	public void setP2(Timestamp p2) {
		this.p2 = p2;
	}

	public Timestamp getP3() {
		return p3;
	}

	public void setP3(Timestamp p3) {
		this.p3 = p3;
	}

	public Timestamp getFinish() {
		return finish;
	}

	public void setFinish(Timestamp finish) {
		this.finish = finish;
	}

	public long getTtime() {
		return ttime;
	}

	public void setTtime(long ttime) {
		this.ttime = ttime;
	}

	public Integer getEntity() {
		return entity;
	}

	public void setEntity(Integer entity) {
		this.entity = entity;
	}

	public Integer getRef() {
		return ref;
	}

	public void setRef(Integer ref) {
		this.ref = ref;
	}
    
	@Override
    public String toString() {
        return "Participant [Event_Dorsal=" + dorsal + ", Username=" + username + ", Event_ID=" + idEvento
                + ", name=" + nomeAtleta + ", gender=" + genero + ", escalao=" + escalao + ", start=" + start + ", P1=" + p1
                + ", P2=" + p2 + ", P3=" + p3 + ", finish=" + finish + ", pago=" + paystatus + ", ttime=" + ttime + "]";
    }

}
