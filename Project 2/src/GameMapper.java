import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * Class with main method to run the book mapper app.
 */
public class GameMapper {

    public static void main(String[] args) throws FileNotFoundException {
        IGameLoader gameLoader = new GameLoader();
        // load the games from the data file
        List<IGame> gameList = gameLoader.loadGames("Games.xml");

        // instantiate the backend
        GameLibraryBD backend = new GameLibraryBD();
        // add all the books to the backend
        for (IGame game : gameList) {
            backend.addGame(game.getName(), game.getPublisher(), game.getYear(), game.getGenre());

        }

        backend.toString();

        // instantiate the scanner for user input
        Scanner userInputScanner = new Scanner(System.in);
        // instantiate the front end and pass references to the scanner, backend, and isbn validator to it
        GameFrontEnd frontend = new GameFrontEnd(userInputScanner, backend);
        // start the input loop of the front end
        frontend.runCommandLoop();

    }

}
