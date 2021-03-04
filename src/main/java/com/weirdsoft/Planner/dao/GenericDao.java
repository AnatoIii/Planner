package com.weirdsoft.Planner.dao;

import java.util.UUID;

public interface GenericDao<T> {
    T find(UUID id);
    T create(T object);
    UUID delete(UUID id, UUID userId);
    UUID update(T object);
}
