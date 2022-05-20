package by.incubator.application.infrastructure.core.impl;

import by.incubator.application.infrastructure.config.Config;
import by.incubator.application.infrastructure.config.impl.JavaConfig;
import by.incubator.application.infrastructure.core.Cache;
import by.incubator.application.infrastructure.core.Context;
import by.incubator.application.infrastructure.core.ObjectFactory;

import java.util.Map;

public class ApplicationContext implements Context {

    private final Config config;
    private final Cache cache;
    private final ObjectFactory factory;

    public ApplicationContext(String packageToScan, Map<Class<?>, Class<?>> interfaceToImplementation) {
        config = new JavaConfig(new ScannerImpl(packageToScan), interfaceToImplementation);
        cache = new CacheImpl();
        cache.put(Context.class, this);
        factory = new ObjectFactoryImpl(this);
    }

    @Override
    public <T> T getObject(Class<T> type) {
        T value;

        if (cache.contains(type)) {
            return cache.get(type);
        }

        if (type.isInterface()) {
            value = factory.createObject(config.getImplementation(type));
        } else {
            value = factory.createObject(type);
        }

        cache.put(type,value);

        return value;
    }

    @Override
    public Config getConfig() {
        return config;
    }
}
