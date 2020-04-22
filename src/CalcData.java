import java.util.ArrayList;

public class CalcData {
	public static double[] castToDoubleArray(ArrayList<String> arrStr, boolean isMoney) {
		double[] arrDoub = new double[arrStr.size()];
		for (int i = 0; i < arrDoub.length; i++) {
			try {
				String s;
				if (isMoney) {
					s = formatMoneyNumber(arrStr.get(i));
				}
				else { //wenn es kein Geldbetrag ist, dann ein Prozentwert
					s = formatPercentNumber(arrStr.get(i));
				}
				arrDoub[i] = Double.parseDouble(s);
			} catch (NumberFormatException e) {
				arrDoub[i] = 0; //"-" bedeutet 0
			}
		}
		return arrDoub;
	}
	public static double avg(double[] arrDoub) {
		return sum(arrDoub)/arrDoub.length;
	}
	public static double sum(double[] arrDoub) {
		double sum = 0;
		for (int i = 0; i < arrDoub.length; i++) {
			sum += arrDoub[i];
		}
		return sum;
	}
	private static String formatMoneyNumber(String s) {
		if (s.equals("-")) { //dieser Fall wird auserhalb der Methode behandelt
			return s;
		}
		s = s.substring(1); //entfernt $-Zeichen
		s = s.replace(",", ""); //entfernt ,-Zeichen
		return s;
	}
	private static String formatPercentNumber(String s) {
		if (s.equals("-")) {
			return s;
		}
		s = s.replace("%", ""); //entfernt %-Zeichen
		return s;
	}
}
