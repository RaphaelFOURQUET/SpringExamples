package fr.adaming.services.elexpression;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("customerMethodInvocationBean")
public class CustomerMethodinvocation {
	
	@Value("#{'toto'.toUpperCase()}")
	private String name;
	
	@Value("#{priceBean.getSpecialPrice()}")
	private double amount;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Customer [name=" + name + ", amount=" + amount + "]";
	}
}
