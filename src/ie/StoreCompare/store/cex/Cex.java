package ie.StoreCompare.store.cex;

// Imports used
import ie.StoreCompare.storage.Items;

import java.io.IOException;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * This class is responsible for parsing a CEX html provided by a url
 * 
 * @author Cian Gannon
 * @author Danielis Joni�kis
 * @author Eddie Eldridge
 */
public class Cex {

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
		String url = "https://ie.webuy.com/search?stext=";

		// Replace all spaces of the game name with '%20' to be used in the url
		gameName = gameName.replaceAll(" ", "%20");

		// new url equals itself plus the the game name with '%20' between words
		url = url + gameName;

		// Inform the user the site is being requested
		System.out.println("\nSending request..." + "\"" + url + "\"\n");

		// Get HTML from the url
		Document doc = Jsoup.connect(url)
				.userAgent(
						"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.120 Safari/535.2")
				.followRedirects(true).referrer("http://www.google.ie").timeout(60000).get();

		// Get all HTML elements under the div tag with class searchRecord
		Elements els = doc.select("div.searchRecord");

		// Loop threw 'els' element by element and that the instance to 'el'
		for (Element el : els) {
			// Get game name
			name = (el.getElementsByTag("h1").text()).replaceAll("\\(.*?\\) ?", "");
			// Get price
			price = Double.parseDouble((el.select("div.priceTxt").get(0).text()).replaceAll("[^0-9.]", ""));
			// get game type (ps3) (xbox360), etc...
			gameType = ((el.getElementsByTag("p").text().substring(el.getElementsByTag("p").text().indexOf("/") + 1,
					el.getElementsByTag("p").text().length())).replaceAll("Games", "")).replaceAll(" ", "");

			// Add all data to the itemList array
			itemList.add(new Items((name + "(" + gameType + ")"), (price + postage)));
		}
	}
}