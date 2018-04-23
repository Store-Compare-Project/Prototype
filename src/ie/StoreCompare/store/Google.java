package ie.StoreCompare.store;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * This was the initial test of Jsoup.
 * 
 * This class returns user entered query and number of queries.<br>
 * The query is a google search result.<br>
 * 
 * @author Cian Gannon
 * @author Danielis Joniškis
 * @author Eddie Eldridge
 */
public class Google {

	/**
	 * Takes in two variables which are used to get a google search result
	 * 
	 * @param amount
	 *            The amount of search results to display
	 * @param gameName
	 *            The search result to search
	 * @throws IOException
	 *             Throws input/output errors
	 */
	public static void main(int amount, String gameName) throws IOException {

		// String to be used to parse with JSpoup
		String url = "https://www.google.ie/search?q=" + gameName + "&num=" + amount;
		
		// Output telling the user that the website is being requested
		System.out.println("Sending request..." + "\"" + url + "\"");
				
		// Get all HTML of the page
		Document doc = Jsoup.connect(url).followRedirects(false).userAgent("Mozilla").timeout(60000).get();

		// Add all html under div with class 'g' to the elements els
		Elements els = doc.select("div.g");

		// Loop threw 'els' element by element and that the instance to 'el'
		for (Element el : els) {
			// If header of the google result is not blank
			if (el.getElementsByTag("h3").text() != " ") {
				// Print out the title
				System.out.println("Title : " + el.getElementsByTag("h3").text());
				// Print out the site url
				System.out.println("Site : " + el.getElementsByTag("cite").text());
				// Print out the site information
				System.out.println("Abstract : " + el.getElementsByTag("span").text() + "\n");
			}
		}
	}
}