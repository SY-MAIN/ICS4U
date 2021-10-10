import Game.Game;

class Main {
	static SaveScanner scan = new SaveScanner();

	public static void main(String[] args) {
		User user = new User();

		introduction();
		donateScreen(user);
		int amountSaved = user.getAmountSaved();
		Game game = new Game(amountSaved);
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

	public static void donateScreen(User user) {
		String foodPrompt = "Do you want to donate Food.";
		String clothesPrompt = "Do you want to donate Clothing.";
		String moneyPrompt = "Do you want to donate money.";

		String ans = scan.nextLine(foodPrompt, true).toLowerCase();

		if (ans.startsWith("y")) {
			// get food donations
			user.getFood();
			clearScreen();
		} else if (ans.startsWith("n")) {
			return;
		}

		ans = scan.nextLine(clothesPrompt, true).toLowerCase();

		if (ans.startsWith("y")) {
			// get clothes donations
			user.getClothing();
			clearScreen();
		} else if (ans.startsWith("n")) {
			return;
		}

		ans = scan.nextLine(moneyPrompt, true).toLowerCase();

		if (ans.startsWith("y")) {
			// get money donations
			user.getMoney();
			clearScreen();
		} else if (ans.startsWith("n")) {
			return;
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