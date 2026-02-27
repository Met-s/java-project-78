package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;


public class MapSchema extends BaseSchema<Map<?, ?>> {

    public final MapSchema required() {

        addValidation("required", Objects::nonNull);
        return this;
    }

    public final MapSchema sizeof(int size) {

        addValidation("sizeof", map -> (map.size() == size));
        return this;
    }

    @SuppressWarnings("unchecked")
    public final <T> MapSchema shape(Map<String, BaseSchema<T>> schemas) {
        addValidation("shape",
                map -> !isNullAllowed(schemas) && schemas.entrySet().stream()
                        .allMatch(schema ->
                                schema.getValue().isValid((T)
                                        map.get(schema.getKey()))));
        return this;
    }
}
