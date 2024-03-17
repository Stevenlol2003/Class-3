import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class DataWranglerTest1 {
        public static void main (String[] args){}

        /**
        * This method tests whether the list loads the correct ISBN13 of the book 
        * @return true if it matches with the correct value
        */
        public static boolean test1() {
                String x = null;
                try {
                        BookLoader bookLoader = new BookLoader();
                        List<IBook> l = new ArrayList();
                        l = bookLoader.loadBooks("books.csv");
                        IBook b = l.get(9);
                        x = (b.getISBN13());
                } catch (FileNotFoundException e) {
                        e.printStackTrace();
                }
                return x.equals("9.7814E+12");
        }

        /**
        * This method tests whether the list loads the correct authors of the book
        * @return true if it matches with the correct value
        */
        public static boolean test2() {
                String x = null;
                try {
                        BookLoader bookLoader = new BookLoader();
                        List<IBook> l = new ArrayList();
                        l = bookLoader.loadBooks("books.csv");
                        IBook b = l.get(5);
                        x = (b.getAuthors());
                } catch (FileNotFoundException e) {
                        e.printStackTrace();
                }
                return x.equals("W. Frederick Zimmerman");
        }
        
        /**
         * This method tests whether the list loads the correct title of the book
         * @return true if it matches with the correct value
         */
         public static boolean test3() {
                 String x = null;
                 try {
                         BookLoader bookLoader = new BookLoader();
                         List<IBook> l = new ArrayList();
                         l = bookLoader.loadBooks("books.csv");
                         IBook b = l.get(16);
                         x = (b.getTitle());
                 } catch (FileNotFoundException e) {
                         e.printStackTrace();
                 }
                 return x.equals("I'm a Stranger Here Myself: Notes on Returning to America After Twenty Years Away");
         }

         /**
         * This method tests whether the list loads the correct authors of the book
         * @return true if it matches with the correct value
         */
         public static boolean test4() {
                 String x = null;
                 try {
                         BookLoader bookLoader = new BookLoader();
                         List<IBook> l = new ArrayList();
                         l = bookLoader.loadBooks("books.csv");
                         IBook b = l.get(542);
                         x = (b.getAuthors());
                 } catch (FileNotFoundException e) {
                         e.printStackTrace();
                 }
                 return x.equals("Ovid/William Scovil Anderson");
         }

         /**
         * This method tests whether the list loads the correct title of the book
         * @return true if it matches with the correct value
         */
         public static boolean test5() {
                 String x = null;
                 try {
                         BookLoader bookLoader = new BookLoader();
                         List<IBook> l = new ArrayList();
                         l = bookLoader.loadBooks("books.csv");
                         IBook b = l.get(1000);
                         x = (b.getTitle());
                 } catch (FileNotFoundException e) {
                         e.printStackTrace();
                 }
                 return x.equals("Three Complete Novels: Tim/An Indecent Obsession/The Ladies of Missalonghi");
         }

 }

