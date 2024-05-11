package com.roytuts.spring.boot.security.form.based.jdbc.userdetailsservice.auth.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.roytuts.spring.boot.security.form.based.jdbc.userdetailsservice.auth.model.Evento;
import com.roytuts.spring.boot.security.form.based.jdbc.userdetailsservice.auth.rowmapper.EventoRowMapper;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

@Repository
public class EventoDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Evento> getAllEventos() {
        return jdbcTemplate.query(
                "SELECT * FROM Eventos",
                new EventoRowMapper());

    }

    public Evento getEvento(Integer eID) {
        return jdbcTemplate.query(
                "SELECT * FROM Eventos Where eID =" + eID,
                new EventoRowMapper()).get(0);

    }

    public List<Evento> searchEventos(String ename, String edate) throws ParseException {
        Date date = new Date();
        if (edate != "") {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            date = dateFormat.parse(edate);
        }
        if (ename != "" && edate != "") {
            String sql = "SELECT * FROM Eventos WHERE DATE(edate) = ? AND ename LIKE ?";
            return jdbcTemplate.query(sql, new Object[] { date, "%" + ename + "%" }, new EventoRowMapper());
        } else if (ename != "") {
            String sql = "SELECT * FROM Eventos WHERE ename LIKE ?";
            return jdbcTemplate.query(sql, new Object[] { ename }, new EventoRowMapper());
        } else {
            String sql = "SELECT * FROM Eventos WHERE DATE(edate) = ?";
            return jdbcTemplate.query(sql, new Object[] { date }, new EventoRowMapper());
        }
    }

    public List<Evento> searchTypeEventos(String type) {
        LocalDate today = LocalDate.now();
        if ("A".equals(type)) {
            String sql = "SELECT * FROM Eventos WHERE edate < ?";
            return jdbcTemplate.query(sql, new Object[] { today }, new EventoRowMapper());
        } else if ("P".equals(type)) {
            String sql = "SELECT * FROM Eventos WHERE DATE(edate) = ?";
            return jdbcTemplate.query(sql, new Object[] { today }, new EventoRowMapper());
        } else {
            String sql = "SELECT * FROM Eventos WHERE edate > ?";
            return jdbcTemplate.query(sql, new Object[] { today }, new EventoRowMapper());
        }
    }
    
    public boolean EventoDate(Integer Eventoid) {
        LocalDate today = LocalDate.now();
        LocalDate EventoDate = jdbcTemplate.queryForObject("SELECT edate FROM Eventos WHERE eID = ?", new Object[]{Eventoid}, LocalDate.class);

        return EventoDate != null && EventoDate.isAfter(today);
    }

    public void saveEvento(final Evento E) {
        String sql = "INSERT INTO Eventos(ename, edate, eprice, edescription)  VALUES ('"
                + E.getEname() + "','"
                + E.getEdate() + "','"
                + E.getEprice() + "','"
                + E.getEdescription() + "')";
        jdbcTemplate.execute(sql);
        System.out.println("EventoDAO salvo! \n" + sql + "\n");
    }


    public String EventoName(Integer Eventoid) {
        
        return jdbcTemplate.queryForObject("SELECT ename FROM Eventos WHERE eID = ?", new Object[]{Eventoid}, String.class);
    }
    public float EventoGetPrice(Integer Eventoid) {
        return jdbcTemplate.queryForObject("SELECT eprice FROM Eventos WHERE eID = ?", new Object[]{Eventoid}, Float.class);
    }
}