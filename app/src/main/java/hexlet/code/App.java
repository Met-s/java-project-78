package hexlet.code;


import hexlet.code.schemas.BaseSchema;

import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {

//        var v = new Validator();

// StringSchema
//        var schema = v.string();
//
//
//        System.out.println(schema.isValid(""));
//        System.out.println(schema.isValid(null));
//
//        schema.required();
//
//        String str = "what does the fox say";
//        System.out.println(schema.isValid(""));
//        System.out.println(schema.isValid(null));
//        System.out.println(schema.isValid(str));
//        System.out.println(schema.isValid("hexlet"));
//
//        System.out.println(schema.contains("wh").isValid(str));
//        System.out.println(schema.contains("what").isValid(str));
//        System.out.println(schema.contains("whatthe").isValid(str));
//        System.out.println("false: " + schema.isValid(str));
//
//        var schema1 = v.string();
//        System.out.println(schema1.minLength(10).minLength(4).isValid("Hexlet"));
//
//// NumberSchema
//        var n = new Validator();
//        var schemaNum = n.number();
//
//        System.out.println(schemaNum.isValid(5));
//        System.out.println(schemaNum.isValid(null));
//        System.out.println(schemaNum.positive().isValid(null));
//
//        schemaNum.required();
//        System.out.println(schemaNum.isValid(null));
//        System.out.println(schemaNum.positive().isValid(10));
//
//        System.out.println(schemaNum.isValid(-10));
//        System.out.println(schemaNum.isValid(0));
//
//        schemaNum.range(5, 10);
//        System.out.println(schemaNum.isValid(5));
//        System.out.println(schemaNum.isValid(10));
//        System.out.println(schemaNum.isValid(4));
//        System.out.println(schemaNum.isValid(11));

//// MapSchema
        var m = new Validator();
        var schemaMap = m.map();

//        System.out.println(schemaMap.isValid(null));
//        schemaMap.required();
//        System.out.println(schemaMap.isValid(null));
//        var data = new HashMap<String, String>();
//        data.put("key1", "value1");
//
//        schemaMap.sizeof(2);
//        System.out.println(schemaMap.isValid(data));
//        data.put("key2", "value2");
//        System.out.println(schemaMap.isValid(data));

        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", m.string().required());
        schemas.put("lastName", m.string().required().minLength(2));

        schemaMap.shape(schemas);

        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        System.out.println(schemaMap.isValid(human1)); // true

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        System.out.println(schemaMap.isValid(human2)); // false


        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        System.out.println(schemaMap.isValid(human3)); // false


    }
}
