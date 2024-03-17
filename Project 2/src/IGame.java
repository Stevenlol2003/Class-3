/**
 * This interface defines getter methods for each game's data attributes
 * and is implemented by classes that represent a game and its associated
 * data.
 */
public interface IGame {

    /**
     * Returns the name of the game.
     * @return name of the game
     */
    String getName();

    /**
     * Returns a string that contains the publisher of the game
     * as a single string
     * @return publisher names as single string
     */
    String getPublisher();

    /**
     * Returns the creation year of this game
     * @return creation year of game
     */
    String getYear();


    /**
     * Returns the gengre of this game
     * @return genre of game
     */
    String getGenre();


}