import java.util.*;
import java.lang.String;

public class User {
	// ================================================
	// Global variables
	// ================================================

	// To store all the information about the donations the user made,
	ArrayList<String> donationStatusLog = new ArrayList<String>();

	// Create scanner object to take in inputs from the user.
	SaveScanner scan = new SaveScanner();

	// font colors
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_WHITE = "\u001B[37m";
	public static final String ANSI_BLUE = "\u001B[34m";

	// User's donations
	int amountOfPants = 0;
	int amountOfShirt = 0;
	int amountOfFood = 0;

	double amountOfCalories = 0.0;
	double amountOfMoney = 0.0;

	// ==============================================================
	// utility method for getting inputs from the user
	// ==============================================================

	public void getClothing() {
		// ================================================
		// Get the user's clothing donation
		// ================================================
		// prompt
		System.out.println("Enter the clothings you want to donate(Enter exit to exit) ");

		while (true) {
			// Ask the user to enter the name of the clothing.
			System.out.print("Name -> ");
			String clothingName = scan.nextLine("", false).toLowerCase();

			// sentinel value to exit the while loop.
			if (clothingName.equals("exit")) {
				break;
			}

			// Ask the user to enter the type of the clothing
			System.out.print("Type(shirt or pants) -> ");
			String clothingType = scan.nextLine("", false);

			// CHeck wether the spelling is correct.
			if (clothingType.equals("shirt") || clothingType.equals("pants")) {
				// Increment the pants counter
				if (clothingType.equals("pants")) {
					amountOfPants++;
				}
				// Increment the shirt counter

				else if (clothingType.equals("shirt")) {
					amountOfShirt++;
				}
			}
			// friendly warning
			else {
				System.out.println("Invalid Option! Please check your spelling!");
			}
		}
	}

	public void getFood() {
		// ================================================
		// Get the user's food donation
		// ================================================
		System.out.println("Enter the foods you want to donate(Enter exit to exit) ");

		while (true) {
			// Ask the user to enter the name of the food donation.
			System.out.print("Name -> ");
			String foodName = scan.nextLine("", false).toLowerCase();

			// sentinel value to exit the while loop.
			if (foodName.equals("exit")) {
				break;
			}

			// Ask the user to enter the amount of calories is in the food.
			System.out.print("Calories -> ");
			amountOfCalories += scan.nextDouble("", false);

			// Increment the food counter.
			this.amountOfFood++;
			scan.nextLine("", false);
		}
	}

	public void getMoney() {
		// ================================================
		// Get the user's money donation
		// ================================================
		// Prompt
		String prompt = "Enter the amount of money you want to donate:";

		// Ask the user to enter the money donation.
		amountOfMoney = scan.nextDouble(prompt, true);
		scan.nextLine("", false);

	}

	private int calNumHelpClothes() {
		// ================================================
		// Calculate the amount of people helped base on the donations
		// ================================================
		// Assuming every person is saved/helped from some harsh environment if they
		// have at least one pant, and one shirt.
		int amountOfPeopleHelped = 0;

		// Return the lowest amount which type of clothing.
		if (this.amountOfPants >= this.amountOfShirt) {
			amountOfPeopleHelped = this.amountOfShirt;
		} else {
			amountOfPeopleHelped = this.amountOfPants;
		}

		// Message
		String logMessage = "With " + ANSI_BLUE + (int) (this.amountOfPants + this.amountOfShirt) + ANSI_WHITE
				+ " different pieces of clothing donated, we can supply about " + ANSI_BLUE + amountOfPeopleHelped + ANSI_WHITE
				+ " individuals with clothing to survive the harsh environments. ";
		this.donationStatusLog.add(logMessage);

		return amountOfPeopleHelped;
	}

