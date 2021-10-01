
class Main {
	static SaveScanner scan = new SaveScanner();

	public static void main(String[] args) {
		User user = new User();

		GetInputs(user);

		System.out.println("Thank for using this program.");
		scan.closeScan();
	}

	public static void GetInputs(User user) {
		boolean getInput = true;
		// ================================================
		// Introduction
		// ================================================
		System.out.println("Welcome to the donations System!!\n");
		System.out.println("This program is designed to help you understand the importance of your donations.");
		System.out.println("\n");

		do {
			displayOptions();
			String prompt = "Which donations do you want to contribute to:";
			int inputs = scan.nextInt(prompt, true);
			scan.nextLine("", false);

			switch (inputs) {
				// Sentinel value
				case -1:
					getInput = false;
					break;
				case 0:
					user.getClothing();
					break;
				default:
					System.out.println("Invalid option");
					break;
			}
			if (inputs != -1) {
				String ContinuePrompt = "Do you want to select another option(y/n)?";
				String Continue = scan.nextLine(ContinuePrompt, true);
				char c = Continue.charAt(0);

				if (c == 'n' || c == 'N') {
					clearScreen();

					break;
				}
			}
			clearScreen();

		} while (getInput);
	}

	public static void displayOptions() {
		System.out.println("  -> Clothing(0)");
		System.out.println("  -> exit(-1)");
	}

	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
}

// Take in inputs from the user Donations include
// - water -> in litres
// - food -> in different types of food -> calorie
// - money -> dollars
// - clothing -> pieces of clothing
// - shelters -> in square meter(square feet)