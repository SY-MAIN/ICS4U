import Donations.*;
import java.util.ArrayList;

public class User {
	// Global variables
	ArrayList<ArrayList<String>> donationLogs = new ArrayList<ArrayList<String>>();

	// ID DonationItem Name Quantity
	// 1 clothing pants 2

	SaveScanner scan = new SaveScanner();

	// User's donations
	ArrayList<Clothing> clothings = new ArrayList<Clothing>();

	// Constructor
	public User() {
	}

	public void logTransactions(String dontaionID, String Name, int Quantity) {
	}

	// ==============================================================
	// utility method for getting inputs from user
	// ==============================================================

	public void getClothing() {
		System.out.println("Enter the clothings in order(Enter exit to exit): ");
		int clothingCounter = 1;
		while (true) {
			System.out.print("clothes Item #" + clothingCounter++ + ": ");
			String item = scan.nextLine("", false).toLowerCase();

			if (item.equals("exit")) {
				break;
			}

			// Create Food object
			Clothing itemObj = new Clothing(item);
			// Save to the array of food items
			clothings.add(itemObj);
		}
	}

	// temp
	public void displayClothing() {
		for (int i = 0; i < clothings.size(); i++) {
			System.out.println(clothings.get(i).getClothingName());
		}
	}

	// ==============================================================
	// utility method
	// ==============================================================

	public void CalClothingPerPerson() {

	}
}