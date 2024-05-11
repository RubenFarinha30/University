package com.roytuts.spring.boot.security.form.based.jdbc.userdetailsservice.auth.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.roytuts.spring.boot.security.form.based.jdbc.userdetailsservice.auth.model.Atleta;
import java.sql.Timestamp;

public class AtletaRowMapper implements RowMapper<Atleta> {

    @Override
    public Atleta mapRow(ResultSet rs, int rowNum) throws SQLException {
        int dorsal = rs.getInt("dorsal");
        int idEvento = rs.getInt("idEvento");
        String username = rs.getString("username");
        String nomeAtleta = rs.getString("nomeAtleta");
        int nif = rs.getInt("nif");
        String genero = rs.getString("genero");
        String escalao = rs.getString("escalao");
        boolean paystatus = rs.getBoolean("paystatus");
        Timestamp start = rs.getTimestamp("start");
        Timestamp p1 = rs.getTimestamp("p1");
        Timestamp p2 = rs.getTimestamp("p2");
        Timestamp p3 = rs.getTimestamp("p3");
        Timestamp finish = rs.getTimestamp("finish");
        long ttime = rs.getLong("ttime");
        int entity = rs.getInt("entity");
        Integer ref = rs.getInt("ref");

        return new Atleta(dorsal, idEvento, username, nomeAtleta, nif, genero, escalao, paystatus, start, p1, p2, p3, finish, ttime, entity, ref);
    }
}
