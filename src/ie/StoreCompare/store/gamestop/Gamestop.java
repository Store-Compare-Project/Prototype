package ie.StoreCompare.store.gamestop;

import java.io.IOException;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ie.StoreCompare.storage.Items;

/**
 * This class was responsible for parsing a GameStop query using JSoup.<br>
 * Unfortunately GameStop website updated and stopped JSoup from parsing the
 * website during development
 * 
 * @author Cian Gannon
 * @author Danielis Joniškis
 * @author Eddie Eldridge
 */
public class Gamestop {

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
		String name;
		String gameType;
		double price;
		double postage = 1.50;
		String url = "https://www.gamestop.ie/SearchResult/QuickSearch?q=";

		// Replace all spaces in gameName with '+'
		gameName = gameName.replaceAll(" ", "+");

		// new url equals itself plus the the game name with '+' between words
		url = url + gameName;

		// Inform the user the site is being requested
		System.out.println("\nSending request..." + "\"" + url + "\"\n");

		// Get HTML from the url
		Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0").followRedirects(true).timeout(60000).get();

		// Get all HTML elements under the div tag with class singleProduct
		Elements els = doc.select("div.singleProduct");

		// Print out entire HTML document
		System.out.println(doc);

		// Loop threw 'els' element by element and that the instance to 'el'
		for (Element el : els) {
			
			// Get game name
			name = el.getElementsByTag("h3").text();

			// Print out entire element
			System.out.println(el);

			// price =
			// Double.parseDouble((el.select("div.priceTxt").get(0).text()).replaceAll("[^0-9.]",
			// ""));
			// gameType =
			// ((el.getElementsByTag("p").text().substring(el.getElementsByTag("p").text().indexOf("/")+1,
			// el.getElementsByTag("p").text().length())).replaceAll("Games",
			// "")).replaceAll(" ", "");

			// System.out.println(name + " " + price + " " + gameType);

			// itemList.add(new Items((name + "(" + gameType + ")"), (price +
			// postage)));
		}
	}
}