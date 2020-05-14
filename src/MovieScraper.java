import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MovieScraper {
	static String baseUrl = "https://www.boxofficemojo.com/year/world/";
	static String titleClass = "a-text-left mojo-field-type-release_group";
	static String moneyClass = "a-text-right mojo-field-type-money";
	static String percentClass = "a-text-right mojo-field-type-percent";
	
	public static ArrayList<ArrayList<String>> ScrapeByYear(int year) throws IOException {
		if (year < 1977 || year > BoxOfficeMain.MAXYEAR) {
			System.out.println("Nur Daten zwischen den Jahren 1977 und "+BoxOfficeMain.MAXYEAR+" existent! ("+year+")");
			return null;
		}
		String url = baseUrl+year+"/"; //die Basisseite wird mit der Jahreszahl verlängert
		Document page = Jsoup.connect(url).userAgent("Jsoup Scraper").get(); //mit diesem Befehl wird für jsoup die Seite abgespeichert
		
		//die Elements Datentypen speichern ALLE HTML-Daten, welche in der entprechenden HTML-Klasse sind (oben deklariert)
		Elements titleElements = page.getElementsByClass(titleClass);
		Elements moneyElements = page.getElementsByClass(moneyClass);
		Elements percentElements = page.getElementsByClass(percentClass);
		
		//alle Elemente der Klasse titleClass sind Filmtitel
		ArrayList<String> movieTitle = addElementFromClass(titleElements);
		
		//jedes dritte Element der Klasse moneyClass (beginnend mit Index 0) ist ein weltweiter Gewinn
		ArrayList<String> moneyTotal = addElementFromClass(moneyElements, 0, 3);
		
		//jedes dritte Element der Klasse moneyClass (beginnend mit Index 1) ist ein inländischer Gewinn
		ArrayList<String> moneyDomestic = addElementFromClass(moneyElements, 1, 3);
		
		//jedes dritte Element der Klasse moneyClass (beginnend mit Index 2) ist ein ausländischer Gewinn
		ArrayList<String> moneyForeign = addElementFromClass(moneyElements, 2, 3);
		
		//jedes zweite Element der Klasse percentClass (beginnend mit Index 0) ist ein inländischer Gewinn in Prozent
		ArrayList<String> percentDomestic = addElementFromClass(percentElements, 0, 2);
		
		//jedes zweite Element der Klasse percentClass (beginnend mit Index 1) ist ein ausländischer Gewinn in Prozent
		ArrayList<String> percentForeign = addElementFromClass(percentElements, 1, 2);
		
		ArrayList<ArrayList<String>> scrapedData = new ArrayList<ArrayList<String>>();
		scrapedData.add(movieTitle);
		scrapedData.add(moneyTotal);
		scrapedData.add(moneyDomestic);
		scrapedData.add(percentDomestic);
		scrapedData.add(moneyForeign);
		scrapedData.add(percentForeign);
		return scrapedData;
	}
	//hopsToNextElement = der Abstand zwischen 2 gesuchten Elementen
	//Bsp: Jedes 3. Element ist gesucht: hopsToNextElement = 3
	private static ArrayList<String> addElementFromClass (Elements e, int firstElement, int hopsToNextElement) {
		ArrayList<String> elemArrayList = new ArrayList<>();
		for (int i = firstElement; i < e.size(); i+=hopsToNextElement) {
			Element currentElement = e.get(i);
			elemArrayList.add(currentElement.text());
		}
		return elemArrayList;
	}
	//jedes Element einer Klasse wird hinzugefügt
	private static ArrayList<String> addElementFromClass (Elements e) {
		return addElementFromClass(e, 0, 1);
	}
}
