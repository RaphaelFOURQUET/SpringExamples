package fr.adaming.entity;

public class Customer {

	private Personne personne;
	
	public Customer() {
		
	}

	public Customer(Personne personne) {
		this.personne = personne;
	}
	
	public void setPersonne(Personne personne) {
		this.personne = personne;
	}
	
	@Override
	public String toString() {
		return "Customer [personne=" + personne + "]";
	}

}
