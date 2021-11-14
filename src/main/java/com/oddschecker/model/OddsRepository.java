package com.oddschecker.model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface OddsRepository extends CrudRepository<Odds, OddComplexKey> {

    List<Odds> findAllByBetId(Long betId);
}