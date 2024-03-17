public interface IGameFrontEnd {
	
    /**
     * Constructor of front-end Game library class, assign value to each private field
     * 
     * @param userInputScanner read user input
     * @param backend read the back-end game library
     */
     //GameLibraryFrontend(Scanner userInputScanner, IGameBackend back-end);

    /**
     * This method starts the command loop for the front-end, and will
     * terminate when the user exits the application.
     */
    public void runCommandLoop();

    // to help make it easier to test the functionality of this program, 
    // the following helper methods will help support runCommandLoop():

    /**
     * Prints command options to the screen (System.out)
     */
    public void displayMainMenu();
    
    /**
     * Display a list of games
     * @param games the game list
     */
    public void displayGames(IGame games);
    
    /**
     * Reads game names from user input (System.in), displays searching results
     */
    public void nameSearch();
}