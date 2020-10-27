import java.util.ArrayList;
import java.util.List;

public class StringCalculator {

  public static void main(String[] args) {
    StringCalculator calculator = new StringCalculator();
    int value = calculator.Add("1\n2,3");
    System.out.println(value);

  }

  public int Add(String numbers) {
    if (numbers == null || numbers.isEmpty()) {
      return 0;
    }
    String delimiters = ",";
    List<Integer> numberArray = new ArrayList<>();
    if (!verifyAndAddToList(numbers, delimiters, numberArray)) {
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
      if (s == null || s.isEmpty()) {
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
        System.out.println("Not Valid");
        return false;
      }
    }
    return true;
  }


}
