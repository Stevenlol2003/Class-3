import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.io. *;
import java.util.Scanner;

public class GameLoader implements  IGameLoader{
    /**
     * This method loads the list of games from a XML file.
     * @param filepathToXML path to the XML file relative to the executable
     * @return a list of game objects
     * @throws FileNotFoundException if nile is not found
     */
    @Override
    // @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<IGame> loadGames(String filepathToXML) throws FileNotFoundException {
        List<IGame> list = new ArrayList();  //generate new list for the return

        Scanner sc = new Scanner(new FileInputStream(filepathToXML), "UTF-8"); //scanner
        // for reading the file encoded in UTF-8

        sc.nextLine(); //skipping the first row of the XML file's <Games>

        try {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();

                if (line.equals("</Games>")) {
                    break; // last line of XML, stop loop
                }

                String[] temp = line.split("\""); // delimiter and store as array

                String name = temp[1]; // store the value for name
                String year = temp[3]; // store the value for year
                String genre = temp[5]; // store the value for genre
                String publisher = temp[7]; // store the value for publisher

                Game tempGame = new Game(name, year, genre, publisher); // safes the new values
                // into a game object
                list.add(tempGame); // add the created game object
                // System.out.println(list.get(0).getName());
            }
        } catch (Exception e) { e.printStackTrace(); }
        sc.close(); //closes the scanner

        return list;
    }
}
