import java.util.ArrayList;
import java.util.List;

public class StringCalculator {

  public static void main(String[] args) {
    StringCalculator calculator = new StringCalculator();
    int value = calculator.Add("1");
    System.out.println(value);

  }

  public int Add(String numbers) {
    if (numbers == null || numbers.isEmpty()) {
      return 0;
    }
    List<Integer> numberArray = new ArrayList<>();
    for (String s : numbers.split(",")) {
      try{
        Integer parseInt = Integer.parseInt(s);
        numberArray.add(parseInt);
      }catch (Exception e){
        System.out.println("Not Valid");
        return 0;
      }
    }
    return numberArray.stream().reduce(0, Integer::sum);
  }


}
