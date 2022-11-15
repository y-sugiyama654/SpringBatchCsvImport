package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeJdbcRepository {

    @Autowired
    private JdbcTemplate jdbc;

    private static final String EXISTS_SQL = "select exists (select * from employee where id = ?)";

    /**
     * SQLの実行
     */
    public boolean exits(Integer id) {
        return jdbc.queryForObject(EXISTS_SQL, Boolean.class, id);
    }
}
