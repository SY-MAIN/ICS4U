import Game.Game;

class Main {
	// Global variables
	// Create a global scanner to read in inputs from the user. By having a global
	// scanner, we can also close the scan in case of memory leak.
	static SaveScanner scan = new SaveScanner(); // custom scanner

	public static void main(String[] args) {
		// ================================================
		// Main method
		// ================================================
		// Initialize the user object. This object will be used for getting inputs,
		// storing user data, etc.
		User user = new User();

		// Display introduction to the user and explain the concept of the game.
		introduction();

		// Display the donation screen and get the user's donations.
		donateScreen(user);

		// Get the amount of individuals saved base on the donations enter by the user.
		int amountSaved = user.getAmountSaved();

		// Initialize the game object. The game object will be used to render the game
		// and run the game loop
		Game game = new Game(amountSaved);

		// run the game loop
		game.run();
		System.out.println("");

		// After exiting the game loop, display the statistics of the donations
		user.displayDonationLogs();

		// display good bye message
		System.out.println("Thank for using this program.");

		// Close the scanner
		scan.closeScan();
	}

	public static void introduction() {
		// ================================================
		// Introduction
		// ================================================
		System.out.println("Welcome to the game of donation!!\n");
		System.out.println("This game is design to help you understand the importance of your donations.");
		System.out.println("How to play: ");
		System.out.println("By donating!!! The more you donate, the more likely you will win.");
		System.out.println("How the game works: ");
		System.out.println("The game is played on a ten by ten board, and you are in the shoes of someone in poverty.");
		System.out.println(
				"Your main objective is to take over as many \"territories\" as possible before the other players take over.");
		System.out.println("One important rule is that you cannot take over other players' territory.");
		System.out.println(
				"By donating, you will increase your chances of obtaining more territories and have a higher chance of surviving and winning.");
		System.out.println("\n");
		enterToContinue();
		clearScreen();
	}

	public static void donateScreen(User user) {
		// ================================================
		// An donation screen showing all the options available for donation. Once
		// selected, the user will be prompt for further information.
		// ================================================
		// Donation prompts
		String foodPrompt = "Do you want to donate Food(y/n).";
		String clothesPrompt = "Do you want to donate Clothing(y/n).";
		String moneyPrompt = "Do you want to donate money(y/n).";

		// Get answer from the user.
		String ans = scan.nextLine(foodPrompt, true).toLowerCase();

		// Check if the user wants to donate food.
		if (ans.startsWith("y")) {
			// get food donations
			user.getFood();
			clearScreen();
		}
		// Get answer from the user.
		ans = scan.nextLine(clothesPrompt, true).toLowerCase();

		// Check if the user wants to donate clothing.
		if (ans.startsWith("y")) {
			// get clothes donations
			user.getClothing();
			clearScreen();
		}
		// Get answer from the user.
		ans = scan.nextLine(moneyPrompt, true).toLowerCase();

		// Check if the user wants to donate money.
		if (ans.startsWith("y")) {
			// get money donations
			user.getMoney();
			clearScreen();
		}
	}

	// ================================================
	// Helper methods
	// ================================================

	public static void enterToContinue() {
		// ================================================
		// An method to wait for user to enter anything
		// ================================================
		String prompt = "Enter anything to continue...";
		scan.nextLine(prompt, true);
	}

	public static void clearScreen() {
		// ================================================
		// An method to clear the console
		// ================================================
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public static void wait(int ms) {
		// ================================================
		// An method to wait in milliseconds
		// ================================================
		try {
			Thread.sleep(ms);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
}