	private int calNumHelpFood() {
		// ================================================
		// Calculate the amount of people helped base on the donations
		// ================================================
		// An average individual's estimated daily food consumption is around 2400
		// calories per day in rural areas.

		int minimumCal = 2400;
		int amountOfPeopleHelped = (int) (this.amountOfCalories / minimumCal);

		// Message
		String logMessage = "With " + ANSI_BLUE + this.amountOfFood + ANSI_WHITE
				+ " different types of food donated, we can supply about " + ANSI_BLUE + amountOfPeopleHelped + ANSI_WHITE
				+ " individuals with food to survive from starvation.";
		this.donationStatusLog.add(logMessage);
		return amountOfPeopleHelped;
	}

	private int calNumHelpMoney() {
		// ================================================
		// Calculate the amount of people helped base on the donations
		// ================================================
		// https://secure.unicef.ca/page/31858/donate/1
		// $20 -> Sends nutritious food to 14 children.
		// $50 -> Gives 59 children life-saving vaccines.
		// $100 -> Provides 137 children with safe water to drink and bathe.

		// in cash
		int amount_nutritiousFood = 20;
		int amount_vaccine = 50;
		int amount_safeWater = 100;

		// amount of children saved
		int amountSaved_nutritiousFood = 14;
		int amountSaved_vaccine = 59;
		int amountSaved_safeWater = 137;

		int helpChildren_nutritiousFood = (int) (this.amountOfMoney / amount_nutritiousFood) * amountSaved_nutritiousFood;
		int helpChildren_vaccine = (int) (this.amountOfMoney / amount_vaccine) * amountSaved_vaccine;
		int helpChildren_saveWater = (int) (this.amountOfMoney / amount_safeWater) * amountSaved_safeWater;

		// return the average children saved
		int amountOfPeopleHelped = (helpChildren_nutritiousFood + helpChildren_vaccine + helpChildren_saveWater) / 3;

		// Messages
		String logMessage = "With " + ANSI_GREEN + "$" + this.formatMoney(this.amountOfMoney) + ANSI_WHITE
				+ ", we can supply " + ANSI_BLUE + helpChildren_nutritiousFood + ANSI_WHITE
				+ " children with nutritious foods.";
		this.donationStatusLog.add(logMessage);

		logMessage = "With " + ANSI_GREEN + "$" + this.formatMoney(this.amountOfMoney) + ANSI_WHITE + ", we can supply "
				+ ANSI_BLUE + helpChildren_vaccine + ANSI_WHITE + " children with life-saving vaccines.";
		this.donationStatusLog.add(logMessage);

		logMessage = "With " + ANSI_GREEN + "$" + this.formatMoney(this.amountOfMoney) + ANSI_WHITE + ", we can supply "
				+ ANSI_BLUE + helpChildren_saveWater + ANSI_WHITE + " children with safe water.";
		this.donationStatusLog.add(logMessage);

		return amountOfPeopleHelped;
	}

	public int getAmountSaved() {
		// ================================================
		// An method to get the total amount of people helped
		// ================================================
		return this.calNumHelpClothes() + this.calNumHelpFood() + this.calNumHelpMoney();
	}

	public void displayDonationLogs() {
		// ================================================
		// An method to display all the donation messages
		// ================================================
		for (int i = 0; i < this.donationStatusLog.size(); i++) {
			System.out.println(this.donationStatusLog.get(i));
		}
	}

	// ====================================
	// Methods for formatting different Strings
	// ====================================

	private String formatMoney(double amt) {
		// Convert the amount to proper money format. For example $40.00
		String amount = Double.toString(amt);
		String[] strArray = amount.split("\\.");

		// Format the string according to the decimal. If the decimal has 0 decimal
		// places, add two 0's to the string. If there is one decimal place, add one 0
		// to the string.
		if (strArray[1].length() == 2) {
			return strArray[0] + "." + strArray[1];
		} else if (strArray[1].length() == 1) {
			return strArray[0] + "." + strArray[1] + "0";
		} else if (strArray[1].length() == 0) {
			return strArray[0] + ".00";
		}
		return amount;
	}
}