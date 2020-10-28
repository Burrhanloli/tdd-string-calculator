import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringCalculator {

  public static void main(String[] args) {
    StringCalculator calculator = new StringCalculator();
    int value = calculator.Add("1,-90,90");
    if (value != -1) {
      System.out.println(value);
    }

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
      numbers = getNumberString(stringValue, split);
    }

    List<Integer> numberArray = new ArrayList<>();
    if (!verifyAndAddToList(numbers, String.valueOf(delimiters), numberArray)) {
      return -1;
    }
    if (!handleNegativeNumbers(numberArray)) {
      return -1;
    }
    return numberArray.stream().reduce(0, Integer::sum);
  }

  private boolean handleNegativeNumbers(List<Integer> numberArray) {
    List<Integer> allNegatives = numberArray.stream()
        .filter(i -> i < 0)
        .collect(Collectors.toList());
    if (!allNegatives.isEmpty()) {
      try {
        throw new Exception(String.format("negatives not allowed %s",
                                          allNegatives.stream()
                                              .map(String::valueOf)
                                              .collect(Collectors.joining(","))));
      } catch (Exception e) {
        System.out.println(e.getMessage());
        return false;
      }
    }
    return true;
  }

  private String getNumberString(String stringValue, String[] split) {
    for (int i = 1; i < split.length; i++) {
      stringValue = stringValue.concat(split[i]);
    }
    return stringValue;
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
        System.out.println(e.getMessage());
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
