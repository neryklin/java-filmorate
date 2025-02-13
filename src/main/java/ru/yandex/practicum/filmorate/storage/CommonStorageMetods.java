package ru.yandex.practicum.filmorate.storage;

import java.util.Map;

public interface CommonStorageMetods {

    public default long getNextId(Map<Long, ?> map) {
        long currentMaxId = map.keySet()
                .stream()
                .mapToLong(id -> id)
                .max()
                .orElse(0);
        return ++currentMaxId;

    }
}
