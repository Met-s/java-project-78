package hexlet.code.schemas;

import java.util.Objects;


public class StringSchema extends BaseSchema<String> {


    public StringSchema required() {
        addValidation("required", Objects::nonNull);

        return this;
    }

    public StringSchema minLength(int minLength) {

        addValidation("minLength", (String str) -> str.length() > minLength);

        return this;
    }

    public StringSchema contains(String substring) {

        addValidation("substring", str -> str == null || str.contains(substring));
        return this;
    }
}
