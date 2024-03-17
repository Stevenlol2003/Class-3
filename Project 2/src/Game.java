public class Game implements Comparable<IGame>, IGame{
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


    @Override
    public int compareTo(IGame o) {

        // comapre by game name
        if(this.name.compareTo(o.getName()) > 0) {
            return 1;
        }

        if(this.name.compareTo(o.getName()) < 0) {
            return -1;
        }

        // comapre by year
        if(this.year.compareTo(o.getYear()) > 0) {
            return 1;
        }

        if(this.year.compareTo(o.getYear()) < 0) {
            return -1;
        }

        // compare by author
        if(this.publisher.compareTo(o.getPublisher()) > 0) {
            return 1;
        }

        if(this.publisher.compareTo(o.getPublisher()) < 0) {
            return -1;
        }

        // compare by genre
        if(this.genre.compareTo(o.getGenre()) > 0) {
            return 1;
        }

        if(this.genre.compareTo(o.getGenre()) < 0) {
            return -1;
        }

        return 0; // same
    }

}
