/**
 * 
 */
package fr.adaming.services.elexpression;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author INTI-0332
 *
 */
@Component("customerElBean2")
public class CustomerEL {
	
	@Value("#{itemElBean2}")
	private ItemEL itemEl;
	
	@Value("#{itemElBean2.name}")
	private String itemName;

	public ItemEL getItemEl() {
		return itemEl;
	}

	public void setItemEl(ItemEL itemEl) {
		this.itemEl = itemEl;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@Override
	public String toString() {
		return "CustomerEL [itemEl=" + itemEl + ", itemName=" + itemName + "]";
	}
	

}
