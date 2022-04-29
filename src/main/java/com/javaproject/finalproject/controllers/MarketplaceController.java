package com.javaproject.finalproject.controllers;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaproject.finalproject.models.Equipment;
import com.javaproject.finalproject.models.Prize;
import com.javaproject.finalproject.models.User;
import com.javaproject.finalproject.models.UsersEquipment;
import com.javaproject.finalproject.models.UsersPrizes;
import com.javaproject.finalproject.models.Wallet;
import com.javaproject.finalproject.services.EquipmentService;
import com.javaproject.finalproject.services.PrizeService;
import com.javaproject.finalproject.services.UserService;
import com.javaproject.finalproject.services.UsersEquipmentService;
import com.javaproject.finalproject.services.UsersPrizesService;
import com.javaproject.finalproject.services.WalletService;

@Controller
public class MarketplaceController
{
	@Autowired
	private EquipmentService equipServ;
	
	@Autowired
	private UsersEquipmentService usersEquipServ;
	
	@Autowired
	private WalletService walletServ;
	
	@Autowired
	private UserService userServ;
	
	@Autowired
	private PrizeService prizeServ;
	
	@Autowired
	private UsersPrizesService usersPrizeServ;
	
	@RequestMapping("/shop")
	public String marketplace(
			Model model,
			HttpSession session)
	{
		if(session.getAttribute("user_id") == null) {
			return "redirect:/";
		}
		System.out.println(session.getAttribute("lowBalance"));
		User user = userServ.findUserByEmail((String) session.getAttribute("email"));
		Wallet wallet = user.getWallet();
		
		Long currentCoins = wallet.getCoins();
		
		if(currentCoins < 10000) {
			session.setAttribute("lowBalance", true);
		} else {
			session.setAttribute("lowBalance", false);
		}
		
		
		List<Equipment> equipment = equipServ.allEquipment();
		List<Prize> prizes = prizeServ.allPrizes();
		
		Integer randomRiddleNum = (int) Math.floor(Math.random() * 11);
		
		if (randomRiddleNum == 0)
		{
			System.out.println("It landed on 0 so we bumped it up to 1!");
			randomRiddleNum++;
		}
		
		String riddle = "";
		String answer = "";
		
		if (randomRiddleNum == 1)
		{
			riddle = "David's father has three sons: Snap, Crackle, and _____?";
			answer = "DAVID";
		} else if (randomRiddleNum == 2)
		{
			riddle = "I have branches but no fruit, trunk, or leaves. What am I?";
			answer = "BANK";
		} else if (randomRiddleNum == 3)
		{
			riddle = "What word contains all of the twenty-six letters?";
			answer = "ALPHABET";
		} else if (randomRiddleNum == 4)
		{
			riddle = "What do you get when you mix lemons with gun powder?";
			answer = "LEMONADES";
		} else if (randomRiddleNum == 5)
		{
			riddle = "What five-letter word can be read the same upside down or right side up?";
			answer = "SWIMS";
		} else if (randomRiddleNum == 6)
		{
			riddle = "Which word in the dictionary is spelled incorrectly?";
			answer = "INCORRECTLY";
		} else if (randomRiddleNum == 7)
		{
			riddle = "What is the longest word in the English language?";
			answer = "SMILES";
		} else if (randomRiddleNum == 8)
		{
			riddle = "What do you call a three-humped camel?";
			answer = "PREGNANT";
		} else if (randomRiddleNum == 9)
		{
			riddle = "The poor have me; the rich need me. Eat me and you will die. What am I?";
			answer = "NOTHING";
		} else if (randomRiddleNum == 10)
		{
			riddle = "What has a head and a tail, but no body or legs?";
			answer = "COIN";
		}
		
		
		
		model.addAttribute("answered", session.getAttribute("jackpotAnswered"));
		model.addAttribute("riddle", riddle);
		model.addAttribute("answer", answer);
		model.addAttribute("wallet", wallet);
		model.addAttribute("equipment", equipment);
		model.addAttribute("prizes", prizes);
		model.addAttribute("lowBalance", session.getAttribute("lowBalance"));
		
		
		
		return "marketplace.jsp";
	}
	
