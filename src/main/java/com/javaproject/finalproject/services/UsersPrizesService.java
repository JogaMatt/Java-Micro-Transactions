package com.javaproject.finalproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaproject.finalproject.models.UsersPrizes;
import com.javaproject.finalproject.repositories.UsersPrizesRepository;

@Service
public class UsersPrizesService
{
	@Autowired
	private UsersPrizesRepository usersPrizesRepo;
	
//	RETURNS ALL THE PRIZES
	public List<UsersPrizes> allPrizes()
	{
		return usersPrizesRepo.findAll();
	}
	
//	CREATES PRIZE
	public UsersPrizes addPrizes(UsersPrizes e)
	{
		return usersPrizesRepo.save(e);
	}
	
//	RETRIEVES ONE PRIZE
	public UsersPrizes findPrize(Long id)
	{
		Optional<UsersPrizes> optionalPrize = usersPrizesRepo.findById(id);
		if(optionalPrize.isPresent())
		{
			return optionalPrize.get();
		} else
		{
			return null;
		}
	}
	
//	RETRIEVES USERS PRIZES
	public List<UsersPrizes> findMyPrizes(Long id)
	{
		return usersPrizesRepo.findByUserId(id);
	}
	
//	UPDATES ONE PRIZE
	public UsersPrizes updatePrize(UsersPrizes e)
	{
		return usersPrizesRepo.save(e);
	}
	
//	DELETES ONE PRIZE
	public void deletePrize(Long id)
	{
		usersPrizesRepo.deleteById(id);
	}
}
