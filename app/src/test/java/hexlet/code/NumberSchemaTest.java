package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class NumberSchemaTest {
    Validator v = new Validator();
    NumberSchema schema = v.number();

    @Test
    @DisplayName("Test: schema NumberSchema, add required()")
    void numberRequieredTest() {

        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(null));
        schema.required();
        assertTrue(schema.isValid(5));
        assertFalse(schema.isValid(null));
    }

    @Test
    @DisplayName("Test: schema NumberSchema, positive()")
    void numberPositiveTest() {

        schema.required().positive();
        assertTrue(schema.isValid(5));
        assertFalse(schema.isValid(-2));
        assertFalse(schema.isValid(0));
    }

    @Test
    @DisplayName("Test: schema NumberSchema range()")
    void numberRangeTest() {

        schema.required().range(5, 10);
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
    }

    @Test
    @DisplayName("Test: NumberSchema validation combination()")
    void numberValidationTest() {

        assertTrue(schema.positive().range(4, 8).isValid(null));
        assertTrue(schema.required().positive().range(4, 8).isValid(5));
        assertFalse(schema.required().positive().range(4, 8).range(9, 11).isValid(5));
    }
}
