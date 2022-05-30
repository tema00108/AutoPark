package by.incubator.application.infrastructure.configurators.impl;

import by.incubator.application.infrastructure.configurators.ObjectConfigurator;
import by.incubator.application.infrastructure.core.Context;
import by.incubator.application.infrastructure.core.annotations.Autowired;
import lombok.SneakyThrows;

import java.lang.reflect.Field;

public class AutowiredObjectConfigurator implements ObjectConfigurator {

    @Override
    @SneakyThrows
    public void configure(Object object, Context context) {
        Field[] fields = object.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(Autowired.class)) {
                field.setAccessible(true);
                field.set( object, context.getObject(field.getType()));
            }
        }
    }
}
