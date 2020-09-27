package com.ocampogeric.soap.pokemon.models.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ocampogeric.soap.pokemon.models.dao.IBinnacleDao;
import com.ocampogeric.soap.pokemon.models.entities.BinnacleRegister;

@Service
public class BinnacleServiceImpl implements IBinnacleService {
	
	@Autowired
	private IBinnacleDao binnacleDao;
	
	@Override
	@Transactional
	public BinnacleRegister save(BinnacleRegister binnacleItem) {
		return binnacleDao.save(binnacleItem);
	}
}
