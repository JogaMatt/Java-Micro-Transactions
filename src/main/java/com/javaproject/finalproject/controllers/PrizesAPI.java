package com.javaproject.finalproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaproject.finalproject.models.Prize;
import com.javaproject.finalproject.services.PrizeService;

@RestController
public class PrizesAPI
{
	@Autowired
	private PrizeService prizeServ;
	
	@RequestMapping("/api/prizes")
	public List<Prize> prizes() {
		return prizeServ.allPrizes();
	}
	
	@PostMapping("/api/prizes")
	public Prize create(
			@ModelAttribute("newPrize") Prize prize)
	{
		return prizeServ.createPrize(prize);
	}
}
