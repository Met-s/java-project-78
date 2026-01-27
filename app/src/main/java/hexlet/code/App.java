package hexlet.code;


public class App {
    public static void main(String[] args) {

        var v = new Validator();
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








    }
}
