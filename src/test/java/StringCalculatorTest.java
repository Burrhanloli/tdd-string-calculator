import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class StringCalculatorTest {

  private final StringCalculator calculator = new StringCalculator();

  @Test
  public void testNullValue() {
    int actual = calculator.Add(null);
    assertEquals(0, actual);
  }
  @Test
  public void testEmptyString() {
    int actual = calculator.Add("");
    assertEquals(0, actual);
  }

  @Test
  public void testSimpleValidNumberString() {
    int actual = calculator.Add("1,2");
    assertEquals(3, actual);
  }

  @Test
  public void testSimpleInvalidString() {
    int actual = calculator.Add("nullas");
    assertEquals(0, actual);
  }

  //====



}