package cmsc256;

/**
 *  CMSC 256 - On Campus
 *  2/16/2022
 *  Hoang Tran
 */
public class RamString implements WackyStringInterface {
    private String stringRam;

    //Default
    public RamString() {
        stringRam = "Rodney, the Ram";
    }

    //Parameterized
    public RamString(String str) {
        setWackyString(str);
    }

    @Override
    public void setWackyString(String string) {
        if (string == null) {
            throw new IllegalArgumentException();
        }
        stringRam = string;
    }

    @Override
    public String getWackyString() {

        return stringRam;
    }

    /**
     * Returns a string that consists of all and only the characters
     * in every fifth positions (i.e., fifth, tenth, and so on) in
     * the current string, in the same order and with the same case as
     * in the current string. The first character in the string is
     * considered to be in Position 1.
     *
     * @return String made of characters in every third positions in the
     * current string
     */
    @Override
    public String getEveryFifthCharacter() {
        //check if length of stringRam is suitable for method
        int count = 0;
        if (stringRam.length() < 5) {
            return "";
        }
        String result = "";
        for (int i = 0; i < stringRam.length(); i++) {
            count++;
            if (count > 0 && count % 5 == 0) {
                result += stringRam.charAt(i);
            }
        }
        return result;
    }

    /**
     * Returns a string that consists of all and only the characters
     * in the even positions (i.e., second, fourth, sixth, and so on) in
     * current string, in the same order and with the same case as in
     * the current string. The first character in the string is
     * considered to be in Position 1.
     *
     * @return String made of characters in the even positions in the
     * current string
     */
    @Override
    public String getEvenCharacters() {
        //check if length of stringRam is suitable for method
        if (stringRam.length() < 2) {
            return "";
        }
        //check at every even position
        String result = "";
        for (int i = 0; i < stringRam.length(); i++) {
            if (i % 2 == 0) {
                continue;
            }
                result += stringRam.charAt(i); //store each character at even position

        }
        return result;
    }

    /**
     * Returns a string that consists of all and only the characters
     * in the odd positions (i.e., first, third, fifth, and so on) in
     * current string, in the same order and with the same case as in
     * the current string. The first character in the string is
     * considered to be in Position 1.
     *
     * @return String made of characters in odd positions in the
     * current string
     */
    @Override
    public String getOddCharacters() {
        //check if length of stringRam is suitable for method
        if (stringRam.length() < 1) {
            return "";
        }
        //check at every odd position
        String result = "";
        if (stringRam.length() == 1) {
            result += stringRam.charAt(0);
            return result;
        }
        for (int i = 0; i < stringRam.length(); i++) {
            if (i % 2 == 1) {
                continue;
            }
            result += stringRam.charAt(i); //store each character at odd position

        }
        return result;
    }

    /**
     * Returns the number of characters that are digits in the current string
     * if two (and no more than two) of the same digit appear side by side.
     *
     * @return Number of double-digits in the current string
     */
    @Override
    public int countDoubleDigits() {
        int result = 0;
        int count = 0;
        int doubleDigits = 0;

        if (stringRam.length() < 2) {
            return 0;
        }
        /*
        int tracker = 0;
        String test ="";
        String temp ="";

        for(int i = 0; i < stringRam.length(); i++) {

                //check if character is not a digit, if so break the string till the index
                if (!Character.isDigit(stringRam.charAt(i))) {
                    test = stringRam.substring(tracker, i);
                    tracker = i + 1;
                    if (test.length() == 2) {
                        result++;
                    }
                }

            }
        //only if the tracker number is smaller than the length of the length
        if(tracker < stringRam.length()) {
            //get the rest from left
            temp = stringRam.substring(tracker);
            if (temp.length() == 2) {
                result++;
            }
        }
        //123a56a

       return result;
    }
        else {
            for (int i = 0; i < stringRam.length(); i++) {
                if (Character.isDigit(stringRam.charAt(i))) {
                    try {
                        if(i==0 && stringRam.charAt(i) == stringRam.charAt(i+1) && stringRam.charAt(i) != stringRam.charAt(i+2)){
                            result++;

                        }
                        if (i > 0 && stringRam.charAt(i) == stringRam.charAt(i + 1) && stringRam.charAt(i) != stringRam.charAt(i+2) && stringRam.charAt(i) != stringRam.charAt(i-1)) {
                            result++;
                        }
                    } catch (Exception e) {
                        break;
                    }

                }
            }
            return result; } */
        {
            if (stringRam.length() > 2) {
                for(int i = 0; i <stringRam.length()-1; i++){
                    if(Character.isDigit(stringRam.charAt(i))){
                        if(stringRam.charAt(i) == stringRam.charAt(i+1)) {
                            doubleDigits += 2;
                        }

                    }
                    else{ //when it is not a digit
                        if (doubleDigits == 2) {
                            count++;
                        }
                        doubleDigits = 0;
                    }
                    //reset

                }
            }
            //case there is only numbers
            if(doubleDigits ==2){
                count++;
            }
            result = count;
        }
        return result;
    }
    /**
     * Returns the number of characters that are either 'a' 'e' 'i' 'o' 'u' or 'y'
     * in the current string regarless of case, 'A' or 'a'.
     *
     * @return Number of vowel in the current string
     */

