package fr.adaming.main;

import java.io.IOException;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;

import fr.adaming.entity.Customer;
import fr.adaming.entity.Personne;
import fr.adaming.services.CustomerHeritage;
import fr.adaming.services.CustomerLifeCycle;
import fr.adaming.services.CustomerService;
import fr.adaming.services.autoscanning.CustomerAutoScanning;
import fr.adaming.services.autowiring.CustomerAutoWiringByAnnotation;
import fr.adaming.services.autowiring.CustomerAutoWiringByName;
import fr.adaming.services.collection.CustomerCollection;
import fr.adaming.services.collection.CustomerRecupFromMapList;
import fr.adaming.services.elexpression.CustomerEL;
import fr.adaming.services.elexpression.CustomerMethodinvocation;
import fr.adaming.services.jdbc.CustomerJdbc;
import fr.adaming.services.jdbc.JdbcCustomerDao;
import fr.adaming.services.jdbc.JdbcCustomerDaoSupport;
import fr.adaming.services.jdbc.JdbcTemplateCustomerDao;
import fr.adaming.services.regularexpression.CustomerRegularExpression;
import fr.adaming.services.ternaire.CustomerTernaire;
import fr.adaming.services.toxml.CustomerToXml;
import fr.adaming.services.toxml.XMLConverter;

@SpringBootApplication
public class SpringConstructorInjectionApplication {
	
	//private static final Logger logger = Logger.getLogger(SpringConstructorInjectionApplication.class);
	private static final String XML_FILE_NAME = "src/main/resources/customerToXml.xml";

	public static void main(String[] args) throws IOException {
		//SpringApplication.run(SpringConstructorInjectionApplication.class, args);

		//logger.setLevel(Level.ERROR);
		
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

		//collections
		CustomerCollection custCollection = (CustomerCollection) context.getBean("CustomerCollectionBean");
		System.out.println(custCollection);
		//recup from collection Map List
		CustomerRecupFromMapList customerRecupFromMapList = (CustomerRecupFromMapList) context.getBean("customerRecupFromMapListBean");
		System.out.println(customerRecupFromMapList);

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

		//Expression Reguliere
		CustomerRegularExpression customerRegularExpression = (CustomerRegularExpression)context.getBean("customerRegExBean");
		System.out.println(customerRegularExpression);

		//Auto scanning : no bean in xml nor @Component("name")
		CustomerAutoScanning customerAutoScanning = (CustomerAutoScanning)context.getBean("customerAutoScanning");
		System.out.println(customerAutoScanning);

		//		<!-- Auto Wiring By name-->
		CustomerAutoWiringByName customerAutoWiringByName = (CustomerAutoWiringByName)context.getBean("customerAutoWiringByNameBean");
		System.out.println(customerAutoWiringByName);

		//		<!-- AutoWiring by annotation -->
		CustomerAutoWiringByAnnotation customerAutoWiringByAnnotation = (CustomerAutoWiringByAnnotation)context.getBean("customerAutoWiringByAnnotationBean");
		System.out.println(customerAutoWiringByAnnotation);

		//		<!-- XML conversion via Spring oxm  : genere customerToXml.xml et utilise mapping.xml -->
		XMLConverter converter = (XMLConverter)context.getBean("XMLConverter");
		
		CustomerToXml customerToXml = new CustomerToXml();
		customerToXml.setName("Raf");
		customerToXml.setAge(26);
		customerToXml.setFlag(true);
		customerToXml.setAddress("This is address");
		
		System.out.println("Convert Object to XML!");
		//from object to XML file
		converter.convertFromObjectToXML(customerToXml, XML_FILE_NAME);
		System.out.println("Done \n");
		
		
		System.out.println("Convert XML back to Object!");
		//from XML to object
		CustomerToXml customerToXml2 = (CustomerToXml)converter.convertFromXMLToObject(XML_FILE_NAME);
		System.out.println(customerToXml2);
		System.out.println("Done");
		
		
//		<!-- JDBC + Spring -->
		//Requete SQL de creation table
		String creationTableSql = "CREATE TABLE `customer` (`CUST_ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,`NAME` VARCHAR(100) NOT NULL, "
				+ "`AGE` INT(10) UNSIGNED NOT NULL, "
				+ "PRIMARY KEY (`CUST_ID`))"
				+ " ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;";
		//envoyer requete
		JdbcTemplate jdbcTemplate = new JdbcTemplate((DataSource) context.getBean("dataSource"));
		try {
			jdbcTemplate.execute(creationTableSql);
		} catch(BadSqlGrammarException e) {	//Si customer existe deja
			//do nothing
		}
		
		JdbcCustomerDao JdbccustomerDAO = (JdbcCustomerDao) context.getBean("customerDaoBean");
		CustomerJdbc customerJdbc = new CustomerJdbc(1, "Raphz",26);
		CustomerJdbc customerJdbc1;
		if((customerJdbc1=JdbccustomerDAO.findByCustomerId(1)) == null) {	//On ne remet pas si déja présent en BD pour éviter un probleme d'unicite d id
			JdbccustomerDAO.insert(customerJdbc);
			customerJdbc1 = JdbccustomerDAO.findByCustomerId(1);
		}
		System.out.println(customerJdbc1);
		
		//JDBC+Spring par JdbcTemplate			JdbcTemplateCustomerDao
		JdbcTemplateCustomerDao jdbcTemplateCustomerDao = (JdbcTemplateCustomerDao) context.getBean("customerTemplateDaoBean");
		CustomerJdbc customerJdbcT = new CustomerJdbc(2, "Raphz90",26);
		CustomerJdbc customerJdbcT1;
		if((customerJdbcT1=jdbcTemplateCustomerDao.findByCustomerId(2)) == null) {	//On ne remet pas si déja présent en BD pour éviter un probleme d'unicite d id
			jdbcTemplateCustomerDao.insert(customerJdbcT);
			customerJdbcT1 = jdbcTemplateCustomerDao.findByCustomerId(2);
		}
		System.out.println(customerJdbcT1);
		
//		<!-- JDBC+Spring par JdbcDaoSupport -->					JdbcCustomerDaoSupport
		JdbcCustomerDaoSupport jdbcCustomerDaoSupport = (JdbcCustomerDaoSupport) context.getBean("customerDAOSupportBean");
		CustomerJdbc customerJdbcS = new CustomerJdbc(3, "Rafz",26);
		CustomerJdbc customerJdbcS1;
		if((customerJdbcS1=jdbcCustomerDaoSupport.findByCustomerId(3)) == null) {	//On ne remet pas si déja présent en BD pour éviter un probleme d'unicite d id
			jdbcCustomerDaoSupport.insert(customerJdbcS);
			customerJdbcS1 = jdbcCustomerDaoSupport.findByCustomerId(3);
		}
		System.out.println(customerJdbcS1);
		
		//jdbcCustomerDaoSupport.insert(new CustomerJdbc("R",20));	//Test id autoIncrémenté.

		context.close();
	}
}
