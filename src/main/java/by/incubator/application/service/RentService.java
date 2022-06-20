package by.incubator.application.service;

import by.incubator.application.entity.Rent;
import by.incubator.application.infrastructure.core.annotations.Autowired;
import by.incubator.application.infrastructure.core.annotations.InitMethod;
import by.incubator.application.infrastructure.orm.EntityManager;

import java.util.List;

public class RentService {
    @Autowired
    EntityManager entityManager;

    @InitMethod
    public void init() { }

    public Rent get(Long id) {
        return entityManager.get(id, Rent.class).orElse(null);
    }

    public List<Rent> getAll() {
        return entityManager.getAll(Rent.class);
    }

    public Long save(Rent rent) {
        return entityManager.save(rent);
    }
}
