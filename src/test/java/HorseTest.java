import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {
    @Test
    public void constructor_NullFirstParam_ThrownIllegalArgumentException() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse(null, 0, 0);
                }
        );
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "\s", "\t"})
    public void constructor_EmptyFirstParam_ThrownIllegalArgumentException(String strings) {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse(strings, 0, 0);
                }
        );
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    public void constructor_NegativeSecondParam_ThrownIllegalArgumentException() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse("name", -1, 0);
                }
        );
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    public void constructor_NegativeThirdParam_ThrownIllegalArgumentException() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse("name", 0, -1);
                }
        );
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }
}