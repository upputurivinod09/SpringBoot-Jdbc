package com.example.app.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.entity.Customer;
import com.example.app.mapper.CustomerRowMapper;
import com.example.app.sql.annotation.Sql;
import com.example.app.sql.annotation.SqlPath;

@Repository
@Transactional
@SqlPath(path="classpath:/sql")
public class CustomerRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Sql
	private String getCustomer;
	
	
	public List<Customer> getCustomers() {
		List<Customer> customers = namedParameterJdbcTemplate.query(getCustomer, new CustomerRowMapper());
		return customers;
	}
}
