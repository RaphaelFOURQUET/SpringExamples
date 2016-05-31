package fr.adaming.services.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class CustomerRowMapper implements RowMapper<Object> {
	
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		CustomerJdbc customer = new CustomerJdbc();
		customer.setCustId(rs.getInt("CUST_ID"));
		customer.setName(rs.getString("NAME"));
		customer.setAge(rs.getInt("AGE"));
		return customer;
	}
	
}