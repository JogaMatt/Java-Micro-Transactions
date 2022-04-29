package com.javaproject.finalproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaproject.finalproject.models.UsersEquipment;
import com.javaproject.finalproject.repositories.UsersEquipmentRepository;

@Service
public class UsersEquipmentService
{
	@Autowired
	private UsersEquipmentRepository usersEquipRepo;
	
//	RETURNS ALL THE EQUIPMENT
	public List<UsersEquipment> allEquipment()
	{
		return usersEquipRepo.findAll();
	}
	
//	CREATES EQUIPMENT
	public UsersEquipment addEquipment(UsersEquipment e)
	{
		return usersEquipRepo.save(e);
	}
	
//	RETRIEVES ONE EQUIPMENT
	public UsersEquipment findEquipment(Long id)
	{
		Optional<UsersEquipment> optionalEquip = usersEquipRepo.findById(id);
		if(optionalEquip.isPresent())
		{
			return optionalEquip.get();
		} else
		{
			return null;
		}
	}
	
//	RETRIEVES USERS EQUIPMENT
	public List<UsersEquipment> findMyEquipment(Long id)
	{
		return usersEquipRepo.findByUserId(id);
	}
	
//	UPDATES ONE EQUIPMENT
	public UsersEquipment updateEquip(UsersEquipment e)
	{
		return usersEquipRepo.save(e);
	}
	
//	DELETES ONE EQUIPMENT
	public void deleteEquip(Long id)
	{
		usersEquipRepo.deleteById(id);
	}
}