    @Override
    public int countVowels() {
        int result = 0;
        char[] vowels = {'a','e','i','o','u','y'};
        for(int i = 0 ; i < stringRam.length(); i++){
            for (int j = 0; j < vowels.length; j++){
                char temp = Character.toLowerCase(stringRam.charAt(i));
                if(temp == vowels[j]){
                    result++;
                }
            }
        }
        return result;
    }

    /**
     * Converts a multi-word String that represents a first name and last name
     * into the format of last name, first name.
     *   For example: "Debra Duke" --> "Duke, Debra"
     * In the case of more than single word names, assume the first word is
     * the first name and all remaining words make up the last names
     *   For example:  "Oscar de la Renta" --> "de la Renta, Oscar"
     *
     =	 * @return			String containing the formatted phone number
     * @throws UnsupportedOperationException if the argument contains a single word
     */
    @Override
    public String reformatName() {
        String result ="";
        String lastName ="";
        String firstName ="";
        //if(stringRam == ""){
        //    return "";
        //}
        if(!stringRam.contains(" ") || stringRam.length() <= 1){
            throw new UnsupportedOperationException();
        }
        else {
            for (int i = 0; i < stringRam.length(); i++) {
                if(stringRam.charAt(i) == ' '){
                    lastName = stringRam.substring(i+1);
                    firstName = stringRam.substring(0, i);
                    break; //to stop from finding 2nd space
                }
            }
            result = lastName + ", " + firstName;
        }

        return result;
    }

    /**
     * Replace all occurrences of a single zero (0) with the string "Go VCU"
     * in the current string,
     * and all occurrences of a double zero (00) with the string "CS@VCU"
     */
    @Override
    public void ramifyString() {
            String temp = "";
            String oneZero = "Go VCU";
            String twoZeros = "CS@VCU";
            int count = 0;

            for (int i = 0; i < stringRam.length(); i++) {
                if (stringRam.charAt(i) == '0') {
                    count++;
                }
                if (stringRam.charAt(i) != '0') {

                    if (count == 1) {
                        temp += oneZero;

                    }
                    if (count == 2) {
                        temp += twoZeros;

                    }
                    if (count > 2) {
                        for (int j = 0; j < count; j++) {
                            temp += '0';
                        }
                    }
                    temp += stringRam.substring(i, i + 1); //RETURNS THE NON 0 CHARACTER
                    count = 0;
                }
            }
            if (count == 1) {
                temp += oneZero;

            }
            if (count == 2) {
                temp += twoZeros;

            }
            if (count > 2) {
                for (int j = 0; j < count; j++) {
                    temp += '0';
                }
            }
            stringRam = temp;
        }




