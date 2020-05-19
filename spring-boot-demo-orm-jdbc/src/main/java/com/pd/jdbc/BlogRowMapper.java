package com.pd.jdbc;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @description: demonstration
 * @author: zhaozhengkang
 * @date: 2020-05-18 12:53
 */
public class BlogRowMapper implements RowMapper<Blog> {
    @Override
    public Blog mapRow(ResultSet rs, int rowNum) throws SQLException {
        Blog b = new Blog();
        b.setBid(rs.getInt("bid"));
        b.setName(rs.getString("name"));
        b.setAuthorId(rs.getInt("author_id"));
        return b;
    }
}
