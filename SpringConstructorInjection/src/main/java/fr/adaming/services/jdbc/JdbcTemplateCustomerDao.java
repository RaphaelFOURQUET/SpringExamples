package fr.adaming.services.jdbc;

import javax.sql.DataSource;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTemplateCustomerDao implements CustomerJdbcDao {
	
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}



	@Override
	public void insert(CustomerJdbc customer) {
		String sql = "INSERT INTO CUSTOMER " +
				"(CUST_ID, NAME, AGE) VALUES (?, ?, ?)";
				jdbcTemplate = new JdbcTemplate(dataSource);
				jdbcTemplate.update(sql, new Object[] { customer.getCustId(),
				customer.getName(),customer.getAge()
				});
		
	}

	@Override
	//query single row with RowMapper
	public CustomerJdbc findByCustomerId(int custId) {
		String sql = "SELECT * FROM CUSTOMER WHERE CUST_ID = ?";
		jdbcTemplate = new JdbcTemplate(dataSource);
		CustomerJdbc customer;
		try {	//Gerer le cas ou on ne recoit aucun resultat comme null
			customer = (CustomerJdbc)jdbcTemplate.queryForObject(
				sql, new Object[] { custId }, new CustomerRowMapper());
		} catch (IncorrectResultSizeDataAccessException e) {
			return null;
		}
		return customer;
	}

}
