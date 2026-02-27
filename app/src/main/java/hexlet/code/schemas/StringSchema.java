package hexlet.code.schemas;


public class StringSchema extends BaseSchema<String> {

    public final StringSchema required() {
        addValidation("required", value -> !isNullAllowed(value)
                && !value.isEmpty());

        return this;
    }

    public final StringSchema minLength(int minLength) {

        addValidation("minLength", str -> isNullAllowed(str)
                || str.length() > minLength);

        return this;
    }

    public final StringSchema contains(String substring) {

        addValidation("substring", str -> isNullAllowed(str)
                || str.contains(substring));
        return this;
    }
}
