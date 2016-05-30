/**
 * 
 */
package fr.adaming.services.autowiring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import fr.adaming.entity.Personne;

/**
 * @author INTI-0332
 *
 */
public class CustomerAutoWiringByAnnotation {
	
//	Now, you can autowired bean via @Autowired, and it can be applied on setter method, constructor or a field.

	@Autowired(required=false)
	@Qualifier("PersonneAWBean")
	private Personne personne;
	private int type;
	private String action;


//	@Autowired
	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Personne getPersonne() {
		return personne;
	}

	@Override
	public String toString() {
		return "CustomerAutoWiringByAnnotation [personne=" + personne + ", type=" + type + ", action=" + action + "]";
	}

//	@Autowired
//	public CustomerAutoWiringByAnnotation(Personne personne) {
//		this.personne = person;
//	}
	
	

}
