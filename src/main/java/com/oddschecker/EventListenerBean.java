package com.oddschecker;


import com.oddschecker.model.Odds;
import com.oddschecker.model.OddsRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


/*
To initialize the application db after the spring context has been loaded
 */
@Component
public class EventListenerBean {


    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {

        //added in case we want to initialize something before starting the app

    }

}