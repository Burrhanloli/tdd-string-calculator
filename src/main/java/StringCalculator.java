import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringCalculator {

  public static void main(String[] args) {
    StringCalculator calculator = new StringCalculator();
    int value = calculator.Add("//^^^\n1^^^2^^^90");
    System.out.println(value);

  }

  public int Add(String numbers) {
    if (numbers == null || numbers.isEmpty()) {
      return 0;
    }

    String delimiters = ",";
    String stringValue = "";
    if (numbers.startsWith("//")) {
      final String[] split = numbers.split("\n");
      delimiters = handleMetaCharacters(split[0].substring(2));
      for (int i = 1; i < split.length; i++) {
        stringValue = stringValue.concat(split[i]);
      }
      numbers = stringValue;
    }

    List<Integer> numberArray = new ArrayList<>();
    if (!verifyAndAddToList(numbers, String.valueOf(delimiters), numberArray)) {
      return 0;
    }
    return numberArray.stream().reduce(0, Integer::sum);
  }

  private boolean verifyAndAddToList(String numbers, String delimiters, List<Integer> numberArray) {
    final String[] split = numbers.split(delimiters);
    if (split.length == 0) {
      return false;
    }
    for (String s : split) {
      if (s.isEmpty()) {
        return false;
      }
      if (s.contains("\n")) {
        if (!verifyAndAddToList(s, "\n", numberArray)) {
          return false;
        }
        continue;
      }
      try {
        Integer parseInt = Integer.parseInt(s);
        numberArray.add(parseInt);
      } catch (Exception e) {
        return false;
      }
    }
    return true;
  }

  public String handleMetaCharacters(String delimiter) {
    String s = delimiter;
    String[] operators = new String[] {"+", "*", "^", "?"};
    if (Arrays.stream(operators).anyMatch(s::contains)) {
      String matchedDelimiter = Arrays.stream(operators).filter(s::contains).findFirst().get();
      s = s.replace(matchedDelimiter, "\\".concat(matchedDelimiter));
    }
    return s;
  }

}
