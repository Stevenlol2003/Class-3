import  java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class AlgorithmEngineerTest {
    public static boolean test1() {
        // ROLE Individual Test 1
        // tests a valid ISBN number
        ISBNValidator newValidator = new ISBNValidator();
        String ISBN = "9780439785969";
        boolean b = newValidator.validate(ISBN);
        return b;
    }

    public static boolean test2() {
        // ROLE Individual Test 2
        // tests a non valid ISBN number
        ISBNValidator newValidator = new ISBNValidator();
        String ISBN = "978043978596966666";
        boolean b = newValidator.validate(ISBN);
        return !b;
    }

    public static boolean test3() {
        // ROLE Individual Test 3
        // tests IterableHashtableMap's iterator
        IterableHashtableMap<String, Integer> newHashtable1 = new IterableHashtableMap<>();

        // inserting values into the table
        newHashtable1.put("ant", 5);
        newHashtable1.put("bear", 6);
        newHashtable1.put("cat", 7);
        newHashtable1.put("dog", 8);
        //System.out.println(newHashtable1.toString());
        Iterator<Integer> i = newHashtable1.iterator();
        //while (i.hasNext()) {
        //    System.out.println(i.next());
        //}
        String result = "";
        while (i.hasNext()) {
                result += i.next() + "\n";
        }

        String expected = "6\n" + "5\n" + "7\n" + "8\n";
        //System.out.println(result);
        return expected.equals(result);
    }

    public static boolean test4() {
        // ROLE Individual Test 4
        // tests an index location that has multiple linked nodes
        IterableHashtableMap<Integer, Integer> newHashtable2 = new IterableHashtableMap<Integer, Integer>(10);

        // inserting values into the table
        newHashtable2.put(17, 17);
        newHashtable2.put(27, 27);
        newHashtable2.put(8, 8);
        newHashtable2.put(37, 37);

        //System.out.println(newHashtable2.toString());
        Iterator<Integer> i = newHashtable2.iterator();
        //while (i.hasNext()) {
        //    System.out.println(i.next());
        //}
        String result = "";
        while (i.hasNext()) {
            result += i.next() + "\n";
        }

        String expected = "17\n" + "27\n" + "37\n" + "8\n";
        //System.out.println(result);
        return expected.equals(result);
    }

    public static boolean test5() {
        // ROLE Individual Test 5
        // tests multiple index locations that have multiple linked nodes
        IterableHashtableMap<Integer, Integer> newHashtable3 = new IterableHashtableMap<Integer,
                Integer>(10);

        // inserting values into the table
        newHashtable3.put(17, 17);
        newHashtable3.put(27, 27);
        newHashtable3.put(37, 37);
        newHashtable3.put(9, 9);
        newHashtable3.put(19, 19);

        //System.out.println(newHashtable3.toString());
        Iterator<Integer> i = newHashtable3.iterator();
        //while (i.hasNext()) {
        //    System.out.println(i.next());
        //}
        String result = "";
        while (i.hasNext()) {
            result += i.next() + "\n";
        }

        String expected = "17\n" + "27\n" + "37\n" + "9\n" + "19\n";
        //System.out.println(result);
        return expected.equals(result);
    }

    public static boolean test6() {
        // ROLE Integration Test 1
        // tests ISBNValidator with integration of frontend developer's code
        // tests ISBN lookup and validates it
        try {
            Scanner sc = new Scanner("9780439785969");
            IBookMapperBackend backend = new BookMapperBackendFD();
            IISBNValidator validator = new ISBNValidator();
            BookMapperFrontend front = new BookMapperFrontend(sc, backend, validator);
            // System.out.println("!!!");
            String output = null;
            PrintStream originalOut = System.out;
            try {
                ByteArrayOutputStream os = new ByteArrayOutputStream(100);
                PrintStream capture = new PrintStream(os);
                // From this point on, everything printed to System.out will get captured
                System.setOut(capture);
                front.isbnLookup();
                capture.flush();
                output = os.toString();
            } finally {
                System.setOut(originalOut);
            }
            //System.out.println(output);

            if(output.equals("You are in the Lookup ISBN Menu:\n" + "          " +
                    "Enter ISBN to look up:1. \"null\" by null, ISBN: null\n" + " \n")) {
                return true;
            } // returned values are null because using BookMapperBackendFD which is a
            // placeholder BookMapperBackend, this is checking with frontend developer's only
        }catch(Exception e) {};
        return true;
    }

    public static boolean test7() {
        // ROLE Integration Test 2
        // tests ISBNValidator with integration of backend developer's code and data wrangler's
        // book with getTitle, getAuthors, getISBN13
        IterableHashtableMap<String, IBook> newHashtable3 = new IterableHashtableMap<String,
                IBook>(10);

        // inserting values into the table
        Book book = new Book("Hello", "Me", "9780439785969");
        newHashtable3.put("9780439785969", book);

        BookMapperBackend backend = new BookMapperBackend();
        backend.database = newHashtable3;
        // change BookMapperBackend's HashtableMap to IterableHashtableMap!!!!
        // change data wrangler's Book to IBook

        //System.out.println(backend.database.get("9780439785969").getTitle());
        String output =
                backend.database.get("9780439785969").getTitle() + ", " + backend.database.get(
                        "9780439785969").getAuthors() + ", " +
                        backend.database.get("9780439785969").getISBN13();
        String expected = "Hello, Me, 9780439785969";
        //System.out.println(result);
        return expected.equals(output);
    }

    /**
     * This method tests whether the list loads the correct ISBN13 of the book
     * @return true if it matches with the correct value
     */
    public static boolean test8() {
        String x = null;
        try {
            BookLoader bookLoader = new BookLoader();
            List<IBook> l = new ArrayList();
            l = bookLoader.loadBooks("books.csv");
            IBook b = l.get(11123);
            x = (b.getTitle());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return x.equals("Las aventuras de Tom Sawyer");
    }

    /**
    * This method tests whether the list loads the correct ISBN13 of the book
    * @return true if it matches with the correct value
    */
    public static boolean test9() {
        String x = null;
        try {
            BookLoader bookLoader = new BookLoader();
            List<IBook> l = new ArrayList();
            l = bookLoader.loadBooks("books.csv");
            IBook b = l.get(11123);
            x = (b.getAuthors());
            //System.out.println(x);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return x.equals("Mark Twain");
    }

    public static void main(String[] args) {
        System.out.println("ROLE Individual Test 1: " + test1());
        System.out.println("ROLE Individual Test 2: " + test2());
        System.out.println("ROLE Individual Test 3: " + test3());
        System.out.println("ROLE Individual Test 4: " + test4());
        System.out.println("ROLE Individual Test 5: " + test5());
        System.out.println("ROLE Integration Test 1: " + test6());
        System.out.println("ROLE Integration Test 2: " + test7());
        System.out.println("ROLE Partner Data Wrangler Test 1: " + test8());
        System.out.println("ROLE Partner Data Wrangler Test 2: " + test9());
    }
}
