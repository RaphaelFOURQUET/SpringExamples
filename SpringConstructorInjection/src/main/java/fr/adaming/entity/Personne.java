/**
 * 
 */
package fr.adaming.entity;

/**
 * @author INTI-0332
 *
 */
public class Personne {

	private String name;
	private String address;
	private int age;
	
	public Personne(String name, int age, String address) {
		this.name = name;
		this.age = age;
		this.address = address;
	}
	
	public Personne() {
		
	}

	public Personne(String name, String address, int age) {
		this.name = name;
		this.address = address;
		this.age = age;
	}

	//getter and setter methods
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Personne [name=" + name + ", address=" + address + ", age=" + age + "]";
	}
	
	
//	public String toString(){
//		return " name : " +name + "\n address : "
//				+ address + "\n age : " + age;
//	}
	
	

}
