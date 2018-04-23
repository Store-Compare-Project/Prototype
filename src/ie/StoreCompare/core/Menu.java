package ie.StoreCompare.core;

// Imports used
import ie.StoreCompare.storage.Items;
import ie.StoreCompare.store.cex.Cex;
import ie.StoreCompare.store.gamestop.Gamestop;
import ie.StoreCompare.store.musicmagpie.MusicMagPieEbayStore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This is a console menu interface which allows the user to interact with the 3
 * websites
 * 
 * MusicMagPie - This is an ebay store which sells used items (Mostly games and
 * movies).<br> CEX - A used electronics store which sells.<br>
 * Gamestop - A video game
 * retailer which sells new and used games.<br>
 * 
 * Using these three game stores we can compare the price of similar items to
 * find the best deal.
 * 
 * @author Cian Gannon
 * @author Danielis Joniškis
 * @author Eddie Eldridge
 */
public class Menu {

	/**
	 * 
	 * @param args
	 *            Default method argument
	 * @throws InterruptedException
	 *             Stops thread error
	 */
	public static void main(String[] args) throws InterruptedException {

		// Threads
		CexThread CexThread;
		MusicMagPieThread MusicMagPieThread;
		GamestopThread GamestopThread;

		// User Input
		Scanner reader = new Scanner(System.in);

		// Items Object list array
		List<Items> itemList = new ArrayList<Items>();

		// Variables
		String gameName = null;
		int menu = -1;

		// Loop while user input does not equal 0
		while (menu != 0) {

			// Menu Prompt
			System.out.println("=========== Menu ===========");
			System.out.println(") 1. Search MusicMagPie");
			System.out.println(") 2. Search Cex");
			System.out.println(") 3. Search GameStop");
			System.out.println(") 4. Search MusicMagPie/Cex");
			System.out.println(") 0. Exit");
			menu = reader.nextInt();
			reader.nextLine();

			// If user input is between 0 and 5
			// Else if the input is 0 then the program will end
			// If the input is not in this range the user will be told to alter
			// their input
			if (menu >= 1 && menu <= 4) {
				System.out.println("> Game to Search: ");
				gameName = reader.nextLine();

				if (menu == 1) {
					// Thread setup
					MusicMagPieThread = new MusicMagPieThread(gameName, itemList);
					MusicMagPieThread.start();

					// Exit menu code
					menu = 0;

					// Wait until the thread has stopped
					MusicMagPieThread.join();
				} else if (menu == 2) {
					// Thread setup
					CexThread = new CexThread(gameName, itemList);
					CexThread.start();

					// Exit menu code
					menu = 0;

					// Wait until the thread has stopped
					CexThread.join();
				} else if (menu == 3) {
					// Thread setup
					GamestopThread = new GamestopThread(gameName, itemList);
					GamestopThread.start();

					// Exit menu code
					menu = 0;

					// Wait until the thread has stopped
					GamestopThread.join();
				} else if (menu == 4) {
					// Threads setup
					MusicMagPieThread = new MusicMagPieThread(gameName, itemList);
					CexThread = new CexThread(gameName, itemList);
					MusicMagPieThread.start();
					CexThread.start();

					// Exit menu code
					menu = 0;

					// Wait until the threads has stopped
					MusicMagPieThread.join();
					CexThread.join();
				}
			} else if (menu == 0) {
				System.out.println("Goodbye!");
			} else {
				System.out.println("\nPlease select a valid option");
			}
		}

		// Basic output of object list array
		System.out.println(itemList);

		// Close input reader
		reader.close();
	}
}

// CexThread thread settings
class CexThread extends Thread {
	String gameName;
	List<Items> itemList;

	// Variables passed from thread starter
	CexThread(String g, List<Items> i) {
		gameName = g;
		itemList = i;
	}

	public void run() {

		try {
			// Pass game name and array list to Cex
			Cex.main(gameName, itemList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

// MusicMagPie thread settings
class MusicMagPieThread extends Thread {
	String gameName;
	List<Items> itemList;

	// Variables passed from thread starter
	MusicMagPieThread(String g, List<Items> i) {
		gameName = g;
		itemList = i;
	}

	public void run() {

		try {
			// Pass game name and array list to MusicMagPieEbayStore
			MusicMagPieEbayStore.main(gameName, itemList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

// Gamestop thread settings
class GamestopThread extends Thread {
	String gameName;
	List<Items> itemList;

	// Variables passed from thread starter
	GamestopThread(String g, List<Items> i) {
		gameName = g;
		itemList = i;
	}

	public void run() {
		try {
			// Pass game name and array list to Gamestop
			Gamestop.main(gameName, itemList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}