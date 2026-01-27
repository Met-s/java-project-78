package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;


public abstract class BaseSchema<T> {
    private final Map<String, Predicate<T>> validations = new HashMap<>();

    protected void addValidation(String key, Predicate<T> validation) {
        this.validations.put(key, validation);
    }

    public boolean isValid(T obj) {
        if (validations.containsKey("required") && (obj == null || obj == "")) {
            return false;
        }
        if (!validations.containsKey("required")) {
            return true;
        }


        return validations.values().stream()
                .allMatch(validation -> validation.test(obj));
    }
}
