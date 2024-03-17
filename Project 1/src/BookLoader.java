import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.io. * ;
import java.util.Scanner;

public class BookLoader implements IBookLoader{
        /**
     * This method loads the list of books from a CSV file.
     * @param filepathToCSV path to the CSV file relative to the executable
     * @return a list of book objects
     * @throws FileNotFoundException
     */
        @SuppressWarnings({ "unchecked", "rawtypes" })
        public List<IBook> loadBooks(String filepathToCSV) throws FileNotFoundException {

                List<IBook> list = new ArrayList();  //generate new list for the return

                Scanner sc = new Scanner(new FileInputStream(filepathToCSV ), "UTF-8"); //scanner for reading the file encoded in UTF-8

                sc.useDelimiter(","); //setting comma as delimiter pattern
                sc.nextLine(); //skipping the first row of the csv file since it's just categories

                try {
                        while (sc.hasNextLine()) {
                                List<String> temp = new ArrayList(); //temporary list to store the values for each book
                                String l = sc.nextLine();
                                Scanner sl = new Scanner(l);
                                sl.useDelimiter(",");
                                while (sl.hasNext()) {
                                        temp.add(sl.next()); //add each value to the list 
                                }
                                String t = temp.get(1); //store the value for title
                                String a = temp.get(2); //store the value for authors
                                String i = temp.get(5); //store the value for ISBN-13
                                list.add(new Book(t, a, i)); //now transfer these values to the book and save it into the temporary list
                                sl.close();
                        }
                } catch (Exception e) { e.printStackTrace(); }
                sc.close(); //closes the scanner

                return list;
        }
}