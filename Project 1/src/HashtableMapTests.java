import java.util.Arrays;
import java.util.NoSuchElementException;

public class HashtableMapTests {
    public static boolean test1() {
        // tests a successful implementation
        HashtableMap<String, Integer> newHashtable1 = new HashtableMap<String, Integer>();

        // inserting values into the table
        newHashtable1.put("ant", 5);
        newHashtable1.put("bear", 6);
        newHashtable1.put("cat", 7);
        newHashtable1.put("dog", 8);

        // check to see if hash table is printed out correctly
        System.out.println(newHashtable1.toString());
        return newHashtable1.toString().equals("bear = 6\nant = 5\ncat = 7\ndog = 8\n");
    }

    public static boolean test2() {
        // tests removing a key
        HashtableMap<String, Integer> newHashtable2 = new HashtableMap<String, Integer>();

        // Inserting values into the table
        newHashtable2.put("ant", 5);
        newHashtable2.put("bear",6);
        newHashtable2.put("cat", 7);
        newHashtable2.put("dog", 8);

        // print out hash table before removing a key
        System.out.println(newHashtable2.toString());


        // checks to see if containsKey method works
        System.out.println(newHashtable2.containsKey("cat"));

        // print out hash table after removing a key
        System.out.println(newHashtable2.remove("cat"));

        // print out new hash table
        System.out.println(newHashtable2.toString());

        return newHashtable2.toString().equals("bear = 6\nant = 5\ndog = 8\n");
    }

    public static boolean test3() {
        // tests getting a non existing key
        HashtableMap<Integer, Integer> newHashtable3 = new HashtableMap<Integer, Integer>(10);

        // inserting values into the table
        newHashtable3.put(17, 17);
        newHashtable3.put(28, 28);
        newHashtable3.put(77, 77);

        // this should catch the exception
        try {
            System.out.println(newHashtable3.get(27));
            return false;
        } catch (NoSuchElementException e) {
            System.out.println("Error caught");
            return true;
        }
    }

    public static boolean test4() {
        // tests resize
        HashtableMap<Integer, Integer> newHashtable4 = new HashtableMap<Integer, Integer>(8);
        newHashtable4.put(1, 1);
        newHashtable4.put(2, 2);
        newHashtable4.put(3, 3);
        newHashtable4.put(4, 4);
        newHashtable4.put(5, 5);
        newHashtable4.put(6, 6);
        System.out.println(newHashtable4.toString());
        System.out.println("Capacity: " + newHashtable4.getCapacity());
        System.out.println("Item Count: " + newHashtable4.getItemCount());

        return newHashtable4.getCapacity() == 16;
    }

    public static boolean test5() {
        // tests removing a key from the middle of a linked list
        HashtableMap<Integer, Integer> newHashtable5 = new HashtableMap<Integer, Integer>(10);

        // inserting values into the table
        newHashtable5.put(17, 17);
        newHashtable5.put(28, 28);
        newHashtable5.put(37, 37);
        newHashtable5.put(47, 47);

        newHashtable5.remove(37);
        System.out.println(newHashtable5.toString());
        return newHashtable5.toString().equals("17 = 17\n47 = 47\n28 = 28\n");
    }

    public static void main(String[] args) {
        System.out.println("test1(): " + test1());
        System.out.println("test2(): " + test2());
        System.out.println("test3(): " + test3());
        System.out.println("test4(): " + test4());
        System.out.println("test5(): " + test5());
    }
}
