import java.security.Key;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class HashtableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType>{
    private int itemCount; // number of items
    private int capacity;
    private double loadFactor = 0.7;

    // singly linked list, based off CS300 P08 DNA Transcription
    public class HashtableNode<KeyType, ValueType> {
        private KeyType key;
        private ValueType value;
        private HashtableNode<KeyType, ValueType> next;
        private HashtableNode<KeyType, ValueType> prev;

        public HashtableNode(KeyType key, ValueType value) {
            this.key = key;
            this.value = value;
        }

        public KeyType getKey() {
            return key;
        }

        public ValueType getValue() {
            return value;
        }

        public HashtableNode<KeyType,ValueType> getNext() { return next; }
    }

    public int getCapacity() { return capacity; } // for test cases
    public int getItemCount() { return itemCount; } // for test cases

    protected HashtableNode<KeyType, ValueType>[] hashtableArray;

    // constructor
    public HashtableMap(int capacity) {
        this.capacity = capacity;
        hashtableArray = new HashtableNode[capacity];
        for (int i = 0; i < capacity; i++) {
            hashtableArray[i] = null;
        }
    }

    // constructor
    public HashtableMap() {
        this(15);
    }

    // converts key to a hash code
    private int getHashCode(KeyType key) {
        return key.hashCode() % capacity;
    }

    // used to double hash table size if number of items reaches the loadFactor
    private void resize() {
        double currentLoadFactor = (double) itemCount / capacity;
        if (currentLoadFactor >= loadFactor) {
            int newCapacity = capacity * 2;
            capacity = newCapacity;
            HashtableNode<KeyType, ValueType>[] newHashtableArray = new HashtableNode[newCapacity];
            HashtableNode<KeyType, ValueType>[] tempHashtableArray = hashtableArray;
            hashtableArray = newHashtableArray;
            for (int i = 0; i < capacity; ++i) {
                HashtableNode temp = tempHashtableArray[i];

                if (temp != null) {
                    KeyType key1 = (KeyType) temp.getKey();
                    ValueType value1 = (ValueType) temp.getValue();
                    put(key1, value1);
                    // go through this linked list to the end
                    while (temp.next != null) {
                        temp = temp.next;
                        KeyType key2 = (KeyType) temp.getKey();
                        ValueType value2 = (ValueType) temp.getValue();
                        put(key2, value2);
                    }
                }
            }
        }
    }

    /**
     * Inserts a new (key, value) pair into the map if the map does not
     * contain a value mapped to key yet.
     *
     * @param key the key of the (key, value) pair to store
     * @param value the value that the key will map to
     * @return true if the (key, value) pair was inserted into the map,
     *         false if a mapping for key already exists and the
     *         new (key, value) pair could not be inserted
     */
    public boolean put(KeyType key, ValueType value) {
        // if key is null or duplicate key return false, hashtable by default is null when empty
        if (key == null || containsKey(key)) {
            return false;
        }

        int index = getHashCode(key);

        HashtableNode newNode = new HashtableNode(key, value);

        // if index had nothing before (no collision), just add it
        if (hashtableArray[index] == null) {
            hashtableArray[index] = newNode;
            itemCount++;
            resize();
            return true;
        }
        // collision handle
        else if (hashtableArray[index] != null) {
            HashtableNode temp = hashtableArray[index];
            // go to the end of linked list
            while (temp.next != null) {
                temp = temp.next;
            }
            // add at the end of linked list
            temp.next = newNode;
            itemCount++;
            resize();
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Returns the value mapped to a key if the map contains such a mapping.
     *
     * @param key the key for which to look up the value
     * @return the value mapped to the key
     * @throws NoSuchElementException if the map does not contain a mapping
     *                                for the key
     */
    public ValueType get(KeyType key) throws NoSuchElementException {
        // check if key is null or if key exists already
        if (key == null || !containsKey(key)) {
            throw new NoSuchElementException();
        }

       int index = getHashCode(key);

       HashtableNode temp = hashtableArray[index];

       if (temp != null) {
           // go through this linked list to the end
           while (!temp.getKey().equals(key) && temp.next != null) {
               temp = temp.next;
           }
           return (ValueType) temp.getValue();
       }
       else {
           throw new NoSuchElementException();
       }
    }

    /**
     * Removes a key and its value from the map.
     *
     * @param key the key for the (key, value) pair to remove
     * @return the value for the (key, value) pair that was removed,
     *         or null if the map did not contain a mapping for key
     */
    public ValueType remove(KeyType key) {
        // check if key is null or if the key exists
        if (key == null || !containsKey(key))
        {
            return null;
        }
        int hashCode = getHashCode(key);
        if (hashtableArray[hashCode].getKey().equals(key))
        {
            ValueType returnedValue = hashtableArray[hashCode].getValue();
            hashtableArray[hashCode] = hashtableArray[hashCode].next;
            itemCount--;
            return returnedValue;
        }
        else
        {
            HashtableNode<KeyType, ValueType> curr = hashtableArray[hashCode];
            while (curr.next != null)
            {
                if (curr.next.getKey().equals(key))
                {
                    ValueType toReturn = curr.next.getValue();
                    curr.next = curr.next.next;
                    itemCount--;
                    return toReturn;
                }
                else
                {
                    curr = curr.next;
                }
            }
        }
        return null;
    }

    /**
     * Checks if a key is stored in the map.
     *
     * @param key the key to check for
     * @return true if the key is stored (mapped to a value) by the map
     *         and false otherwise
     */
    public boolean containsKey(KeyType key) {
        int index = getHashCode(key);

        for (int i = 0; i < capacity; ++i) {
            HashtableNode temp = hashtableArray[i];

            if (temp != null) {
                if (temp.getKey().equals(key)) {
                    return true;
                }
                // go through this linked list to the end
                while (temp.next != null) {
                    temp = temp.next;
                    if (temp.getKey().equals(key)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Returns the number of (key, value) pairs stored in the map.
     *
     * @return the number of (key, value) pairs stored in the map
     */
    public int size() {
        return itemCount;
    }

    /**
     * Removes all (key, value) pairs from the map.
     */
    public void clear() {
        hashtableArray = new HashtableNode[capacity];
        itemCount = 0;
    }


    // toString method for tests
    @Override
    public String toString() {
        String toReturn = "";

        for (int i = 0; i < capacity; ++i) {
            HashtableNode temp = hashtableArray[i];

            if (temp != null) {
                toReturn += temp.getKey() + " = " + temp.getValue() + "\n";
                // go through this linked list to the end
                while (temp.next != null) {
                    temp = temp.next;
                    toReturn += temp.getKey() + " = " + temp.getValue() + "\n";
                }
            }
        }

        return toReturn;
    }
}
