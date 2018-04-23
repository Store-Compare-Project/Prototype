package ie.StoreCompare.storage;

/**
 * Object that hold all variables for the store threads
 * 
 * @author Cian Gannon
 * @author Danielis Joniškis
 * @author Eddie Eldridge
 */
public class Items {

	// Variables used
	private String name;
	private double price;

	// Set instance
	public Items(String name, double price) {
		super();
		this.name = name;
		this.price = price;
	}

	// Return name instance
	public String getName() {
		return name;
	}

	// Set name instance
	public void setName(String name) {
		this.name = name;
	}

	// Get price instance
	public double getPrice() {
		return price;
	}

	// Set price instance
	public void setPrice(double price) {
		this.price = price;
	}

	// Return instance data
	@Override
	public String toString() {
		return "\nName:  " + name + "\nPrice: €" + price + "\n";
	}
}