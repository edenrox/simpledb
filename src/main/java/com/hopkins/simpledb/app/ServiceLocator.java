package com.hopkins.simpledb.app;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ian_000 on 6/1/2017.
 */
public class ServiceLocator {
  private static final ServiceLocator instance = new ServiceLocator();

  private final Map<Class<?>, Object> map = new HashMap<>();

  public static ServiceLocator getInstance() {
    return instance;
  }

  public <T> ServiceLocator bind(Class<T> clazz, T instance) {
    map.put(clazz, instance);
    return this;
  }

  public <T> T get(Class<T> clazz) {
    if (!map.containsKey(clazz)) {
      throw new IllegalStateException("Class not found: " + clazz);
    }
    return (T) map.get(clazz);
  }
}
