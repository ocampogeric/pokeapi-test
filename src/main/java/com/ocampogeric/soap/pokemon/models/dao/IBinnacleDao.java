package com.ocampogeric.soap.pokemon.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.ocampogeric.soap.pokemon.models.entities.BinnacleRegister;

public interface IBinnacleDao extends CrudRepository<BinnacleRegister, Long>{

}
