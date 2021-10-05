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
	// utility method for getting inputs from user
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

	// ==============================================================
	// utility method
	// ==============================================================
}