	@PostMapping("/jackpot")
	public String jackpot(
			@RequestParam(value="jackpot") String userGuess,
			@RequestParam(value="code") String jackpotCode,
			HttpSession session)
	{
		userGuess = userGuess.toUpperCase();
		System.out.println(userGuess);
		System.out.println(jackpotCode);
		if (userGuess.equals(jackpotCode) && (boolean) session.getAttribute("jackpotAnswered") == false)
		{
			System.out.println("You Win the Jackpot!");
			User user = userServ.findUserByEmail((String) session.getAttribute("email"));
			Wallet wallet = user.getWallet();
			
			Long currentCoins = wallet.getCoins();
			Long currentDiamonds = wallet.getDiamonds();
			
			wallet.setCoins(currentCoins + 50000);
			wallet.setDiamonds(currentDiamonds + 100);
			walletServ.updateWallet(wallet);
			
			session.setAttribute("jackpotAnswered", true);
			session.setAttribute("wrongAnswer", false);
			
			
			return "redirect:/shop";
		} else if (!userGuess.equals(jackpotCode) && (boolean) session.getAttribute("jackpotAnswered") == false)
		{
			session.setAttribute("wrongAnswer", true);
			System.out.println("Better luck next time");
			return "redirect:/shop";
		} else
		{
			System.out.println("You already guessed!");
			return "redirect:/shop";
		}
	}
	
	@PostMapping("/shop/buy1Diamond")
	public String buy1Diamond(
			@RequestParam(value="myCoins") Long coins,
			HttpSession session,
			Model model)
	{
		User user = userServ.findUserByEmail((String) session.getAttribute("email"));
		Wallet wallet = user.getWallet();
		
		Long currentCoins = wallet.getCoins();
		Long currentDiamonds = wallet.getDiamonds();
		
		
		if (currentCoins > 10000) {
			Long purchaseCoins = (long) 10000;
			Long purchasedDiamonds = (long) 1;
			System.out.println(purchasedDiamonds);
			Long newDiamondAmount = purchasedDiamonds + currentDiamonds;
			Long purchaseRemainder = (currentCoins - purchaseCoins);
			System.out.println("Your remaining coin balance is " + purchaseRemainder + " coins!");
			wallet.setCoins(purchaseRemainder);
			System.out.println("Your new diamond balance is " + newDiamondAmount + " diamonds!");
			wallet.setDiamonds(newDiamondAmount);
			walletServ.updateWallet(wallet);
		} else
		{
			System.out.println("Not enough coins!");
		}
		
		
		
		return "redirect:/shop";
	}
	
	@PostMapping("/shop/buy5Diamonds")
	public String buy5Diamonds(
			@RequestParam(value="myCoins") Long coins,
			HttpSession session,
			Model model)
	{
		User user = userServ.findUserByEmail((String) session.getAttribute("email"));
		Wallet wallet = user.getWallet();
		
		Long currentCoins = wallet.getCoins();
		Long currentDiamonds = wallet.getDiamonds();
		
		
		if (currentCoins > 50000) {
			Long purchaseCoins = (long) 50000;
			Long purchasedDiamonds = (long) 5;
			System.out.println(purchasedDiamonds);
			Long newDiamondAmount = purchasedDiamonds + currentDiamonds;
			Long purchaseRemainder = (currentCoins - purchaseCoins);
			System.out.println("Your remaining coin balance is " + purchaseRemainder + " coins!");
			wallet.setCoins(purchaseRemainder);
			System.out.println("Your new diamond balance is " + newDiamondAmount + " diamonds!");
			wallet.setDiamonds(newDiamondAmount);
			walletServ.updateWallet(wallet);
		} else
		{
			System.out.println("Not enough coins!");
		}
		
		
		
		return "redirect:/shop";
	}

