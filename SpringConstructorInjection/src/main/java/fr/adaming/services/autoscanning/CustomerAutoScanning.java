package fr.adaming.services.autoscanning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerAutoScanning {
	@Autowired
	CustomerDAO customerDAO;
	
	@Override
	public String toString() {
		return "CustomerService [customerDAO=" + customerDAO + "]";
	}
	
}