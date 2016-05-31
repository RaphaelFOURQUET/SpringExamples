package fr.adaming.services.jdbc;

public interface CustomerJdbcDao {
	
	public void insert(CustomerJdbc customer);
	
	public CustomerJdbc findByCustomerId(int custId);

}
