package hexlet.code;


public class App {
    public static void main(String[] args) {

        var v = new Validator();

// StringSchema
        var schema = v.string();


        System.out.println(schema.isValid(""));
        System.out.println(schema.isValid(null));

        schema.required();

        String str = "what does the fox say";
        System.out.println(schema.isValid(""));
        System.out.println(schema.isValid(null));
        System.out.println(schema.isValid(str));
        System.out.println(schema.isValid("hexlet"));

        System.out.println(schema.contains("wh").isValid(str));
        System.out.println(schema.contains("what").isValid(str));
        System.out.println(schema.contains("whatthe").isValid(str));
        System.out.println("false: " + schema.isValid(str));

        var schema1 = v.string();
        System.out.println(schema1.minLength(10).minLength(4).isValid("Hexlet"));

// NumberSchema
        var n = new Validator();
        var schemaNum = n.number();

        System.out.println(schemaNum.isValid(5));
        System.out.println(schemaNum.isValid(null));
        System.out.println(schemaNum.positive().isValid(null));

        schemaNum.required();
        System.out.println(schemaNum.isValid(null));
        System.out.println(schemaNum.positive().isValid(10));

        System.out.println(schemaNum.isValid(-10));
        System.out.println(schemaNum.isValid(0));

        schemaNum.range(5, 10);
        System.out.println(schemaNum.isValid(5));
        System.out.println(schemaNum.isValid(10));
        System.out.println(schemaNum.isValid(4));
        System.out.println(schemaNum.isValid(11));





    }
}
