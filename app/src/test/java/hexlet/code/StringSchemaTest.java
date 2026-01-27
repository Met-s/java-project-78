package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class StringSchemaTest {
    Validator v = new Validator();
    StringSchema schema = v.string();

    @Test
    @DisplayName("Test: schema string, add required()")
    void testSchemaString() {

        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));

        schema.required();

        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid(null));
    }

    @Test
    @DisplayName("Test: SchemaString method MinLength()")
    void testSchemaStringMinLength() {

        assertTrue(schema.minLength(4).isValid("Hexlet"));
    }

    @Test
    @DisplayName("Test: SchemaString method Contains()")
    void testSchemaStringContains() {

        var schema1 = schema.required().contains("fox");
        assertTrue(schema1.isValid("fox say"));
        assertFalse(schema1.isValid("say"));
        assertFalse(schema1.isValid(null));

    }

    @Test
    @DisplayName("Test: SchemaString validation combination()")
    void testSchemaStringValidation() {

        var text = "what does the fox say";
        assertTrue(schema.minLength(8).contains("fox").isValid(null));
        assertTrue(schema.required().minLength(8).contains("fox").isValid(text));
    }
}
