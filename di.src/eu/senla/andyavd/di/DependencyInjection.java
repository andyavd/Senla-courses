package eu.senla.andyavd.di;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class DependencyInjection {
    private static final Logger LOG = Logger.getLogger(DependencyInjection.class);
    private static Map<Class<?>, Object> mainMaps = new HashMap<>();
    private static InstanceProperties instanceProperties = new InstanceProperties();

    public DependencyInjection() {
    }

    private static DependencyInjection injection;

    public static DependencyInjection getInjection() {
        if (injection == null) {
            injection = new DependencyInjection();
        }

        return injection;
    }


    public Object getInstance(Class<?> className) {
        Object object = mainMaps.get(className);

        if (object == null) {
            String nameInstance = instanceProperties.getRealization(className.getSimpleName());

            try {
                Class<?> clazz = Class.forName(nameInstance);
                Object obj = clazz.newInstance();
                mainMaps.put(className, obj);

                return obj;
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                LOG.error(e.getMessage(), e);
            }
        }

        return object;
    }
}
