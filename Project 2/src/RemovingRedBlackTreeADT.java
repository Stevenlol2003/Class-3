/**
 * This interface extends Comparable, and have functions of remove, insert, and contains methods
 * for RB class.
 */
public interface RemovingRedBlackTreeADT <T extends Comparable>{

        /**
        * Removes a key and its value from the map.
        *
        * @param data the game data to be removed
        * @return true if the node was deleted, false if it couldn't be found
        *        
        */
        public boolean remove(String name);
         
        /**
        * @param data the game data to be added
        * @return true if data was successfully inserted, false otherwise
        */
        public boolean insert(T data);
         
        /**
        * @param data the game data to find
        * @return true if data was found, false otherwise
        */
        public boolean contains(T data);

        /**
         *
         * Find the game
         *
         * @param gameName the game data to find
         * @return true if data was found, false otherwise
         */
        public T find(String gameName);

}
