import java.io.IOException;
import java.util.ArrayList;

public class BoxOfficeMain {

	public static void main(String[] args) throws IOException {
		ArrayList<ArrayList<String>> data2020 = MovieScraper.ScrapeByYear(2020);
	}

}
