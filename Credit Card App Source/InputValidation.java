package runner;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidation {
	
	private static final String states 
		= "AK|AL|AR|AZ|CA|CO|CT|DC|DE|FL|GA|HI|IA|ID|IL|IN|KS|KY|LA|MA|MD|ME|MI|MN|MO|MS|MT|NC|ND|NE|NH|NJ|NM|NV|NY|OH|OK|OR|PA|RI|SC|SD|TN|TX|UT|VA|VT|WA|WI|WV|WY" 
				+ "ak|al|ar|az|ca|co|ct|dc|de|fl|ga|hi|ia|id|il|in|ks|ky|la|ma|md|me|mi|mn|mo|ms|mt|nc|nd|ne|nh|nj|nm|nv|ny|oh|ok|or|pa|ri|sc|sd|tn|tx|ut|va|vt|wa|wi|wv|wy";
	private static final String categories
		= "Education|Entertainment|Grocery|Gas|Bills|Test|Healthcare|"
				+ "|education|entertainment|grocery|gas|bills|test|healthcare";
	
	public static String getStates() {
		return states;
	}
	
	public static String getCategories() {
		return categories;
	}
	
	public static boolean isNumeric(String numString) {
		return Pattern.matches("\\d+", numString);
	}
	
	public static boolean isAlpha(String alphaString) {
		return Pattern.matches("[ a-zA-Z]+", alphaString);
	}
	
	public static boolean isValidEmail(String email) { 
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$";                              
        Pattern pat = Pattern.compile(emailRegex); 
        if (email == null || email.length() > 40) 
            return false; 
        return pat.matcher(email).matches(); 
    } 
	
	public static void printInvalidEntry(String entryName, String request) {
		System.out.print("********************************************\n");
		System.out.print("***You have entered invalid "+entryName+"***\n");
		System.out.print("********************************************\n");
		System.out.print(request);
		
	}
	
	public static int getValidInteger(int limit1, int limit2, String word, String sentence, Scanner input) {
		System.out.print(sentence);
		boolean valid = false;
		int validInt = 0;
		while (!valid) {
			try {
				validInt = input.nextInt();
				while (validInt < limit1 || validInt > limit2) {
					printInvalidEntry(word, sentence);
					validInt = input.nextInt();
				}
				valid = true;
			}
			catch (InputMismatchException e) {
				printInvalidEntry(word, sentence);
				input.next();
			}
		}
		return validInt;
	}
	
	public static void pressEnter(Scanner s) {
		System.out.println("Press ENTER to continue.");
		s.nextLine();
		s.nextLine();
	}
	
	public static String makeTitleCase(String name) {
		String titleName = "";
		String[] split = name.split(" ");
		for (String s: split) {
			String firstLetter = s.substring(0,1).toUpperCase();
			String endOfName = s.substring(1, s.length()).toLowerCase();
			if (split[0] != s) {
				titleName = titleName + " ";
			}
			titleName = titleName + firstLetter + endOfName;
		}
		return titleName;
	}  
	
	public static String getValidName(int l, String field, String request, Scanner input, boolean next, boolean alphaOnly) {
		System.out.print(request);
		String name = input.next();	
		while ((name.length() == 0 || name.length() > l || name == " ") 
				|| (alphaOnly && !isAlpha(name))) {		
			if (next) {
				input.nextLine();
				next = false;
			}
			printInvalidEntry(field, request);
			name = input.nextLine();
		}
		return makeTitleCase(name);
	}
	
	public static String getValidNumericString(String field, String request, int l, Scanner input) {
		System.out.print(request);
		String numericString = input.next();
		while (numericString.length() != l || !isNumeric(numericString)) {
			printInvalidEntry(field, request);
			numericString = input.next();
		}	
		return numericString;
	}
	
	public static String getValidEmail(String field, String request, Scanner input) {
		System.out.print(request);
		String email = input.next();
		while (!InputValidation.isValidEmail(email)) {
			printInvalidEntry(field, request);
			email = input.next();
		}
		return email.toLowerCase();
	}
	
	public static String getValidChoice(String field, String request, Scanner input, String validChoices) {
		System.out.print(request); 
		String choice = input.next().toLowerCase();
        CharSequence inputStr = choice;
        Pattern pattern = Pattern.compile(validChoices);
        Matcher matcher = pattern.matcher(inputStr);  
        while (!matcher.matches()) {
        	printInvalidEntry(field, request);
	        choice = input.next().toLowerCase();
	        inputStr = choice;
            pattern = Pattern.compile(validChoices);
            matcher = pattern.matcher(inputStr);
        }
        return choice;
	}
	
	public static String getValidState(String field, String request, Scanner input) {
		return getValidChoice(field, request, input, getStates()).toUpperCase();
	}
	
	public static String getValidCategory(String field, String request, Scanner input) {
		String validChoice = getValidChoice(field, request, input, getCategories());
		return makeTitleCase(validChoice);	
	}
}
