package ie.StoreCompare.store.musicmagpie;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ie.StoreCompare.storage.Items;

/**
 * This class is responsible for parsing the MusicMagPie ebay store html page provided by a url
 * 
 * @author Cian Gannon
 * @author Danielis Joniškis
 * @author Eddie Eldridge
 */
public class MusicMagPieEbayStore {

	/**
	 * Method is passed gameName, object list and itemList from the main menu
	 * 
	 * @param gameName
	 *            Name of the game to be added to the url so the HTML can be
	 *            taken from the custom result
	 * @param itemList
	 *            List of the object Items. This object hold all data from each
	 *            thread and website
	 * @throws IOException
	 *             Throws input output errors
	 */
	public static void main(String gameName, List<Items> itemList) throws IOException {

		// Variables used
		String name = null;
		double price = 0;
		double postage = 0;
		String url = "http://www.ebay.ie/sch/i.html?LH_BIN=1&_nkw=";

		// Replace all spaces in gameName with '+'
		gameName = gameName.replaceAll(" ", "+");

		// New url equals itself plus the the game name with '+' between words
		// and then the store ID at the end
		url = url + gameName + "&_ssn=musicmagpie";

		// Inform the user the site is being requested
		System.out.println("\nSending request..." + "\"" + url + "\"");
		
		// Get HTML from the url
		Document doc = Jsoup.connect(url)
				.userAgent(
						"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.120 Safari/535.2")
				.timeout(60000).get();
		
		// Get all HTML elements under the li tag with class sresult and class lvresult undet that
		Elements els = doc.select("li.sresult.lvresult");

		// Loop threw 'els' element by element and that the instance to 'el'
		for (Element el : els) {
			// Get game name
			name = (el.getElementsByTag("h3").text()).replaceAll("VideoGames", "");
			// Get game price
			price = Double.parseDouble((el.getElementsByClass("lvprice prc").text()).replaceAll("[^0-9.]", ""));
			// Get game postage
			postage = Double.parseDouble((el.getElementsByClass("fee").text()).replaceAll("[^0-9.]", ""));
			
			// Add all data to the itemList array
			itemList.add(new Items(name, (price + postage)));
		}
	}
}