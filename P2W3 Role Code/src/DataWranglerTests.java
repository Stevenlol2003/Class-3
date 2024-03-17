import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DataWranglerTests {
    @Test
    // tests returning the 10th game's name in the XML file
    public void test1() {
        String result = null;
        try {
            GameLoader gameLoader = new GameLoader();
            List<IGame> gameList = new ArrayList();
            gameList = gameLoader.loadGames("Games.xml");
            result = gameList.get(9).getName();
            // System.out.println(result);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assertTrue(result.equals("Nintendogs"), "something is wrong for test1");
    }


    @Test
    // tests returning the 50th game's year in the XML file
    public void test2() {
        String result = null;
        try {
            GameLoader gameLoader = new GameLoader();
            List<IGame> gameList = new ArrayList();
            gameList = gameLoader.loadGames("Games.xml");
            result = gameList.get(49).getYear();
            // System.out.println(result);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assertTrue(result.equals("1993"), "something is wrong for test2");
    }

    @Test
    // tests returning the 88th game's genre in the XML file
    public void test3() {
        String result = null;
        try {
            GameLoader gameLoader = new GameLoader();
            List<IGame> gameList = new ArrayList();
            gameList = gameLoader.loadGames("Games.xml");
            result = gameList.get(87).getGenre();
            // System.out.println(result);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assertTrue(result.equals("Fighting"), "something is wrong for test3");
    }

    @Test
    // tests returning the 150th game's publisher in the XML file
    public void test4() {
        String result = null;
        try {
            GameLoader gameLoader = new GameLoader();
            List<IGame> gameList = new ArrayList();
            gameList = gameLoader.loadGames("Games.xml");
            result = gameList.get(149).getPublisher();
            // System.out.println(result);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assertTrue(result.equals("Universal Interactive"), "something is wrong for test4");
    }

    @Test
    // tests returning the 172nd game's full information in the XML file
    public void test5() {
        String result = null;
        try {
            GameLoader gameLoader = new GameLoader();
            List<IGame> gameList = new ArrayList();
            gameList = gameLoader.loadGames("Games.xml");
            result = gameList.get(171).getName() + " --- " + gameList.get(171).getYear() + " --- "
                    + gameList.get (171).getGenre() + " --- " + gameList.get(171).getPublisher();
            // System.out.println(result);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assertTrue(result.equals("Resident Evil 5 --- 2009 --- Action --- Capcom"),
                "something is wrong for test5");
    }

    @Test
    public void integrationTest1() {

    }

    @Test
    public void integrationTest2() {

    }

    @Test
    public void CodeReviewOfFrontendDeveloper1() {

    }

    @Test
    public void CodeReviewOfFrontendDeveloper2() {

    }

    public static void main(String[] args) {
        /**
        try {
            GameLoader gameLoader = new GameLoader();
            List<IGame> gameList = new ArrayList();
            gameList = gameLoader.loadGames("Games.xml");
            String list = "";
            for (int i = 0; i < gameList.size(); ++i) {
                list += gameList.get(i).getName() + " --- " + gameList.get(i).getYear() + " --- " +
                        gameList.get(i).getGenre() + " --- " + gameList.get(i).getPublisher() +
                        "\n";
            }
            System.out.println(list);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
         **/
    }
}
