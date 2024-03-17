import java.util.NoSuchElementException;

/**
 * This class tests methods in HashtableMap. This class contains 5 methods and
 * each of the methods is to test put(), get(), containsKey(), remove(), clear()
 * methods respectively. Some methods contains tests for Constructor, size(),
 * doubleRehash(), and getIndex() as well.
 */
public class HashtableMapTests2 {

	/**
	 * This method verifies that the constructors and put method works properly in
	 * HashtableMap class and any relevant exceptions are thrown.
	 * 
	 * @return true if the test pass, or return false otherwise
	 */
	public static boolean test1() {

		try {

			// check the size and capacity
			HashtableMap<String, Integer> map = new HashtableMap<>();
			if (map.size() != 0) { // no element inside yet

				return false;
			}

			// when key is null, put method should return false
			if (map.put(null, null) != false) {
				return false;
			}

			map.put("Marin", 100); // store one element in the array, so size is 1 now
			if (map.size() != 1) {
				System.out.println(map.size());
				return false;
			}

			if (map.put("Marin", 100) != false) { // the same key is already stored.
				return false;
			}

			map.put("Backy", 50); // one element in the array
			if (map.size() != 2) {
				System.out.println(map.size());
				return false;
			}

			// check whether whether DoubleRehash works
			HashtableMap<String, Integer> map1 = new HashtableMap<>(1);// the current capacity is 1
			map1.put("Marin", 100);
			map1.put("m", 100);

		} catch (Exception e) {
			e.printStackTrace(System.out);
			return false; // unexpected error
		}

		return true; // all test passed
	}

	/**
	 * This method verifies that the constructors and get method works properly in
	 * HashtableMap class and any relevant exceptions are thrown.
	 * 
	 * @return true if the test pass, or return false otherwise
	 */
	public static boolean test2() {

		try {

			HashtableMap<String, Integer> map = new HashtableMap<>();
			map.put("Marin", 100);
			map.put("Bucky", 50);

			if (!map.get("Marin").equals(100)) {
				return false; // Marin should be in array as Key
			}

			if (!map.get("Bucky").equals(50)) {
				return false; // Bucky should be in array as Key
			}

			try {
				map.get("Suzuki").equals(100); // No Suzuki element in the array
				return false; // it should throw NotSuchElementException
			} catch (NoSuchElementException e) {
				// correct
			}

			try {
				map.get(null).equals(100);

				return false; // it should throw NotSuchElementException
			} catch (NoSuchElementException e) {
				// correct
			}
			if (map.size() != 2) { // two elements in the array now
				return false;
			}
			// check whether DoubleRehash works
			HashtableMap<String, Integer> map1 = new HashtableMap<>(1);// the current capacity is 1
			map1.put("Marin", 100);
			//System.out.println(map1.toString());
			map1.put("m", 100);
			//System.out.println(map1.toString());
			System.out.println(map1.size());
			if (map1.size() != 2) { // size should be 2
				System.out.println(map1.size());
				return false;

			}
			System.out.println("!!!");
			System.out.println(map1.toString());
			if (!map1.get("m").equals(100)) {
				System.out.println("!!!");
				System.out.println(map1.get("m"));
				return false; // m should be in array as Key
			}

		} catch (Exception e) {

			e.printStackTrace(System.out);
			return false;
		}

		return true; // all test passed;
	}

	/**
	 * This method verifies that the constructors and remove method works properly
	 * in HashtableMap class and any relevant exceptions are thrown.
	 * 
	 * @return true if the test pass, or return false otherwise
	 */
	public static boolean test3() {

		try {

			HashtableMap<String, Integer> map = new HashtableMap<>();
			map.put("Marin", 100);
			map.put("Bucky", 50);

			if (map.remove(null) != null) {
				return false;
			}

			if (map.remove("Suzuki") != null) { // No Suzuki in the array
				return false;
			}

			if (!map.remove("Bucky").equals(50)) { // Bucky is in the array
				return false;
			}

			// Bucky does not exist in array anymore
			if (map.remove("Bucky") != null) {
				return false;
			}

			if (map.size() != 1) {
				return false; // after removing, size here should be 1 now
			}

			// Marin removed correctly
			if (!map.remove("Marin").equals(100)) {
				return false;
			}

			if (map.size() != 0) {
				return false; // size here should be 1 now
			}

		} catch (Exception e) {

			e.printStackTrace(System.out);
			return false; // unexpected error

		}

		return true; // all test passed
	}

	/**
	 * This method verifies that the constructors and containsKey method works
	 * properly in HashtableMap class and any relevant exceptions are thrown.
	 * 
	 * @return true if the test pass, or return false otherwise
	 */
	public static boolean test4() {

		try {

			HashtableMap<String, Integer> map = new HashtableMap<>();
			map.put("Marin", 100);
			map.put("m", 50);

			if (map.containsKey(null) != false) {
				return false;
			}

			if (map.containsKey("Marin") == false) { // Marin is in the array
				return false;
			}

			if (map.containsKey("m") == false) { // m is in the array
				return false;
			}

			if (map.containsKey("Hehehehe") != false) { // Hehehehe does not exist in the array
				return false;
			}

			map.remove("Marin");
			if (map.containsKey("Marin") != false) { // Marin is not in the array anymore
				return false;
			}

			if (map.containsKey("m") == false) { // m should still be in the array
				return false;
			}

		} catch (Exception e) {
			// System.out.println("!!!"); // return false here for some reason
			return false;

		}

		return true; // all test passed
	}

	/**
	 * This method verifies that the constructors and clear method works properly in
	 * HashtableMap class and any relevant exceptions are thrown.
	 * 
	 * @return true if the test pass, or return false otherwise
	 */
	public static boolean test5() {

		try {

			HashtableMap<String, Integer> map = new HashtableMap<>();
			map.put("Marin", 100);
			map.put("Bucky", 50);

			map.clear(); // remove all

			if (map.size() != 0) {
				return false;
			}

			if (map.containsKey("Marin")) { // No element now
				return false;
			}

			if (map.containsKey("Bucky")) { // No element now
				return false;
			}

		} catch (Exception e) {
			return false;
		}

		return true; // all test passed
	}

	/**
	 * This main method prints the result of test1,2,3,4, and 5.
	 * 
	 */
	public static void main(String[] args) {
		System.out.println(test1());
		System.out.println(test2());
		System.out.println(test3());
		System.out.println(test4());
		System.out.println(test5());
	}
}
