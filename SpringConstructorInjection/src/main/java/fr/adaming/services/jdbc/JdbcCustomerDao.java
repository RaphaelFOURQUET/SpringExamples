package fr.adaming.services.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class JdbcCustomerDao implements CustomerJdbcDao {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void insert(CustomerJdbc customer) {
		String sql = "INSERT INTO CUSTOMER " +
				"(CUST_ID, NAME, AGE) VALUES (?, ?, ?)";

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, customer.getCustId());
			ps.setString(2, customer.getName());
			ps.setInt(3, customer.getAge());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}

	}

	@Override
	public CustomerJdbc findByCustomerId(int custId) {
		String sql = "SELECT * FROM CUSTOMER WHERE CUST_ID = ?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, custId);
			CustomerJdbc customer = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				customer = new CustomerJdbc(
						rs.getInt("CUST_ID"),
						rs.getString("NAME"),
						rs.getInt("Age")
						);
			}
			rs.close();
			ps.close();
			return customer;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}

}
