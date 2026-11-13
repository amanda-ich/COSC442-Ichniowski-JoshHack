package game;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

// Added CombatRulesTest with test cases for Exam - Question 1:
public class CombatRulesTest {

    @ParameterizedTest
    @CsvSource({
        "1, 100, -2, -1",
        "50, 100, -1, 49",
        "100, 100, 2, 100"
    })
    void testCalculateNewHp(int currentHp, int maxHp, int amount, int expected) {
        int actual = CombatRules.calculateNewHp(currentHp, maxHp, amount);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "-1, true",
        "50, false",
        "100, false",
        "1, false"
    })
    void testIsDead(int currentHp, boolean expected) {
        boolean actual = CombatRules.isDead(currentHp);
        assertEquals(expected, actual);
    }
}
