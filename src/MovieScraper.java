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
	
	public static void ScrapeByYear(int year) throws IOException {
		String url = baseUrl+year+"/"; //die Basisseite wird mit der Jahreszahl verlängert
		Document page = Jsoup.connect(url).userAgent("Jsoup Scraper").get(); //mit diesem Befehl wird für jsoup die Seite abgespeichert
		
		//die Elements Datentypen speichern ALLE HTML-Daten, welche in der entprechenden HTML-Klasse sind (oben deklariert)
		Elements titleElements = page.getElementsByClass(titleClass);
		Elements moneyElements = page.getElementsByClass(moneyClass);
		Elements percentElements = page.getElementsByClass(percentClass);
		
		//alle Daten der Klasse titleClass sind Filmtitel
		ArrayList<String> movieTitle = new ArrayList<>();
		for (Element e:titleElements) {
			movieTitle.add(e.text());
		}
		
		//jedes dritte Element der Klasse moneyClass (beginnend mit Index 0) ist ein weltweiter Gewinn
		ArrayList<String> movieWorldwide = new ArrayList<>();
		for (int i = 0; i < moneyElements.size(); i+=3) {
			Element currentElement = moneyElements.get(i);
			movieWorldwide.add(currentElement.text());
		}
		
		//jedes dritte Element der Klasse moneyClass (beginnend mit Index 1) ist ein inländischer Gewinn
		ArrayList<String> movieDomestic = new ArrayList<>();
		for (int i = 1; i < moneyElements.size(); i+=3) {
			Element currentElement = moneyElements.get(i);
			movieDomestic.add(currentElement.text());
		}
		
		//jedes dritte Element der Klasse moneyClass (beginnend mit Index 2) ist ein ausländischer Gewinn
		ArrayList<String> movieForeign = new ArrayList<>();
		for (int i = 2; i < moneyElements.size(); i+=3) {
			Element currentElement = moneyElements.get(i);
			movieForeign.add(currentElement.text());
		}
		
		//jedes zweite Element der Klasse percentClass (beginnend mit Index 0) ist ein inländischer Gewinn in Prozent
		ArrayList<String> movieDomesticPercent = new ArrayList<>();
		for (int i = 0; i < percentElements.size(); i+=2) {
			Element currentElement = percentElements.get(i);
			movieDomesticPercent.add(currentElement.text());
		}
		
		//jedes zweite Element der Klasse percentClass (beginnend mit Index 1) ist ein ausländischer Gewinn in Prozent
		ArrayList<String> movieForeignPercent = new ArrayList<>();
		for (int i = 1; i < percentElements.size(); i+=2) {
			Element currentElement = percentElements.get(i);
			movieForeignPercent.add(currentElement.text());
		}
		
		//TO-DO: Diese gescrapten Daten in eine Datenbank einbinden
		
		for (String s:movieTitle) {
			System.out.println(s);
		}
	}
}
