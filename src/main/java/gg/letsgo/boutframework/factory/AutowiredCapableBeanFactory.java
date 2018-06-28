package gg.letsgo.boutframework.factory;

import gg.letsgo.boutframework.BeanDefinition;

import java.lang.reflect.Field;
import java.util.Map;

public class AutowiredCapableBeanFactory extends AbstractBeanFactory {
    @Override
    protected Object createBean(BeanDefinition beanDefinition) throws Exception {
        Object bean = createBeanInstance(beanDefinition);
        applyProperties(bean, beanDefinition);
        return bean;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition) throws Exception {
        return beanDefinition.getBeanClass().newInstance();
    }

    protected void applyProperties(Object bean, BeanDefinition beanDefinition) throws Exception {
        for (Map.Entry entry : beanDefinition.getProperties().getPropertyMap().entrySet()) {
            Field field = bean.getClass().getDeclaredField((String) entry.getKey());
            field.setAccessible(true);
            field.set(bean, entry.getValue());
        }
    }
}
