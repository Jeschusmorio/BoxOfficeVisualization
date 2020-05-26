import java.io.IOException;

public class BoxOfficeMain {
	final static int MAXYEAR = 2020;
	static int STARTYEAR;
	public static Year[] years;

	public static void main(String[] args) throws IOException {
		Year[] y = getYears(); //Startjahr als Parameter übergebbar
		if (y != null) {
			years = y;
			System.out.println("\nFINISHED SCRAPING -- SHOWING RESULTS");
			Visualization.visualize(args);
		}
	}
	public static Year[] getYears() throws IOException {
		return getYears(1977);
	}
	public static Year[] getYears(int startYear) throws IOException {
		if (startYear < 1977 || startYear > MAXYEAR) {
			System.out.println("Nur Daten zwischen den Jahren 1977 und "+MAXYEAR+" existent! ("+startYear+")");
			return null;
		}
		STARTYEAR = startYear;
		Year[] allYears = new Year[MAXYEAR - startYear + 1];
		int year = startYear;
		for (int i = 0; i < allYears.length; i++) {
			Year nextYear = new Year(year);
			allYears[i] = nextYear;
			year++;
		}
		return allYears;
	}
	public static int getIndexByYear(int y, Year[] allYears) {
		for (int i = 0; i < allYears.length; i++) {
			if (allYears[i].year == y) {
				return i;
			}
		}
		System.out.println("Das gesuchte Jahr ist nicht enthalten! (" + y + ")");
		return -1;
	}

}
