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
    assertEquals(-1, actual);
  }

  @Test
  public void testStringNumberWithNewLine() {
    int actual = calculator.Add("1\n2,3");
    assertEquals(6, actual);
  }

  @Test
  public void testInvalidStringNumberWithNewLine() {
    int actual = calculator.Add("1,\n");
    assertEquals(-1, actual);
  }

  @Test
  public void testInvalidStringNumberWithNull() {
    int actual = calculator.Add("1,null");
    assertEquals(-1, actual);
  }

  @Test
  public void testNewDelimiterString() {
    int actual = calculator.Add("//:\n1:2:3");
    assertEquals(6, actual);
  }

  @Test
  public void testDelimiterString() {
    int actual = calculator.Add("//;\n1;2;999");
    assertEquals(1002, actual);
  }

  @Test
  public void testDelimiterCanBeAnyLengthString() {
    int actual = calculator.Add("//~~~\n1~~~2~~~90");
    assertEquals(93, actual);
  }

  @Test
  public void testRegexMetaDelimiterString() {
    int actual = calculator.Add("//^\n1^5^90");
    assertEquals(96, actual);
  }

  @Test
  public void testRegexMetaDelimiterAnyLengthString() {
    int actual = calculator.Add("//????\n1????90????90");
    assertEquals(181, actual);
  }

  @Test
  public void testNegativeNumberString() {
    int actual = calculator.Add("1,-90,90");
    assertEquals(-1, actual);
  }

  @Test
  public void testMultipleNegativeNumberString() {
    int actual = calculator.Add("-1,-90,90,78");
    assertEquals(-1, actual);
  }
}