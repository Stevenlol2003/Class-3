public interface IGameBackend {
    /**
     * Adds a new game to the Backend's database and is stored in the Red-Black
     * tree.
     * 
     * @param game the game to add
     */
    public boolean addGame(String name, String year, String genre, String publisher);

    /**
     * Deletes a game from the database of the Red-Black tree.
     * 
     * @param game to remove
     */
    public boolean removeGame(String name);

    /**
     * @param data the game data to find
     * @return true if data was found, false otherwise
     */
    public IGame searchByName(String name);


}
