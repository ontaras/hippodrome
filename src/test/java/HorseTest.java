import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {
    @Test
    void constructor_NullAsNameParam_ThrownIllegalArgumentException() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse(null, 0, 0);
                }
        );
        assertEquals("Name cannot be null.", exception.getMessage());
    }
}