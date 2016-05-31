package fr.adaming.services.jdbc;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class JdbcCustomerDaoSupport extends JdbcDaoSupport implements CustomerJdbcDao {

	@Override
	public void insert(CustomerJdbc customer) {
		String sql = "INSERT INTO CUSTOMER " +
				"(CUST_ID, NAME, AGE) VALUES (?, ?, ?)";
		
		this.getJdbcTemplate().update(sql, new Object[] { customer.getCustId(),
				customer.getName(),customer.getAge()
				});
		
	}

	@Override
	public CustomerJdbc findByCustomerId(int custId) {
		String sql = "SELECT * FROM CUSTOMER WHERE CUST_ID = ?";
		CustomerJdbc customer;
		try {	//Gerer le cas ou on ne recoit aucun resultat comme null
			customer = (CustomerJdbc)this.getJdbcTemplate().queryForObject(
				sql, new Object[] { custId }, new CustomerRowMapper());
		} catch (IncorrectResultSizeDataAccessException e) {
			return null;
		}
		return customer;
	}

}
