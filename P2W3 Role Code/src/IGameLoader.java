import java.io.FileNotFoundException;
import java.util.List;

/**
 * Instances of this interface can be used to load game data from a XML file.
 */
public interface IGameLoader {
   
    /**
     * This method loads the list of games from a XML file.
     * @param filepathToXML path to the XML file relative to the executable
     * @return a list of game objects
     * @throws FileNotFoundException
     */
    List<IGame> loadGames(String filepathToXML) throws FileNotFoundException;

}
