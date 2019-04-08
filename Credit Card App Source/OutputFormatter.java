package dao;

import java.text.NumberFormat;
import java.util.Locale;

public class OutputFormatter {

	public static void printMessage(String message) {
		System.out.println("**************************************************");
		System.out.println(message);
		System.out.println("**************************************************");
	}
	
	public static String formatCurrency(double currency) {
		Locale locale = new Locale("en", "US");      
		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
		return currencyFormatter.format(currency);
	}	
}
