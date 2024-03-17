public class ISBNValidator implements IISBNValidator {

    @Override
    public boolean validate(String ISBN) {
        if (ISBN.length() != 13) {
            return false;
        }
        char[] ISBNarray = new char[13];
        for (int i = 0; i < 13; ++i) {
            ISBNarray[i] = ISBN.charAt(i);
        }
        int sum = 0;
        for (int i = 0; i < 13; ++i) {
            int num = Integer.parseInt(String.valueOf(ISBNarray[i]));
            if (i % 2 == 0) {
                sum += num;
            }
            else {
                sum += 3 * num;
            }
        }
        return sum % 10 == 0;
    }
}
