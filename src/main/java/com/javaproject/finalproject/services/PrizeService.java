package com.javaproject.finalproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaproject.finalproject.models.Prize;
import com.javaproject.finalproject.repositories.PrizeRepository;

@Service
public class PrizeService
{
	@Autowired
	private PrizeRepository prizeRepo;
	
//	RETURNS ALL THE PRIZES
	public List<Prize> allPrizes()
	{
		return prizeRepo.findAll();
	}
	
//	CREATES PRIZE
	public Prize createPrize(Prize e)
	{
		return prizeRepo.save(e);
	}
	
//	RETRIEVES ONE PRIZE
	public Prize findPrize(Long id)
	{
		Optional<Prize> optionalPrize = prizeRepo.findById(id);
		if(optionalPrize.isPresent())
		{
			return optionalPrize.get();
		} else
		{
			return null;
		}
	}
	
//	UPDATES ONE PRIZE
	public Prize updatePrize(Prize e)
	{
		return prizeRepo.save(e);
	}
	
//	DELETES ONE PRIZE
	public void deletePrize(Long id)
	{
		prizeRepo.deleteById(id);
	}
}
