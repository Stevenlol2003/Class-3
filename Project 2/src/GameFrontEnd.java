import java.util.Scanner;

public class GameFrontEnd implements IGameFrontEnd{
	Scanner sc;
	IGameBackend backend;
	
	/**
	 * The constructor. It takes the Scanner that will read user input as
     * a parameter as well as the backend.
     * 
	 * @param userInputScanner  The scanner of user input
	 * @param backend  the backend object for access of backend method
	 */
	public GameFrontEnd(Scanner userInputScanner, IGameBackend backend) {
		//assignment
		this.sc = userInputScanner;
		this.backend = backend;
	}
	
	/**
	 * This method starts the command loop for the frontend, and will
     * terminate when the user exists the app.
	 */
	@Override
	public void runCommandLoop() {
		//welcome message
		System.out.println("Welcome to the Game Library Application!\n"
							+ "x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x+x\n"
							+ " ");
		while(true) {
			displayMainMenu();
			String input = sc.nextLine();
			
			if(input != null && input.strip().equals("1")) nameSearch();
			
			else if(input != null && input.strip().equals("2")) {
				System.out.println("You are in the Add New Game Menu:");
				System.out.println("          Please enter the name of this new game: ");
				String name = sc.nextLine();
				System.out.println("          Please enter the publisher of this new game: ");
				String publisher = sc.nextLine();
				System.out.println("          Please enter the publish year of this new game: ");
				String year = sc.nextLine();
				System.out.println("          Please enter the genre of this new game: ");
				String genre = sc.nextLine();
				if(backend.addGame(name,publisher,year,genre)) {
					System.out.print("You have added a new game: ");
					displayGames(backend.searchByName(name));
					System.out.print("\n");
				}
				else System.out.println("Invalid Input!");
			}
			
			else if(input != null && input.strip().equals("3")) {
				System.out.println("You are in the Delete Game Menu: ");
				System.out.println("          Please enter the name of game to delete: ");
				String name = sc.nextLine();
				if(backend.removeGame(name)) {
					System.out.print("Deleted successfully!\n");
				}
				else System.out.println("Invalid Input: the game cannot be found!");
			}
			
			//terminate
			else if(input != null && input.strip().equals("4")) {
				System.out.print("Goodbye!");
				sc.close();
				return;
			}
			
			else System.out.println("Invalid Input!");
		}
	}

	/**
	 * prints command options to System.out
	 */
	@Override
	public void displayMainMenu() {
		String menu = "You are in the Main Menu:\n"
				+ "          1) Search by Name\n"
				+ "          2) Add New Game\n"
				+ "          3) Delete Game\n"
				+ "          4) Exit Application";
		System.out.println(menu);	
	}

	/**
	 * Prints out the game info
	 */
	@Override
	public void displayGames(IGame games) {
		if(games == null) {
			return;
		}
		System.out.println("\"" + games.getName() + "\"" 
				+ " published by " + games.getPublisher() 
				+ " in " + games.getYear()
				+ ", Genre: " + games.getGenre() + "\n");
	}

	/**
	 * searching game by exact word and print the info of game
	 */
	@Override
	public void nameSearch() {
		System.out.print("You are in the Search for Name Word Menu:\n"
				+ "          Enter a word to search for in game names: \n");
		String input = sc.nextLine();
		input = input.strip();

		try {
			if (!backend.searchByName(input).getName().equals(input)) {
				System.out.println("Invalid name");
				return;
			} else displayGames(backend.searchByName(input));
		}
		catch (NullPointerException e) {
			System.out.println("Invalid name");
			return;
		}
	}

}
