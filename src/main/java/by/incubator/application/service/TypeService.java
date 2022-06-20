package by.incubator.application.service;

import by.incubator.application.entity.Type;
import by.incubator.application.infrastructure.core.annotations.Autowired;
import by.incubator.application.infrastructure.core.annotations.InitMethod;
import by.incubator.application.infrastructure.orm.EntityManager;

import java.util.List;

public class TypeService {
    @Autowired
    EntityManager entityManager;

    @InitMethod
    public void init() { }

    public Type get(Long id) {
        return entityManager.get(id, Type.class).orElse(null);
    }

    public List<Type> getAll() {
        return entityManager.getAll(Type.class);
    }

    public Long save(Type type) {
        return entityManager.save(type);
    }
}
