import Game.Game;

class Main {
	static SaveScanner scan = new SaveScanner();

	public static void main(String[] args) {
		User user = new User();

		Game game = new Game();
		introduction();
		donateScreen(user);
		game.run();

		System.out.println("Thank for using this program.");
		scan.closeScan();
	}

	public static void introduction() {
		// ================================================
		// Introduction
		// ================================================
		System.out.println("Welcome to the game of donation!!\n");
		System.out.println("This game is designed to help you understand the importance of your donations.");
		System.out.println("How to play: ");
		System.out.println("By donating!!! The more you donate, the more likely you will win.");
		System.out.println("How the game works: ");
		System.out.println("The game is played on a 10 by 10 board and you are in the shoes of someone in poverty.");
		System.out.println(
				"Your main objective is to take over as many \"territory\" as possible before the other players take over.");
		System.out.println("One major rule is you cannot take over other players territory.");
		System.out.println("By donating, you will have a high chance of surviving and winning");
		System.out.println("\n");
		enterToContinue();
		clearScreen();
	}

	public static void enterToContinue() {
		String prompt = "Enter anything to continue...";
		scan.nextLine(prompt, true);
	}

	// ================================================================
	// Screens
	// ================================================================
	// public static String menuScreen() {
	// String options[] = { "Start" };

	// while (true) {
	// clearScreen();
	// String textOptions[] = { "New Game(1)" };

	// displayCenterText(textOptions);
	// String prompt = "Select an option to continue...";

	// String option = scan.nextLine(prompt, true);

	// // If the user entered an int
	// try {
	// int IntOption = Integer.parseInt(option);

	// // Sentinel value
	// if (IntOption == -1) {
	// return "Exit";
	// } else if (IntOption < 0 || IntOption > 3) {
	// System.out.println("Invalid Option!");
	// wait(2000);
	// continue;
	// }
	// // Check boundaries between 1, 3

	// return (options[IntOption - 1]);
	// } catch (NumberFormatException e) {
	// // User entered an String option
	// // Check if the option is in the options list.
	// for (int i = 0; i < options.length; i++) {
	// if (options[i].equals(option)) {
	// return option;
	// }
	// }
	// System.out.println("Incorrect or Invalid Option!");
	// wait(2000);
	// }
	// }
	// }

	public static void donateScreen(User user) {
		String foodPrompt = "Do you want to donate Food.";
		String clothesPrompt = "Do you want to donate Clothing.";
		String moneyPrompt = "Do you want to donate money.";

		String ans = scan.nextLine(foodPrompt, true).toLowerCase();

		if (ans.startsWith("y")) {
			// get food donations
			user.getFood();
			clearScreen();

		}

		ans = scan.nextLine(clothesPrompt, true).toLowerCase();

		if (ans.startsWith("y")) {
			// get clothes donations
			user.getClothing();
			clearScreen();
		}

		ans = scan.nextLine(moneyPrompt, true).toLowerCase();

		if (ans.startsWith("y")) {
			// get money donations
			user.getMoney();
			clearScreen();
		}

	}

	public static void displayCenterText(String options[]) {
		int totalWidth = 30;

		System.out.println();

		for (int i = 0; i < options.length; i++) {
			String current = options[i];
			int spaceLeft = totalWidth - current.length();

			int left = spaceLeft / 2;
			int right = left + current.length();

			// Print left amount of spaces
			for (int j = 0; j < left; j++) {
				System.out.print(" ");
			}
			System.out.print(current);

			for (int j = 0; j < right; j++) {
				System.out.print(" ");
			}

			System.out.println("\n");
		}
	}

	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public static void wait(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
}