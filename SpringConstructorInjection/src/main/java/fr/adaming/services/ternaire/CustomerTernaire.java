package fr.adaming.services.ternaire;

public class CustomerTernaire {

	private boolean warning;
	
	public boolean isWarning() {
		return warning;
	}
	
	public void setWarning(boolean warning) {
		this.warning = warning;
	}
	
	@Override
	public String toString() {
		return "Customer [warning=" + warning + "]";
	}

}
