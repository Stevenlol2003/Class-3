public class Game implements IGame{
    private String name;
    private String year;
    private String genre;
    private String publisher;

    public Game() {
        this.name = null;
        this.year = null;
        this.genre = null;
        this.publisher = null;
    }

    public Game(String name, String year, String genre, String publisher) {
        this.name = name;
        this.year = year;
        this.genre = genre;
        this.publisher = publisher;
    }


    /**
     * Returns the name of the game.
     * @return name of the game
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a string that contains the publisher of the game
     * as a single string
     * @return publisher names as single string
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Returns the creation year of this game
     * @return creation year of game
     */
    public String getYear() {
        return year;
    }


    /**
     * Returns the gengre of this game
     * @return genre of game
     */
    public String getGenre() {
        return genre;
    }


}
