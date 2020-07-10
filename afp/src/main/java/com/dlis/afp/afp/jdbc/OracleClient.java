package com.dlis.afp.afp.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


@Service
public class OracleClient {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean exeSql(String sql){
        try{
            jdbcTemplate.execute(sql);
            return true;
        }catch (DataAccessException dataAccessException){
            System.err.println(dataAccessException.getMessage());
            return false;
        }
    }
}