	@PostMapping("/shop/buy10Diamonds")
	public String buyDiamonds(
			@RequestParam(value="myCoins") Long coins,
			HttpSession session,
			Model model)
	{
		User user = userServ.findUserByEmail((String) session.getAttribute("email"));
		Wallet wallet = user.getWallet();
		
		Long currentCoins = wallet.getCoins();
		Long currentDiamonds = wallet.getDiamonds();
		
		
		if (currentCoins > 100000) {
			Long purchaseCoins = (long) 100000;
			Long purchasedDiamonds = (long) 10;
			System.out.println(purchasedDiamonds);
			Long newDiamondAmount = purchasedDiamonds + currentDiamonds;
			Long purchaseRemainder = (currentCoins - purchaseCoins);
			System.out.println("Your remaining coin balance is " + purchaseRemainder + " coins!");
			wallet.setCoins(purchaseRemainder);
			System.out.println("Your new diamond balance is " + newDiamondAmount + " diamonds!");
			wallet.setDiamonds(newDiamondAmount);
			walletServ.updateWallet(wallet);
		} else
		{
			System.out.println("Not enough coins!");
		}
		
		
		
		return "redirect:/shop";
	}
	
	@PostMapping("/shop/buy25Diamonds")
	public String buy25Diamonds(
			@RequestParam(value="myCoins") Long coins,
			HttpSession session,
			Model model)
	{
		User user = userServ.findUserByEmail((String) session.getAttribute("email"));
		Wallet wallet = user.getWallet();
		
		Long currentCoins = wallet.getCoins();
		Long currentDiamonds = wallet.getDiamonds();
		
		
		if (currentCoins > 250000) {
			Long purchaseCoins = (long) 250000;
			Long purchasedDiamonds = (long) 25;
			System.out.println(purchasedDiamonds);
			Long newDiamondAmount = purchasedDiamonds + currentDiamonds;
			Long purchaseRemainder = (currentCoins - purchaseCoins);
			System.out.println("Your remaining coin balance is " + purchaseRemainder + " coins!");
			wallet.setCoins(purchaseRemainder);
			System.out.println("Your new diamond balance is " + newDiamondAmount + " diamonds!");
			wallet.setDiamonds(newDiamondAmount);
			walletServ.updateWallet(wallet);
		} else
		{
			System.out.println("Not enough coins!");
		}
		
		
		
		return "redirect:/shop";
	}
	
	@PostMapping("/shop/buy50Diamonds")
	public String buy50Diamonds(
			@RequestParam(value="myCoins") Long coins,
			HttpSession session,
			Model model)
	{
		User user = userServ.findUserByEmail((String) session.getAttribute("email"));
		Wallet wallet = user.getWallet();
		
		Long currentCoins = wallet.getCoins();
		Long currentDiamonds = wallet.getDiamonds();
		
		
		if (currentCoins > 500000) {
			Long purchaseCoins = (long) 500000;
			Long purchasedDiamonds = (long) 50;
			System.out.println(purchasedDiamonds);
			Long newDiamondAmount = purchasedDiamonds + currentDiamonds;
			Long purchaseRemainder = (currentCoins - purchaseCoins);
			System.out.println("Your remaining coin balance is " + purchaseRemainder + " coins!");
			wallet.setCoins(purchaseRemainder);
			System.out.println("Your new diamond balance is " + newDiamondAmount + " diamonds!");
			wallet.setDiamonds(newDiamondAmount);
			walletServ.updateWallet(wallet);
		} else
		{
			System.out.println("Not enough coins!");
		}
		
		
		
		return "redirect:/shop";
	}
	
