/**
 * 
 */
package fr.adaming.services.toxml;

/**
 * @author INTI-0332
 *
 */
public class CustomerToXml {
	
	private String name;
	
	private int age;
	
	private boolean flag;
	
	private String address;

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

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "CustomerToXml [name=" + name + ", age=" + age + ", flag=" + flag + ", address=" + address + "]";
	}
	
	

}
