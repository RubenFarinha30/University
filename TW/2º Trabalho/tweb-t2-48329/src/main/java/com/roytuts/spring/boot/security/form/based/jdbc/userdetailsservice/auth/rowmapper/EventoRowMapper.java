package com.roytuts.spring.boot.security.form.based.jdbc.userdetailsservice.auth.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.roytuts.spring.boot.security.form.based.jdbc.userdetailsservice.auth.model.Evento;
import java.sql.Date;


public class EventoRowMapper implements RowMapper<Evento> {

	@Override
	public Evento mapRow(ResultSet rs, int rowNum) throws SQLException {
		int eID = rs.getInt("eID");
        String ename = rs.getString("ename"); 
        Date edate = rs.getDate("edate");
		float eprice = rs.getFloat("eprice");
		String edescription = rs.getString("edescription");    
        return new Evento(eID, ename, edate, eprice, edescription);
	}

}