	@PostMapping("/shop/buy100Diamonds")
	public String buy100Diamonds(
			@RequestParam(value="myCoins") Long coins,
			HttpSession session,
			Model model)
	{
		User user = userServ.findUserByEmail((String) session.getAttribute("email"));
		Wallet wallet = user.getWallet();
		
		Long currentCoins = wallet.getCoins();
		Long currentDiamonds = wallet.getDiamonds();
		
		
		if (currentCoins > 1000000) {
			Long purchaseCoins = (long) 1000000;
			Long purchasedDiamonds = (long) 100;
			System.out.println(purchasedDiamonds);
			Long newDiamondAmount = purchasedDiamonds + currentDiamonds;
			Long purchaseRemainder = (currentCoins - purchaseCoins);
			System.out.println("Your remaining coin balance is " + purchaseRemainder + " coins!");
			wallet.setCoins(purchaseRemainder);
			System.out.println("Your new diamond balance is " + newDiamondAmount + " diamonds!");
			wallet.setDiamonds(newDiamondAmount);
			walletServ.updateWallet(wallet);
		} else
		{
			System.out.println("Not enough coins!");
		}
		
		
		
		return "redirect:/shop";
	}
	
	@RequestMapping("/shop/equipment/{id}")
	public String equipmentInfo(
			@PathVariable("id") Long id,
			@ModelAttribute("addEquip") UsersEquipment emptyEquip,
			Model model,
			HttpSession session)
	{	
		if(session.getAttribute("user_id") == null) {
			return "redirect:/";
		}
		User user = userServ.findUserByEmail((String) session.getAttribute("email"));
		Wallet wallet = user.getWallet();
		Equipment equipment = equipServ.findEquipment(id);
		
		if(wallet.getCoins() >= (long) equipment.getPrice()) {
			model.addAttribute("canPurchase", true);
		} else {
			model.addAttribute("canPurchase", false);
		}
		
		model.addAttribute("wallet", wallet);
		model.addAttribute("equipment", equipment);
		model.addAttribute("user_id", session.getAttribute("user_id"));
		
		return "showEquipment.jsp";
	}
	
	@PostMapping("/shop/purchase")
	public String purchase(
			@Valid
			@ModelAttribute("addEquip") UsersEquipment fullEquip,
			BindingResult results,
			HttpSession session)
	{
		User user = userServ.findUserByEmail((String) session.getAttribute("email"));
		
		Wallet wallet = user.getWallet();
		
		if(results.hasErrors())
		{
			System.out.println(results);
			return "redirect:/shop";
		}
		
		
//		CHECK TO SEE IF USER HAS ENOUGH TO PAY FOR ITEM
		
		if (wallet.getCoins() < fullEquip.getPrice())
		{
			System.out.println("Not enough coins!");
			return "redirect:/shop";
		}
		
		Long newCoinAmount = wallet.getCoins() - fullEquip.getPrice();
		wallet.setCoins(newCoinAmount);
		walletServ.updateWallet(wallet);
		
		Instant startTime = Instant.now();
		
		fullEquip.setStartTime(startTime);
		
		usersEquipServ.addEquipment(fullEquip);
		return "redirect:/shop";
	
	}
	
	@RequestMapping("/shop/prizes/{id}")
	public String prizeInfo(
			@PathVariable("id") Long id,
			@ModelAttribute("addPrize") UsersPrizes emptyPrize,
			Model model,
			HttpSession session)
	{
		if(session.getAttribute("user_id") == null) {
			return "redirect:/";
		}
		User user = userServ.findUserByEmail((String) session.getAttribute("email"));
		Wallet wallet = user.getWallet();
		Prize prize = prizeServ.findPrize(id);
		
		if(wallet.getCoins() >= (long) prize.getCoinPrice()) {
			model.addAttribute("canCoinPurchase", true);
		} else {
			model.addAttribute("canCoinPurchase", false);
		}
		
		if(wallet.getDiamonds() >= (long) prize.getDiamondPrice()) {
			model.addAttribute("canDiamondPurchase", true);
		} else {
			model.addAttribute("canDiamondPurchase", false);
		}
		
		model.addAttribute("prize", prize);
		model.addAttribute("wallet", wallet);
		model.addAttribute("user_id", session.getAttribute("user_id"));
		
		
		return "showPrize.jsp";
	}
	
	
	
	
	@PostMapping("/shop/prize/coinPurchase")
	public String coinPurchasePrize(
			@Valid
			@ModelAttribute("addPrize") UsersPrizes fullPrize,
			BindingResult results,
			HttpSession session)
	{
		User user = userServ.findUserByEmail((String) session.getAttribute("email"));
		
		Wallet wallet = user.getWallet();
		
//		CHECK TO SEE IF USER HAS ENOUGH TO PAY FOR ITEM
		
		if (wallet.getCoins() < fullPrize.getCoinPrice())
		{
			System.out.println("Not enough coins!");
			return "redirect:/shop";
		}
		
		Long newCoinAmount = wallet.getCoins() - fullPrize.getCoinPrice();
		wallet.setCoins(newCoinAmount);
		walletServ.updateWallet(wallet);
		
		
		usersPrizeServ.addPrizes(fullPrize);
		return "redirect:/shop";
	
	}
	
