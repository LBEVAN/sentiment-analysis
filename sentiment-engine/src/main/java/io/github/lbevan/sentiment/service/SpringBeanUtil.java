package io.github.lbevan.sentiment.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * Service for retrieving Spring beans from the application context.
 */
@Service
public class SpringBeanUtil implements ApplicationContextAware {

    private static ApplicationContext context;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.context = context;
    }

    /**
     * Retrieve a bean by it's class from the application context.
     *
     * @param beanClass
     * @param <T>
     * @return T
     */
    public static <T> T getBean(Class<T> beanClass) {
        return context.getBean(beanClass);
    }
}
