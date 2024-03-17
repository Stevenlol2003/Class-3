/**
 * this is a class that contains methods needed for front end
 *
 */
public class GameLibraryBD implements IGameBackend{
	private RemovingRedBlackTree ae = new RemovingRedBlackTree(true);

	@Override
	/**
	 * Adds a new game to the Backend's database and is stored in the Red-Black
     * tree.
     * @param name name of the game
     * @param year publish year
     * @param genre the genre of the game
     * @param publisher the publisher of the game
	 */
	public boolean addGame(String name, String publisher, String year, String genre) {
		//check if the input is valid
		if(name == null || year == null ||genre == null || publisher == null) {
			return false;
		} 
	
		if(year.length() == 4) {
			for(int i=0; i<4; i++) {
				if(!Character.isDigit(year.charAt(i))) return false;
			}
		}else {
			return false;
		} 

		IGame game = new Game(name, year, genre, publisher);

		return ae.insert((Comparable)game);
	}

	/**
     * Deletes a game from the database of the Red-Black tree.
     * 
     * @param name game to remove
     */
	@Override
	public boolean removeGame(String name) {
		if(name == null) {
			return false;
		}

		return ae.remove(name);
	}

	/**
     * Search through all the games in the database and returns the game with the name
     * if no game is found return null
     * 
     * @param name game name
     * @return game
     */
	@Override
	public IGame searchByName(String name) {
		if(name == null) {
			return null;
		}

		return (IGame)ae.find(name);
	}
	
}
