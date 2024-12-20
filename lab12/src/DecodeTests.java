import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DecodeTests {
    @Test
    void emptyString() {
        assertThrows(NumberFormatException.class, () -> Integer.decode(""));
    }

    @Test
    void negativeNumber() {
        assertEquals(-1, Integer.decode("-1"));
    }

    @Test
    void explicitlyPositiveNumber() {
        assertEquals(1, Integer.decode("+1"));
    }

    @Test
    void positiveNumber() {
        assertEquals(5, Integer.decode("5"));
    }

    @Test
    void hexRadixSpecifiers() {
        int expected = 202;
        String hex = "ca";

        assertEquals(expected, Integer.decode("0x" + hex));
        assertEquals(expected, Integer.decode("0X" + hex));
        assertEquals(expected, Integer.decode("#" + hex));
    }

    @Test
    void octRadixSpecifiers() {
        int expected = 202;
        String oct = "312";

        assertEquals(expected, Integer.decode("0" + oct));
    }

    @Test
    void zeroSingleDigit() {
        assertEquals(0, Integer.decode("0"));
    }

    @Test
    void signIncorrectPosition() {
        assertThrows(NumberFormatException.class, () -> Integer.decode("0+9"));
        assertThrows(NumberFormatException.class, () -> Integer.decode("0-9"));
        assertThrows(NumberFormatException.class, () -> Integer.decode("09+"));
        assertThrows(NumberFormatException.class, () -> Integer.decode("09-"));
    }

    @Test
    void leadingZeroMultipleDigitsOctal() {
        assertEquals(511, Integer.decode("0777"));
    }

    @Test
    void minValue() {
        String minValueString = Integer.valueOf(Integer.MIN_VALUE).toString();
        assertEquals(Integer.MIN_VALUE, Integer.decode(minValueString));
    }

    @Test
    void maxValue() {
        String maxValueString = Integer.valueOf(Integer.MAX_VALUE).toString();
        assertEquals(Integer.MAX_VALUE, Integer.decode(maxValueString));
    }

    @Test
    void formatErrorRethrown() {
        assertThrows(NumberFormatException.class, () -> Integer.decode("1llBeBack1"));
    }

    @Test
    void tooLarge() {
        assertThrows(NumberFormatException.class, () -> Integer.decode("2147483648"));
    }
}
