class Main {
	public static void main(String[] args) {
		User user = new User();

		user.getAmountOfWater();
	}

	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public static void GetInputs() {
		// ================================================
		// Introduction
		// ================================================
		System.out.println("Welcome to the donations System!!\n");
		System.out.println("This program is designed to help you understand what your donations are used for.");
		System.out.println("Here are the options you can chose to donate:");
		displayOptions();
		System.out.println("\n");

	}

	public static void displayOptions() {
		System.out.println("	- Food");
		System.out.println("	- Water");
		System.out.println("	- Money");
		System.out.println("	- Clothing");
		System.out.println("	- Shelter");
	}
}

// Take in inputs from the user Donations include
// - water -> in litres
// - food -> in different types of food -> calorie
// - money -> dollars
// - clothing -> pieces of clothing
// - shelters -> in square meter(square feet)