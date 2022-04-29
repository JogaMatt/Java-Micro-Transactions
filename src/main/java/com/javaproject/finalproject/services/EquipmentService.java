package com.javaproject.finalproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaproject.finalproject.models.Equipment;
import com.javaproject.finalproject.repositories.EquipmentRepository;

@Service
public class EquipmentService
{
	@Autowired
	private EquipmentRepository equipRepo;
	
//	RETURNS ALL THE EQUIPMENT
	public List<Equipment> allEquipment()
	{
		return equipRepo.findAll();
	}
	
//	CREATES EQUIPMENT
	public Equipment createEquipment(Equipment e)
	{
		return equipRepo.save(e);
	}
	
//	RETRIEVES ONE EQUIPMENT
	public Equipment findEquipment(Long id)
	{
		Optional<Equipment> optionalEquip = equipRepo.findById(id);
		if(optionalEquip.isPresent())
		{
			return optionalEquip.get();
		} else
		{
			return null;
		}
	}
	
//	UPDATES ONE EQUIPMENT
	public Equipment updateEquip(Equipment e)
	{
		return equipRepo.save(e);
	}
	
//	DELETES ONE EQUIPMENT
	public void deleteEquip(Long id)
	{
		equipRepo.deleteById(id);
	}
}
