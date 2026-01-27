package hexlet.code.schemas;

import java.util.Objects;

public class NumberSchema extends BaseSchema<Integer>{

    public NumberSchema required() {

        addValidation("required", Objects::nonNull);
        return this;
    }

    public NumberSchema positive() {

        addValidation("positive", number -> number > 0);
        return this;
    }

    public NumberSchema range(int min, int max) {

        addValidation("range", number ->
               number >= min && number <= max);
        return this;
    }
}
