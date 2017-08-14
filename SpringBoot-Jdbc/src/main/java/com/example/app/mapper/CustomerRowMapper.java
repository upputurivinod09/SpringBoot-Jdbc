package com.example.app.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.example.app.entity.Customer;
@Component
public class CustomerRowMapper implements RowMapper<Customer>{

	@Override
	public Customer mapRow(ResultSet rs, int i) throws SQLException {
		Customer customer = new Customer();
		customer.setId(rs.getString("id"));
		customer.setName(rs.getString("name"));
		return customer; 
	}
}