	@PostMapping("/shop/prize/diamondPurchase")
	public String diamondPurchasePrize(
			@Valid
			@ModelAttribute("addPrize") UsersPrizes fullPrize,
			BindingResult results,
			HttpSession session)
	{
		User user = userServ.findUserByEmail((String) session.getAttribute("email"));
		
		Wallet wallet = user.getWallet();
		
//		CHECK TO SEE IF USER HAS ENOUGH TO PAY FOR ITEM
		
		if (wallet.getDiamonds() < fullPrize.getDiamondPrice())
		{
			System.out.println("Not enough diamonds!");
			return "redirect:/shop";
		}
		
		Long newDiamondAmount = wallet.getDiamonds() - fullPrize.getDiamondPrice();
		wallet.setDiamonds(newDiamondAmount);
		walletServ.updateWallet(wallet);
		
		
		usersPrizeServ.addPrizes(fullPrize);
		return "redirect:/shop";
	
	}
	
	@RequestMapping(value="/equipment/{id}/cashout", method=RequestMethod.PUT)
	public String cashOut(
			@PathVariable("id") Long id,
			@ModelAttribute("equipment") UsersEquipment equipment,
			HttpSession session)
	{
		UsersEquipment myEquipment = usersEquipServ.findEquipment(id);
		Instant previousTime = myEquipment.getStartTime();
		Instant cashOutTime = Instant.now();
		Long generationTime = Duration.between(previousTime, cashOutTime).toSeconds();
		
		Integer generationVal = myEquipment.getGenerationVal();
		
		Long coinCashOut = generationVal * generationTime;
		
		System.out.println("----------COIN CASHOUT----------");
		System.out.println("Time since generation start: " + generationTime + " seconds!");
		System.out.println("Your equipment has generated a total of " + coinCashOut + " coins since last cash out!");		
		
//		FIND USER
		
		User user = userServ.findUserByEmail((String) session.getAttribute("email"));
		
//		GET USER'S WALLET
		
		Wallet wallet = user.getWallet();
		
//		PULL USERS COIN AMOUNT AND ADD COINS FROM CASHOUT
		
		Long currentCoins = wallet.getCoins();
		
		Long newCoinAmount = currentCoins + coinCashOut;
		
		
//		SAVE NEW COIN / DIAMOND AMOUNT
		
		wallet.setCoins(newCoinAmount);
		walletServ.updateWallet(wallet);
		
		
		
//		SET NEW START TIME FOR EQUIPMENT
		
		Instant startTime = Instant.now();
		equipment.setStartTime(startTime);
		
		usersEquipServ.updateEquip(equipment);
		return "redirect:/profile";
	}
	
//  SELL EQUIPMENT
    
