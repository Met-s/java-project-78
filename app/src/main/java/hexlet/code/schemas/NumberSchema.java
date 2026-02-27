package hexlet.code.schemas;


public class NumberSchema extends BaseSchema<Integer> {

    public final NumberSchema required() {

        addValidation("required", value -> !isNullAllowed(value));
        return this;
    }

    public final NumberSchema positive() {

        addValidation("positive", number -> isNullAllowed(number)
                || number > 0);
        return this;
    }

    public final NumberSchema range(int minLength, int maxLength) {

        addValidation("range", number -> isNullAllowed(number)
                || number >= minLength && number <= maxLength);
        return this;
    }
}
