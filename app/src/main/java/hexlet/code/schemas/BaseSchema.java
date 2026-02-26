package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;


public abstract class BaseSchema<T> {
    private final Map<String, Predicate<T>> validations = new HashMap<>();

    protected void addValidation(String key, Predicate<T> validation) {
        this.validations.put(key, validation);
    }

    protected final boolean isNullAllowed(T value) {
        return value == null;
    }

    public boolean isValid(T obj) {

        return validations.values().stream()
                .allMatch(validation -> validation.test(obj));
    }
}
