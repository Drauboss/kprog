package prog.ex01.solution.palindrome;


import java.text.Normalizer;
import java.text.Normalizer.Form;
import prog.ex01.exercise.palindrome.PalindromeChecker;

/**
 * Simple palindrome checker.
 */
public class SimplePalindromeChecker implements PalindromeChecker {
  @Override
  public boolean isPalindrome(final String line) {


    char[] chars = normalizeLine(line); //string to normalized char array
    int arrayLengthHalf = chars.length / 2;
    int arrayLength = chars.length;
    boolean p = true;

    //compare first with last char, then second with second last,
    // etc until half of string is compared
    //if a two compared chars arent equal
    for (int i = 0; i < arrayLengthHalf; i++) {
      if (chars[i] != chars[arrayLength - 1 - i]) {
        p = false;
        //System.out.println(chars[i] + " ist ungleich " + chars[arrayLength - 1 - i]);
      }


    }
    //System.out.println(p);
    return p;
  }

  @Override
  public char[] normalizeLine(String line) {



    line = line.replaceAll("[\\-\\+\\.\\^:,!–]","");
    line = line.replaceAll(" ", "");
    line = Normalizer.normalize(line, Form.NFD);
    line = line.replaceAll("\\p{M}", "");
    line = line.toLowerCase();



    //wandle den String in einen array aus chars um
    char[] chars = line.toCharArray();

    return chars;
  }

}
