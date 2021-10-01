import Donations.*;

public class User {
	// Global variables
	String name;
	int age;
	SaveScanner scan = new SaveScanner();

	// User's donations
	double amountOfWater;
	double amountOfMoney;
	FoodItem[] foodItems;
	Clothing[] clothings;
	Shelter[] shelters;

	// Constructor
	public User() {

	}

	// ==============================================================
	// utility method for getting inputs from user
	// ==============================================================

	public void getAmountOfWater() {
		String prompt = "Enter the amount of water in litres do you want to donate: ";
		amountOfWater = scan.nextDouble(prompt, true);
	}

	public void getAmountOfMoney() {
		String prompt = "Enter the amount of money in dollars do you want to donate: ";
		amountOfMoney = scan.nextDouble(prompt, true);
	}

	public void getFoodItems() {
		System.out.println("Enter the foods in order: ");
		int foodCounter = 0;
		while (true) {
			System.out.print("Food Item #" + foodCounter + ": ");
			String foodItem = scan.nextLine("", false);
		}
	}
}