    /**
     * Replace the _individual_ digits in the current string, between
     * startPosition and endPosition (included), with the corresponding
     * Roman numeral symbol(s). The first character in the string is
     * considered to be in Position 1. Digits are converted individually,
     * even if contiguous, and digit "0" is not converted (e.g., 460 is
     * converted to IVVI0). In case you are not familiar with Roman
     * numerals, see https://en.wikipedia.org/wiki/Roman_numerals
     *
     * @param startPosition  Position of the first character to consider
     * @param endPosition    Position of the last character to consider
     * @throws IllegalArgumentException
     *            If "startPosition" > "endPosition" (but both are
     *            within bounds)
     */
    @Override
    /*public void convertDigitsToRomanNumeralsInSubstring(int startPosition, int endPosition) throws IllegalArgumentException {
        if(startPosition > endPosition || startPosition < 1 || endPosition < 1 || endPosition > stringRam.length() || endPosition == startPosition){
            throw new IllegalArgumentException();
        }

        String roman = "";
        String temp = "";
        if(startPosition >=1){
        //-1 to reconfigure position to beginning index
        for(int i = startPosition-1; i < endPosition; i++) {
            if (Character.isDigit(stringRam.charAt(i)) && stringRam.charAt(i) != '0') {
                if (stringRam.charAt(i) == '1') {
                    roman = "I";
                    temp += roman;
                }
                if (stringRam.charAt(i) == '2') {
                    roman = "II";
                    temp += roman;
                }
                if (stringRam.charAt(i) == '3') {
                    roman = "III";
                    temp += roman;
                }
                if (stringRam.charAt(i) == '4') {
                    roman = "IV";
                    temp += roman;
                }
                if (stringRam.charAt(i) == '5') {
                    roman = "V";
                    temp += roman;
                }
                if (stringRam.charAt(i) == '6') {
                    roman = "VI";
                    temp += roman;
                }
                if (stringRam.charAt(i) == '7') {
                    roman = "VII";
                    temp += roman;
                }
                if (stringRam.charAt(i) == '8') {
                    roman = "VIII";
                    temp += roman;
                }
                if (stringRam.charAt(i) == '9') {
                    roman = "IX";
                    temp += roman;
                }

            } else {
                temp += stringRam.substring(i, i + 1);
            }
        }

        }
        stringRam = temp;


    }*/
    public void convertDigitsToRomanNumeralsInSubstring(int startPosition, int endPosition) throws IllegalArgumentException, StringIndexOutOfBoundsException {
        if (startPosition > endPosition) {
            throw new IllegalArgumentException();
        }
        if (startPosition < 1 || endPosition < 1 || endPosition > stringRam.length() || endPosition > stringRam.length()) {

            throw new StringIndexOutOfBoundsException();
        }

            String roman = "";
            String temp = "";
            if (startPosition >= 1) {
                //-1 to reconfigure position to beginning index
                for (int i = startPosition - 1; i < endPosition; i++) {
                    if (Character.isDigit(stringRam.charAt(i))) {
                        if (stringRam.charAt(i) == '0') {
                            temp += '0';
                        }
                        if (stringRam.charAt(i) == '1') {
                            roman = "I";
                            temp += roman;
                        }
                        if (stringRam.charAt(i) == '2') {
                            roman = "II";
                            temp += roman;
                        }
                        if (stringRam.charAt(i) == '3') {
                            roman = "III";
                            temp += roman;
                        }
                        if (stringRam.charAt(i) == '4') {
                            roman = "IV";
                            temp += roman;
                        }
                        if (stringRam.charAt(i) == '5') {
                            roman = "V";
                            temp += roman;
                        }
                        if (stringRam.charAt(i) == '6') {
                            roman = "VI";
                            temp += roman;
                        }
                        if (stringRam.charAt(i) == '7') {
                            roman = "VII";
                            temp += roman;
                        }
                        if (stringRam.charAt(i) == '8') {
                            roman = "VIII";
                            temp += roman;
                        }
                        if (stringRam.charAt(i) == '9') {
                            roman = "IX";
                            temp += roman;
                        }

                    } else {
                        temp += stringRam.charAt(i);
                    }
                }

            }
            stringRam = stringRam.substring(0, startPosition - 1) + temp + stringRam.substring(endPosition);


        }


    }
    //end of class
