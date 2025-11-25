/**
 * This project takes in a character input from the user which specifies whether we have to encode, decode or quit. And it takes the * text file as inputs from which to be readed and to which to be written to the ENCODED text or DECODED text which follows a given
 * number of shift.
 * 
 * @author Lokesh Narasimhan
 * @version 11/25/2025
 */

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class SubstitutionCipher {

    /**
     * Private constants used to shift characters for the substitution cipher.
     */
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";

    /**
     * Constructs a new String where each letter in the String input is shifted
     * by the amount shift to the right, preserving whether the original
     * character was uppercase or lowercase. For example, the String "ABC" with
     * shift 3 would cause this method to return "DEF". A negative value should
     * shift to the left. For example, the String "ABC" with shift -3 would
     * cause this method to return "XYZ". Punctuation, numbers, whitespace and
     * other non-letter characters should be left unchanged. NOTE: For full
     * credit you are REQUIRED to use a StringBuilder to build the String in
     * this method rather than using String concatenation.
     *
     * @param input
     *            String to be encrypted
     * @param shift
     *            Amount to shift each character of input to the right
     * @return the encrypted String as outlined above
     */

    public static String shift(String input, int shift) {

        StringBuilder sb = new StringBuilder(input); // creates a stringbuilder object for the inputted string
        StringBuilder encodedString = new StringBuilder(); // creates a Stringbuilder object for returning stringbuilder

        // checks if the shift is positive
        if ( shift >= 0 ){

            // for-loop to parse through the stringbuilder
            for (int i = 0; i < sb.length(); i++){

                char charInString = sb.charAt(i); // extracts the character at the index i;

                // checks if the character at index i is uppercase
                if ( Character.isUpperCase(charInString) ){

                    int uppercaseIndex = UPPERCASE.indexOf(charInString); // finds the index of the character in the UPPERCASE string

                    // checks if the index+shift value is less than 26
                    if ( (uppercaseIndex+shift) < 26){

                        char encodedUpperCaseCharacter = UPPERCASE.charAt(uppercaseIndex+shift); // extracts the encoded character
                        encodedString.append(encodedUpperCaseCharacter); // appends the encoded character to the stringbuilder object
                    
                    }

                    else {

                        char encodedUpperCaseCharacter = UPPERCASE.charAt((uppercaseIndex+shift)%26); // extracts the encoded character based on the modulo operator
                        encodedString.append(encodedUpperCaseCharacter); // appends it to the stringbuilder object

                    }

                }

                // checks if the character at index i is lowercase
                else if ( Character.isLowerCase(charInString) ){
                    
                    int lowerCaseIndex = LOWERCASE.indexOf(charInString);

                    if ( (lowerCaseIndex+shift) < 26){

                        char encodedLowerCaseCharacter = LOWERCASE.charAt(lowerCaseIndex+shift);
                        encodedString.append(encodedLowerCaseCharacter);
                    
                    }

                    else {

                        char encodedLowerCaseCharacter = LOWERCASE.charAt((lowerCaseIndex+shift)%26);
                        encodedString.append(encodedLowerCaseCharacter);
                        
                    }

                }

                // checks if the character at index i is a whitespace or a number
                else {

                    encodedString.append(charInString);

                }

            }

        }

        // condition for negative shifts
        else if ( shift < 0 ){

            for (int i = 0; i < sb.length(); i++){

                char charInString = sb.charAt(i); // extracts the character at the index i;

                // checks if the character at index i is uppercase
                if ( Character.isUpperCase(charInString) ){

                    int uppercaseIndex = UPPERCASE.indexOf(charInString); // gets the uppercase Index

                    // checks if the sum (actually difference as shift is negative) is greater than 0
                    if ( (uppercaseIndex+shift) > 0){

                        char encodedUpperCaseCharacter = UPPERCASE.charAt(uppercaseIndex+shift); 
                        encodedString.append(encodedUpperCaseCharacter);
                    
                    }

                    else {

                        char encodedUpperCaseCharacter = UPPERCASE.charAt((uppercaseIndex+shift)%26);
                        encodedString.append(encodedUpperCaseCharacter);

                    }

                }

                // checks if the character at index i is lowercase
                else if ( Character.isLowerCase(charInString) ){
                    
                    int lowerCaseIndex = LOWERCASE.indexOf(charInString);

                    if ( (lowerCaseIndex+shift) > 0){

                        char encodedLowerCaseCharacter = LOWERCASE.charAt(lowerCaseIndex+shift);
                        encodedString.append(encodedLowerCaseCharacter);
                    
                    }

                    else {

                        char encodedLowerCaseCharacter = LOWERCASE.charAt((lowerCaseIndex+shift)%26);
                        encodedString.append(encodedLowerCaseCharacter);
                        
                    }

                }

                else {

                    encodedString.append(charInString);

                }

            }
            
        }

        // finally convert the stringbuilder to string and then return the value
        return encodedString.toString();

    }

    /**
     * Displays the message "promptMsg" to the user and reads the next full line
     * that the user enters. If the user enters an empty string, reports the
     * error message "ERROR! Empty Input Not Allowed!" and then loops,
     * repeatedly prompting them with "promptMsg" to enter a new string until
     * the user enters a non-empty String
     *
     * @param in
     *            Scanner to read user input from
     * @param promptMsg
     *            Message to display to user to prompt them for input
     * @return the String entered by the user
     */
    public static String promptForString(Scanner in, String promptMsg) {
        
        System.out.print(promptMsg);

        while ((in.nextLine()).length()<=0){
            System.out.println("ERROR! Empty Input Not Allowed!");
            String message = in.nextLine();
        }

        return in.nextLine();

    }

    /**
     * Opens the file inFile for reading and the file outFile for writing,
     * reading one line at a time from inFile, shifting it the number of
     * characters given by "shift" and writing that line to outFile. If an
     * exception occurs, must report the error message: "ERROR! File inFile not
     * found or cannot write to outFile" where "inFile" and "outFile" are the
     * filenames given as parameters.
     *
     * @param inFile
     *            the file to be transformed
     * @param outFile
     *            the file to write the transformed output to
     * @param shift
     *            the amount to shift the characters from inFile by
     * @return false if an exception occurs and the error message is written,
     *         otherwise true
     */
    public static boolean transformFile(String inFile, String outFile, int shift) {
        
        // use a try block to create BufferedReader and BufferedWriter objects which hold the input file and output file respectively
        try (BufferedReader reader = new BufferedReader(new FileReader(inFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(outFile))){

            // use a while loop to parse through the input file until it is empty
            while ( (reader.readLine()) != null ){

                String line = reader.readLine(); // reads a line from the input file

                String encodedLine = shift(line, shift); // calls the shift() method which encodes the character according to the shift value

                writer.write(encodedLine); // writes the encoded line to the output file
                writer.write("\n");
            }
            
        } catch (Exception e) {
            System.out.println("File " + inFile + " not found or cannot write to " + outFile);
            return false;
        }

        return true;
    }

    /**
     * Prompts the user to enter a single character choice. The only allowable
     * values are 'E', 'D' or 'Q'. All other values are invalid, including all
     * values longer than one character in length, however the user is allowed
     * to enter values in either lower or upper case. If the user enters an
     * invalid value, the method displays the error message "ERROR! Enter a
     * valid value!" and then prompts the user repeatedly until a valid value is
     * entered. Returns a single uppercase character representing the user's
     * choice.
     *
     * @param in
     *            Scanner to read user choices from
     * @return the user's choice as an uppercase character
     */
    public static char getChoice(Scanner in) {

        System.out.print("Enter your choice: ");

        while ( !(in.nextLine().equalsIgnoreCase("E")) || !(in.nextLine().equalsIgnoreCase("D")) || !(in.nextLine().equalsIgnoreCase("Q")) || (in.nextLine().length()>1)){

            System.out.println("ERROR! Enter a valid value!");
            String choice = in.nextLine();
            char charChoice = choice.charAt(0);

        }

        return 0;
    }

    /**
     * Displays the menu of choices to the user.
     */
    public static void displayMenu() {
        System.out.println("[E]ncode a file");
        System.out.println("[D]ecode a file");
        System.out.println("[Q]uit");
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        displayMenu();

        // TODO - complete this procedure with your own implementation

        in.close();
    }

}