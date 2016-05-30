package fr.adaming.main;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fr.adaming.entity.Customer;
import fr.adaming.entity.Personne;
import fr.adaming.services.CustomerCollection;
import fr.adaming.services.CustomerHeritage;
import fr.adaming.services.CustomerLifeCycle;
import fr.adaming.services.CustomerService;
import fr.adaming.services.elexpression.CustomerEL;
import fr.adaming.services.elexpression.CustomerMethodinvocation;
import fr.adaming.services.ternaire.CustomerTernaire;

@SpringBootApplication
public class SpringConstructorInjectionApplication {

	public static void main(String[] args) {
		//SpringApplication.run(SpringConstructorInjectionApplication.class, args);
		
		//Recuperation des Beans
		ClassPathXmlApplicationContext context =
				new ClassPathXmlApplicationContext("Spring-Customer.xml");
		
//		<!-- Injection par constructeur 3 parametres -->
		Personne p = (Personne)context.getBean("PersonneBean");
		System.out.println(p);
		
//		<!-- Injection par constructeur sans parametres et utilisation des setteurs -->
		Customer c = (Customer) context.getBean("CustomerBean");
		System.out.println(c);
		
		
		CustomerService custA = (CustomerService)context.getBean("customerService");
		custA.setMessage("Message by custA");
		System.out.println("Message : " + custA.getMessage());
		
		//retrieve it again
		CustomerService custB = (CustomerService)context.getBean("customerService");
		System.out.println("Message : " + custB.getMessage());
		
		
		CustomerCollection custCollection = (CustomerCollection) context.getBean("CustomerCollectionBean");
		System.out.println(custCollection);
		
//		<!-- Bean par héritage -->
		CustomerHeritage custHeritage = (CustomerHeritage)context.getBean("CustomerHeritageBean");
		System.out.println(custHeritage);
//		<!-- Override country -->
		CustomerHeritage custHeritage2 = (CustomerHeritage)context.getBean("CustomerHeritageBean2");
		System.out.println(custHeritage2);
		
//		<!-- Cycle de vie Bean par init-method and destroy-method dans le xml : autre solution par Spring @PostConstruct And @PreDestroy (mais dependances Spring) -->
		CustomerLifeCycle customerLifeCycle = (CustomerLifeCycle) context.getBean("CustomerLifeCycle");
		System.out.println(customerLifeCycle);
		
//		<!-- Spring EL Expression : On peut egalement passer par annotations -->
		CustomerEL customerEL = (CustomerEL) context.getBean("customerElBean");
		System.out.println(customerEL);
		
//		<!-- Spring EL Expression  par annotations --> prb : Une seule annotation @Component possible
		CustomerEL customerEL2 = (CustomerEL) context.getBean("customerElBean2");
		System.out.println(customerEL2);
		
//		Spring EL method invocation
		CustomerMethodinvocation customerMethodinvocation = (CustomerMethodinvocation) context.getBean("customerMethodInvocationBean");
		System.out.println(customerMethodinvocation);
		
//		<!-- Operateur ternaire -->
		CustomerTernaire customerTernaire = (CustomerTernaire)context.getBean("customerTernaireBean");
		System.out.println(customerTernaire);
		
		context.close();
	}
}