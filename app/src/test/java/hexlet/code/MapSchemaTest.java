package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class MapSchemaTest {

    final Validator v = new Validator();
    final MapSchema schema = new MapSchema();

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
    @DisplayName("Test: SchemaMap, shape()")
    void shapeTest() {
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2));

        schema.shape(schemas);

        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        assertTrue(schema.isValid(human1));

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        assertFalse(schema.isValid(human2));

        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        assertFalse(schema.isValid(human3));

    }

    @Test
    @DisplayName("Test: SchemaMap validation combination()")
    void  testSchemaMapValidation() {
        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        data.put("key2", "value2");

        assertTrue(schema.required().sizeof(2).isValid(data));
        assertFalse(schema.required().sizeof(3).isValid(data));

        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", v.string().required().minLength(2).contains("s"));
        schemas.put("lastName", v.string().required().minLength(4));
        schema.sizeof(2);
        schema.shape(schemas);
        Map<String, String> human1 = new HashMap<>();

        human1.put("firstName", "Mens");
        human1.put("lastName", "Smith");
        assertTrue(schema.isValid(human1));

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", "Smith");
        assertFalse(schema.isValid(human2));

        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Mens");
        human3.put("lastName", "Smith");
        human3.put("key", "value");
        assertFalse(schema.isValid(human3));
    }
}