    @DeleteMapping("/equipment/{id}/sell")
    public String destroy(
    		@PathVariable("id") Long id,
    		HttpSession session)
    {
    	if(session.getAttribute("user_id") == null)
		{
			 return "redirect:/";
		}
    	
    	
    	
//		FIND USER    	
    	User user = userServ.findUserByEmail((String) session.getAttribute("email"));
    	
//    	GET SPECIFIC EQUIPMENT INFO AND SELL PRICE
    	UsersEquipment myEquipment = usersEquipServ.findEquipment(id);
    	Long sellAmount = (long) myEquipment.getSellPrice();
    	
    	System.out.println("----------EQUIPMENT SALE----------");
		System.out.println("Congratulations! You just sold your equipment for " + sellAmount + " coins!");
		
    	
//    	GET USER'S WALLET
    	Wallet wallet = user.getWallet();
		Long currentCoins = wallet.getCoins();
		
//		SELL EQUIPMENT
		Long newCoinAmount = currentCoins + sellAmount;
		System.out.println("You now have " + newCoinAmount + " coins in your wallet!");
		
//		SAVE NEW COIN AMOUNT
		wallet.setCoins(newCoinAmount);
		walletServ.updateWallet(wallet);
		
		
    	
    	
        usersEquipServ.deleteEquip(id);
        return "redirect:/profile";
    }
    
//  SELL PRIZE FOR COINS
    
    @DeleteMapping("/prize/{id}/coinSell")
    public String sellCoinPrize(
    		@PathVariable("id") Long id,
    		HttpSession session)
    {
    	if(session.getAttribute("user_id") == null)
		{
			 return "redirect:/";
		}
    	
    	
    	
//		FIND USER    	
    	User user = userServ.findUserByEmail((String) session.getAttribute("email"));
    	
//    	GET SPECIFIC EQUIPMENT INFO AND SELL PRICE
    	UsersPrizes myPrize = usersPrizeServ.findPrize(id);
    	Long sellAmount = (long) myPrize.getSellCoinPrice();
    	
    	System.out.println("----------PRIZE SALE----------");
		System.out.println("Congratulations! You just sold your prize for " + sellAmount + " coins!");
		
    	
//    	GET USER'S WALLET
    	Wallet wallet = user.getWallet();
		Long currentCoins = wallet.getCoins();
		
//		SELL PRIZE
		Long newCoinAmount = currentCoins + sellAmount;
		System.out.println("You now have " + newCoinAmount + " coins in your wallet!");
		
//		SAVE NEW COIN AMOUNT
		wallet.setCoins(newCoinAmount);
		walletServ.updateWallet(wallet);
		
		
    	
    	
        usersPrizeServ.deletePrize(id);
        return "redirect:/profile";
    }
    
//  SELL PRIZE FOR DIAMONDS
    
    @DeleteMapping("/prize/{id}/diamondSell")
    public String sellDiamondPrize(
    		@PathVariable("id") Long id,
    		HttpSession session)
    {
    	if(session.getAttribute("user_id") == null)
		{
			 return "redirect:/";
		}
    	
    	
    	
//		FIND USER    	
    	User user = userServ.findUserByEmail((String) session.getAttribute("email"));
    	
//    	GET SPECIFIC EQUIPMENT INFO AND SELL PRICE
    	UsersPrizes myPrize = usersPrizeServ.findPrize(id);
    	Long sellAmount = (long) myPrize.getSellDiamondPrice();
    	
    	System.out.println("----------PRIZE SALE----------");
		System.out.println("Congratulations! You just sold your prize for " + sellAmount + " diamonds!");
		
    	
//    	GET USER'S WALLET
    	Wallet wallet = user.getWallet();
		Long currentDiamonds = wallet.getDiamonds();
		
//		SELL PRIZE
		Long newDiamondAmount = currentDiamonds + sellAmount;
		System.out.println("You now have " + newDiamondAmount + " diamonds in your wallet!");
		
//		SAVE NEW COIN AMOUNT
		wallet.setDiamonds(newDiamondAmount);
		walletServ.updateWallet(wallet);
		
		
    	
    	
        usersPrizeServ.deletePrize(id);
        return "redirect:/profile";
    }
}
