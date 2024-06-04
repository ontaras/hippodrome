import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;


@ExtendWith(MockitoExtension.class)
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

    @Test
    public void getName_checkFirstParam_returnObjectName() {
        Horse horse = new Horse("noname", 0);
        assertEquals("noname", horse.getName());
    }

    @Test
    public void getSpeed_checkSecondParam_returnObjectSpeed() {
        Horse horse = new Horse("noname", 10.55555);
        assertEquals(10.55555, horse.getSpeed());
    }

    @Test
    public void getDistance_checkThirdParam_returnObjectDistance() {
        Horse horse = new Horse("noname", 0, 1000.333);
        assertEquals(1000.333, horse.getDistance());
    }

    @Test
    public void move_shouldInvokeGetRandomDoubleMethod_correctParams() {
        MockedStatic<Horse> horseMockedStatic = mockStatic(Horse.class);
        Horse horse = new Horse("noname", 10.555, 55.111);
        horse.move();

        horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-0.1, 0, 0.2, 0.4, 0.7, 0.9, 8})
    public void getRandomDouble_CalculatingMethod_returnCorrectValue(double doubles) {
        double speed = 10;
        double distance = 100;
        Horse horse = new Horse("noname", speed, distance);
        double expectedValue = distance + speed * doubles;
        try (MockedStatic<Horse> horseMockedStatic = mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(doubles);
            horse.move();
        }

        assertEquals(expectedValue, horse.getDistance());
    }
}