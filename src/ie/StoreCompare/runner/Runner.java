package ie.StoreCompare.runner;

import ie.StoreCompare.core.Menu;

/**
 * The runner responsible for kick starting the program.
 * 
 * @author Cian Gannon
 * @author Danielis Joniškis
 * @author Eddie Eldridge
 */
public class Runner {

	/**
	 * 
	 * This method calls the 'Menu.main' function which starts the user
	 * interface.
	 * 
	 * @param args
	 *            Default method argument
	 * @throws InterruptedException
	 *             Stops the threads from crashing the program
	 */
	public static void main(String[] args) throws InterruptedException {

		Menu.main(args);

	}
}
