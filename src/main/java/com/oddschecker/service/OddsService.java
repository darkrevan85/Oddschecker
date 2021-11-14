package com.oddschecker.service;


import com.google.common.collect.Lists;
import com.oddschecker.model.Odds;
import com.oddschecker.model.OddsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;

@Service
@Transactional
public class OddsService {

    private Logger logger = LoggerFactory.getLogger(OddsService.class);

    @Autowired
    private OddsRepository oddsRepository;

    public Odds addOdd(Odds odd){
        return oddsRepository.save(odd);
    }

    //the logic of this app is simple so I moved it to the controller, but in a real api the logic should be here
    //in the service
    public List<Odds> findAllByBetId(Long betId){
        return oddsRepository.findAllByBetId(betId);
    }

}
