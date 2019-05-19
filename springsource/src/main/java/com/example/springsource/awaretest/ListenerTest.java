package com.example.springsource.awaretest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by LIZHUANGZHUANG001 on 2019/5/15.
 */
@Slf4j
@Component
public class ListenerTest implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent applicationEnvironmentPreparedEvent) {
        MutablePropertySources propertySources = applicationEnvironmentPreparedEvent.getEnvironment().getPropertySources();
        Properties properties = new Properties();
        Map<String, SimpleMessageListenerContainer> map = new HashMap();
        map.put("aa", new SimpleMessageListenerContainer());
        properties.put("aaa", map);
        propertySources.addFirst(new PropertiesPropertySource("alenProperty", properties));
        log.info(propertySources.toString());
    }
}
