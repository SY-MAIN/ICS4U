import java.util.ArrayList;

public class User {
	// Global variables
	ArrayList<ArrayList<String>> donationLogs = new ArrayList<ArrayList<String>>();

	// ID DonationItem Name Quantity
	// 1 clothing pants 2

	SaveScanner scan = new SaveScanner();

	// User's donations
	int amountOfClothing = 0;
	int amountOfCalories = 0;

	public void logTransactions(String donationID, String Name, int Quantity) {
	}

	// ==============================================================
	// utility method for getting inputs from user
	// ==============================================================

	public void getClothing() {
		System.out.println("Enter the amount of clothings you want to donate: ");
		amountOfClothing = scan.nextInt("", false);
	}

	// ==============================================================
	// utility method
	// ==============================================================

	public int calClothingPerPerson() {
		// Assuming one person requires two pieces of clothings
		return Math.floorDiv(amountOfClothing, 2);
	}

	public int calFoodPerChild() {
		int numCalPerChild = 2000;
		return amountOfCalories / numCalPerChild;
	}
}