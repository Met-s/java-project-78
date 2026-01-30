package hexlet.code;

import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MapSchemaTest {

    Validator v = new Validator();
    MapSchema schema = new MapSchema();

    @Test
    @DisplayName("Test: schema MapSchema, required()")
    void requiredTest() {

        assertTrue(schema.isValid(null));
        schema.required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));
    }

    @Test
    @DisplayName("Test: schema MapSchema, sizeof()")
    void sizeofTest() {

        var data = new HashMap<String, String>();
        data.put("key1", "value1");

        assertTrue(schema.isValid(data));

        schema.required().sizeof(2);

        assertFalse(schema.isValid(data));

        data.put("key2", "value2");
        assertTrue(schema.isValid(data));
    }

    @Test
    @DisplayName("Test: SchemaMap validation combination()")
    void  testSchemaMapValidation() {
        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        data.put("key2", "value2");

        assertTrue(schema.required().sizeof(2).isValid(data));
        assertFalse(schema.required().sizeof(3).isValid(data));
    }
}
