package com.oddschecker.controller;

import com.google.common.collect.Iterables;
import com.oddschecker.model.Odds;
import com.oddschecker.service.OddsService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;

@RestController
public class OddsController {

    private Logger logger = LoggerFactory.getLogger(OddsController.class);


    //this is using directly the service implementation, to decouple we could use a generic service interface
    @Autowired
    OddsService oddsService;

    //added apiResponses annotation only to play with swagger ui..
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Odds have been created for bet"),
            @ApiResponse(code = 400, message = "Invalid format of Odds")
    })
    @PostMapping(value = "/odds", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Odds> addOdd(@Valid @RequestBody Odds odd) {

        return new ResponseEntity<>(oddsService.addOdd(odd), HttpStatus.CREATED);

    }


    @GetMapping(value = "/odds/{betId}", produces = "application/json")
    public ResponseEntity<Odds[]> odds( @PathVariable Long betId) {

        //the service could contain the logic and return a custom not found entity exception
        //I did like this as the api is quite simple as requested
        Iterable<Odds> odds = oddsService.findAllByBetId(betId);

        if(odds.iterator().hasNext()) {
            Odds[] bet = Iterables.toArray(odds, Odds.class);
            return new ResponseEntity<>(bet, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}