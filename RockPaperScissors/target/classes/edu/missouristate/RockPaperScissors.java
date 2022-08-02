//Hansen, Alexander

package edu.missouristate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.SecureRandom;

public class RockPaperScissors {
	BufferedReader bufferedReader = null;
	SecureRandom random = new SecureRandom();
	String message = "Welcome to Rock Paper Scissors";
	

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Help");
		new RockPaperScissors().playGame();
		System.out.println("Help");
	}
	private void playGame() throws Exception {
		//initialize buffReader
		try {	
			bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			while(true) {
				displayMainMenu();
				int menuOption = getMainMenuOption();
				//System.out.println("You selected "+ menuOption );
				processMainMenuOption(menuOption);
				
			}
		
		} catch(Exception e) {
		e.printStackTrace();
	}
		
	
	}

	private void processMainMenuOption(int menuOption) throws Exception {
		switch(menuOption) {
		case 1: displayGameMenu();
			break;
		case 2: quit(0);
			break;
		}
		
	}

	private void quit(int exitCode) {
		System.out.println("\n\nGood Bye!");
		try {
			bufferedReader.close();
		} catch(Exception e) {}
		System.exit(exitCode);
	}

	private void displayGameMenu() throws IOException {
		clearTheConsole();
		
		System.out.println("\n\n");
		System.out.println("*************************************");
		System.out.println("*        ROCK PAPER SCISSORS        *");
		System.out.println("*************************************");
		System.out.println("* 1. Rock                           *");
		System.out.println("* 2. Paper                          *");
		System.out.println("* 3. Scissors                       *");
		System.out.println("*************************************");
		
		if(message != null && message.length() > 0) {
			System.out.println(message);
			message = "";
		}
		
		System.out.println("Select Rock (1), Paper (2), or Scissors (3):");
		int gameMenuOption = getGameMenuOption();
		//System.out.println("You selected " + gameMenuOption);
		processGameMenuOption(gameMenuOption);
		
		
	}

	private void processGameMenuOption(int userSelection) throws IOException {
		int computerSelection = getRandomNumber(1,3);
		boolean selectionsMatch = (userSelection == computerSelection);
		if(selectionsMatch) {
			message = "you both selected " + getLiteralSelection(userSelection) + "Try again...";
			displayGameMenu();
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append("Your Selection     : " + getLiteralSelection(userSelection)+"\n");
			sb.append("Computer Selection :" + getLiteralSelection(computerSelection)+"\n" );
			
			switch(userSelection) {
			case(1): //Rock
				switch(computerSelection) {
				case(2): sb.append("Paper covers rock. You Lose!"); break;
				case(3): sb.append("Rock crushes scissors. You Win!"); break;
				}
				break;
			case(2): //Paper
				switch(computerSelection) {
				case(1): sb.append("Paper Covers rock. You Win! "); break;
				case(3): sb.append("Scissors cuts paper. You Lose!"); break;
				}
				break;			
			case(3): //Scissors
				switch(computerSelection) {
				case(1): sb.append("Rock crushes Scissors. You Lose!"); break;
				case(2): sb.append("Scissors cuts Paper. You Win!"); break;
				}
			break;
		}
		
	}
	}

	private int getRandomNumber(int min, int max) {
		return random.nextInt(max - min +1)+min;
	}

	private String getLiteralSelection(int selection) {
		switch(selection) {
		case(1): return "Rock";
		case(2): return "Paper";
		case(3): return "Scissors";
		default: return "";
		}
	
	}

	private int getGameMenuOption() throws IOException {
		int gameMenuOption = -1;
		String inputString = bufferedReader.readLine();
		
		if(isValidMenuOption(inputString, new Integer[] {1,2,3})) {
			gameMenuOption = Integer.parseInt(inputString);
		} else {
			message = "Invalid Selection";
		}
		displayGameMenu();
		getGameMenuOption();
		return gameMenuOption;
	}

	private int getMainMenuOption() throws IOException {
		int menuOption = -1;
		String inputString = bufferedReader.readLine();
		
		if(isValidMenuOption(inputString, new Integer[] {1,2})) {
			menuOption = Integer.parseInt(inputString);
		} else {
			message = "Invalid Selection";
			displayMainMenu();
			getMainMenuOption();
		}
		return menuOption;
	}

	private boolean isValidMenuOption(String inputString, Integer[] integers) {
		try {
			Integer input = Integer.parseInt(inputString);
			
			for(Integer integer : integers) {
				if(input.intValue() == integer.intValue()) {
					return true;
				}
			}
			
		}catch(NumberFormatException nfe) {
			return false;
		}
		return false;
	}

	private void displayMainMenu() {
		clearTheConsole();
		
		System.out.println("\n\n");
		System.out.println("*************************************");
		System.out.println("*        ROCK PAPER SCISSORS        *");
		System.out.println("*************************************");
		System.out.println("* 1. Play Rock Paper Scissors       *");
		System.out.println("* 2. Exit                           *");
		System.out.println("*************************************");
		
		if(message != null && message.length() > 0) {
			System.out.println(message);
			message = "";
		}
		System.out.println("Please Select a menu Option");
	}
	
	public static void clearTheConsole() {
		try {
			Thread.sleep(500);
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
