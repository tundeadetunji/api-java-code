package io.github.tundeadetunji;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Lite JSON parser.
 */
public final class Json {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static String from(Object entity) throws IOException, JsonProcessingException {
        return mapper.writeValueAsString(entity);
    }

    public static Object from(Object entity, boolean asBytesNotString) throws IOException {
        return !asBytesNotString ? from(entity) : mapper.writeValueAsBytes(entity);
    }

    public static <T> T to(String json, Class<T> entity) throws IOException {
        return ((T) mapper.readValue(json, entity));
    }

    public static <T> List<T> asList(String jsonArray, Class<T> type) throws IOException {
        return mapper.readValue(jsonArray, new TypeReference<List<T>>(){});
    }

    /*public static <T> List<T> asList(String jsonArray, TypeReference<T> reference) throws IOException {
        return mapper.readValue(jsonArray, reference);
    }*/

    public static <T> List<T> asList(String jsonArray, TypeReference<List<T>> reference) throws IOException, JsonProcessingException {
        return mapper.readValue(jsonArray, reference);
    }

    public static <K, V> Map<K, V> asMap(String jsonObject, Class<K> K, Class<V> V) throws IOException, JsonProcessingException {
        return mapper.readValue(jsonObject, new TypeReference<Map<K, V>> () {});
    }
}
