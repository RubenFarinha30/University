package com.roytuts.spring.boot.security.form.based.jdbc.userdetailsservice.auth.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.roytuts.spring.boot.security.form.based.jdbc.userdetailsservice.auth.model.User;
import com.roytuts.spring.boot.security.form.based.jdbc.userdetailsservice.auth.rowmapper.UserRowMapper;
import java.util.List;

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User getUser(final String username) {
        try {
            return jdbcTemplate.queryForObject(
                    "select u.username username, u.password password, email, ur.user_role user_role from utilizador u, cargo ur where u.username = ? and u.username = ur.username",
                    new String[]{username}, new UserRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    public void saveUser(final User u) {
        jdbcTemplate.execute("INSERT INTO utilizador VALUES ('"
                + u.getUsername() + "','"
                + u.getPassword() + "','"
                + u.getEmail() + "')");
        jdbcTemplate.execute("INSERT INTO cargo VALUES ('" + u.getUsername() + "','" + u.getRole() + "')");
        System.out.println("UserDAO - saved\n" + "INSERT INTO utilizador VALUES ('"
                + u.getUsername() + "','"
                + u.getPassword() + "','"
                + u.getEmail() + "')" + "\n");
    }

    public List<String> getUsernameList() {
        return jdbcTemplate.queryForList("select my_username FROM user", String.class);
    }

}
