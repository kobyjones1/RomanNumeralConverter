import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class romNumToNum {

	static Scanner userInput = new Scanner(System.in);	//Take the user's input.
	
	public static void main(String[] args) {
		String romNum = checkInput();	//Check that the input contains only Roman numerals.
		userInput.close();
		
		int convNum = calcRomNum(romNum);	//Convert the Roman numerals into an integer.
		
		System.out.println("--------------------");
		System.out.println(convNum);
	}
	
	public static String checkInput() {
		String romNumConcat = null;
		boolean loopFinish = false;
		int loopCount = 0;
		
		while(loopFinish == false) {	//Loop until the user's input is valid.
			System.out.println("Valid Roman numerals are: I, V, X, L, C, D and M.");
			System.out.println("I = 1, V = 5, X = 10, L = 50, C = 100, D = 500, M = 1,000");
			System.out.println("Enter Roman numerals to be converted to a number (From 1 to 3,999): ");

			String userRomNum = userInput.next();
			
			for(int i = 0; i < userRomNum.length(); i++) {	//Check to make sure each character is a Roman numeral.
				if(userRomNum.length() >= 10) {
					break;
				}
				
				try {
					char c = userRomNum.charAt(i);
					c = Character.toUpperCase(c);	//Checks for numbers and special characters.
					boolean notRomNum = checkLetters(c);
					
					if(notRomNum == true) {
						break;
					}
					
					if(i == 0){
						romNumConcat = Character.toString(c);
					}
					else {
						romNumConcat += Character.toString(c);
						boolean notConcatValid = checkRomConcat(romNumConcat);	//Checks to make sure the Roman numerals are valid.
						
						if (notConcatValid == true) {
							break;
						}
					}
				}
				catch(Exception e) {
					System.out.println("Invalid input.");
					break;
				}
				
				loopCount++;
			}
			
			if(loopCount == userRomNum.length()) {
				loopFinish = true;
			}
		}
		
		return romNumConcat;
	}
	
	public static boolean checkLetters(char c) {
		boolean switchState = false;
		
		switch (c)
		{
		case 'I':
			return switchState;
		case 'V':
			return switchState;
		case 'X':
			return switchState;
		case 'L':
			return switchState;
		case 'C':
			return switchState;
		case 'D':
			return switchState;
		case 'M':
			return switchState;
		default:
			System.out.println("Invalid input.");
			switchState = true;
			return switchState;
		}
	}
	
	public static boolean checkRomConcat(String romConcat) {
		boolean switchState = true;
		
		if(compareLetters(romConcat, 'V', 'X') == false) {
			System.out.println("V cannot be placed before X.");
			return switchState;
		}
		else if(compareLetters(romConcat, 'L', 'C') == false){
			System.out.println("L cannot be placed before C.");
			return switchState;
		}
		else if(compareLetters(romConcat, 'D', 'M') == false){
			System.out.println("D cannot be placed before M.");
			return switchState;
		}
		else if(letterLoop(romConcat, 'I') >= 4) {
			System.out.println("I can only repeat a maximum of 3 times.");
			return switchState;
		}
		else if (letterLoop(romConcat, 'X') >= 4) {
			System.out.println("X can only repeat a maximum of 3 times.");
			return switchState;
		}
		else if (letterLoop(romConcat, 'C') >= 4) {
			System.out.println("C can only repeat a maximum of 3 times.");
			return switchState;
		}
		else if (letterLoop(romConcat, 'M') >= 4) {
			System.out.println("M can only repeat a maximum of 3 times.");
			return switchState;
		}
		else if (letterLoop(romConcat, 'V') >= 2) {
			System.out.println("V cannot repeat.");
			return switchState;
		}
		else if (letterLoop(romConcat, 'L') >= 2) {
			System.out.println("L cannot repeat.");
			return switchState;
		}
		else if (letterLoop(romConcat, 'D') >= 2) {
			System.out.println("D cannot repeat.");
			return switchState;
		}
		else {
			switchState = false;
		}
		
		return switchState;
	}
	
	public static boolean compareLetters(String romConcat, char smallNum, char bigNum) {	//Compares two characters to follow Roman numeral rule.
		int checkSmall = 0, checkBig = 0;
		
		if(romConcat.indexOf(smallNum) > -1) {
			checkSmall = romConcat.indexOf(smallNum);
		}
		
		if(romConcat.indexOf(bigNum) > -1) {
			checkBig = romConcat.indexOf(bigNum);
		}
		
		if(checkSmall == 0 || checkBig == 0) {
			return true;
		}
		else if(checkSmall < checkBig) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public static int letterLoop(String romConcat, char charSearch) {	//Finds duplicated letters from the user's input.
		int count = 0;
		
		for(int i = 0; i < romConcat.length(); i++) {
			char currentChar = romConcat.charAt(i);
			
			if (currentChar == charSearch) {
				count++;
			}
		}
		
		return count;
	}

	public static int calcRomNum (String romNum) {
		Map<Character, Integer> romCharMap = new HashMap<Character, Integer>();
		romCharMap.put('I', 1);
		romCharMap.put('V', 5);
		romCharMap.put('X', 10);
		romCharMap.put('L', 50);
		romCharMap.put('C', 100);
		romCharMap.put('D', 500);
		romCharMap.put('M', 1000);

		int finalVal = 0;
		
		for(int i = 0; i < romNum.length(); i++) {
			if(i > 0 && romCharMap.get(romNum.charAt(i)) > romCharMap.get(romNum.charAt(i - 1))) {
				finalVal += romCharMap.get(romNum.charAt(i)) - (2 * romCharMap.get(romNum.charAt(i - 1)));	//For subtracting values.
			}
			else {
				finalVal += romCharMap.get(romNum.charAt(i));	//For adding values
			}
		}
		
		return finalVal;
	}
}
