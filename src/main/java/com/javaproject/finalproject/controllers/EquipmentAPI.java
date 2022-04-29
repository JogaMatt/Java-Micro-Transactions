package com.javaproject.finalproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaproject.finalproject.models.Equipment;
import com.javaproject.finalproject.services.EquipmentService;

@RestController
public class EquipmentAPI
{
	@Autowired
	private EquipmentService equipServ;
	
	@RequestMapping("/api/equipment")
	public List<Equipment> index()
	{
		return equipServ.allEquipment();
	}
	
	@PostMapping("/api/equipment")
	public Equipment create(
			@ModelAttribute("newEquipment") Equipment equip)
	{
		return equipServ.createEquipment(equip);
	}
	
	
}
