package com.roytuts.spring.boot.security.form.based.jdbc.userdetailsservice.auth.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.roytuts.spring.boot.security.form.based.jdbc.userdetailsservice.auth.model.Atleta;
import com.roytuts.spring.boot.security.form.based.jdbc.userdetailsservice.auth.rowmapper.AtletaRowMapper;
import java.sql.Timestamp;
import java.util.*;

@Repository
public class AtletaDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Atleta> getAllAtletas(Integer eventID) {
        return jdbcTemplate.query(
                "SELECT * FROM Atleta WHERE idEvento = ?",
                new Object[] { eventID },
                new AtletaRowMapper());
    }

    public void saveAtleta(final Atleta A) {
        String query = "INSERT INTO Atleta(dorsal, idEvento, username, nomeAtleta, nif, genero, escalao, paystatus) VALUES ('"
                +
                A.getDorsal() + "','" +
                A.getIdEvento() + "','" +
                A.getUsername() + "','" +
                A.getNomeAtleta() + "','" +
                A.getNif() + "','" +
                A.getGenero() + "','" +
                A.getEscalao() + "','" +
                A.isPaystatus() +
                "')";

        jdbcTemplate.execute(query);
        System.out.println("AtletaDAO saved: \n" + query + "\n");
    }
    
    public Atleta getAtleta(Integer idEvento, Integer Dorsal) {
        List<Atleta> result = jdbcTemplate.query(
                "SELECT * FROM Atleta WHERE idEvento =" + idEvento + " AND Dorsal=" + Dorsal,
                new AtletaRowMapper());
        return result.isEmpty() ? null : result.get(0);
    }

    public Integer countDorsal(Integer idEvento) {
        return jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM Atleta WHERE idEvento = ?",
                Integer.class,
                idEvento) + 1;
    }

    public void saveTotalTime(final Atleta A) {

        String query = "UPDATE Atleta SET Ttime = " + A.getTtime() +
                " WHERE Dorsal = " + A.getDorsal() + " AND idEvento = " + A.getIdEvento();
        jdbcTemplate.execute(query);

    }

    public void saveTime(Integer Dorsal, Integer idEvento, String local, Timestamp T) {

        String query = "UPDATE Atleta SET " + local + " = " + T +
                " WHERE Dorsal = " + Dorsal + " AND idEvento = " + idEvento;
        jdbcTemplate.execute(query);

    }

    public List<Atleta> getAllAtletasByTime(Integer idEvento) {
        return jdbcTemplate.query(
                "SELECT * FROM Atleta WHERE idEvento = ? ORDER BY CASE WHEN Ttime = 0 THEN 1 ELSE 0 END, Ttime",
                new Object[] { idEvento },
                new AtletaRowMapper());
    }

    public List<Atleta> getregisterusername(String username) {
        return jdbcTemplate.query(
                "SELECT * FROM Atleta WHERE user_name = ?",
                new Object[] { username },
                new AtletaRowMapper());
    }

    public void payment(String ent, String ref, Integer Dorsal, Integer idEvento) {

        String query = "UPDATE Atleta SET entity= " + ent + ", ref=" + ref +
                " WHERE Dorsal = " + Dorsal + " AND idEvento = " + idEvento;
        jdbcTemplate.execute(query);

    }

    public void pay(final Atleta A) {
        String query = "UPDATE Atleta SET paystatus= " + A.isPaystatus() + " WHERE Dorsal = " + A.getDorsal()
                + " AND idEvento = " + A.getIdEvento();
        jdbcTemplate.execute(query);

    }
}
