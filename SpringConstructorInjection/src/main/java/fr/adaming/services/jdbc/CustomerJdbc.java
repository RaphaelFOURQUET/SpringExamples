package fr.adaming.services.jdbc;

public class CustomerJdbc {

	private int custId;
	
	private String name;
	
	private int age;

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "CustomerJdbc [custId=" + custId + ", name=" + name + ", age=" + age + "]";
	}
	
	public CustomerJdbc() {
		
	}

	public CustomerJdbc(int custId, String name, int age) {
		super();
		this.custId = custId;
		this.name = name;
		this.age = age;
	}
	
	
		
}
