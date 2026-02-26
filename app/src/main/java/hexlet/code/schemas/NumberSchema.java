package hexlet.code.schemas;


public class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema required() {

        addValidation("required", value -> !isNullAllowed(value));
        return this;
    }

    public NumberSchema positive() {

        addValidation("positive", number -> isNullAllowed(number)
                || number > 0);
        return this;
    }

    public NumberSchema range(int minLength, int maxLength) {

        addValidation("range", number -> isNullAllowed(number)
                || number >= minLength && number <= maxLength);
        return this;
    }
}
