package ru.sstu.cryptography.utils;

import java.util.Map;
import java.util.Optional;

public class MapUtil {
    public static Optional<String> get(Map<String, String> map, String key) {
        return Optional.ofNullable(map.get(key));
    }
}
