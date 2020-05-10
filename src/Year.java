import java.io.IOException;
import java.util.ArrayList;

public class Year {
	ArrayList<String> titles;
	double[] worldwideEarnings;
	double[] domesticEarnings;
	double[] domesticPercents;
	double[] foreignEarnings;
	double[] foreignPercents;
	
	public Year(int year) throws IOException {
		ArrayList<ArrayList<String>> data = MovieScraper.ScrapeByYear(year);
		this.titles = data.get(0);
		this.worldwideEarnings = CalcData.castToDoubleArray(data.get(1), true);
		this.domesticEarnings = CalcData.castToDoubleArray(data.get(2), true);
		this.domesticPercents = CalcData.castToDoubleArray(data.get(3), false);
		this.foreignEarnings = CalcData.castToDoubleArray(data.get(4), true);
		this.foreignPercents = CalcData.castToDoubleArray(data.get(5), false);
	}
}
