import java.util.ArrayList;

public class User {
	// Global variables
	ArrayList<ArrayList<String>> donationLogs = new ArrayList<ArrayList<String>>();

	// ID DonationItem Name Quantity
	// 1 clothing pants 2

	SaveScanner scan = new SaveScanner();

	// User's donations
	int amountOfPants = 0;
	int amountOfShirt = 0;
	double amountOfMoney = 0;
	double amountOfCalories = 0;

	public void logTransactions(String donationID, String Name, int Quantity) {
	}

	// ==============================================================
	// utility method for getting inputs from the user
	// ==============================================================

	public void getClothing() {
		System.out.println("Enter the clothings you want to donate(Enter exit to exit) ");

		while (true) {
			System.out.print("Name -> ");
			String clothingName = scan.nextLine("", false).toLowerCase();

			if (clothingName.equals("exit")) {
				break;
			}

			System.out.print("Type(shirt or pants) -> ");
			String clothingType = scan.nextLine("", false);
			if (clothingType.equals("shirt") || clothingType.equals("pants")) {
				if (clothingType.equals("pants")) {
					amountOfPants++;
				} else if (clothingType.equals("shirt")) {
					amountOfShirt++;
				}
			} else {
				System.out.print("Invalid Option! Please check your spelling!");
			}
		}
	}

	public void getFood() {
		System.out.println("Enter the foods you want to donate(Enter exit to exit) ");

		while (true) {
			System.out.print("Name -> ");
			String foodName = scan.nextLine("", false).toLowerCase();

			if (foodName.equals("exit")) {
				break;
			}

			System.out.print("Calories -> ");
			amountOfCalories += scan.nextDouble("", false);
			scan.nextLine("", false);
		}
	}

	public void getMoney() {
		String prompt = "Enter the amount of money you want to donate:";

		amountOfMoney = scan.nextDouble(prompt, true);
		scan.nextLine("", false);

	}

	private int calNumHelpClothes() {
		// Assuming every person is saved/helped from some harsh environment if they
		// have at least one pant, and one shirt.

		if (this.amountOfPants >= this.amountOfShirt) {
			return this.amountOfShirt;
		} else {
			return this.amountOfPants;
		}
	}

	private int calNumHelpFood() {
		// An average individual's estimated daily food consumption is around 2400
		// calories per day in rural areas.

		int minimumCal = 2400;
		int amountOfPeopleHelped = (int) (this.amountOfCalories / minimumCal);
		return amountOfPeopleHelped;
	}

	private int calNumHelpMoney() {
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
		int averageSaved = (helpChildren_nutritiousFood + helpChildren_vaccine + helpChildren_saveWater) / 3;

		return averageSaved;
	}

	public int getAmountSaved() {
		return this.calNumHelpClothes() + this.calNumHelpFood() + this.calNumHelpMoney();
	}

}