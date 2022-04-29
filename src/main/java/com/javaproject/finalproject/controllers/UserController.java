package com.javaproject.finalproject.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaproject.finalproject.models.LoginUser;
import com.javaproject.finalproject.models.User;
import com.javaproject.finalproject.models.UsersEquipment;
import com.javaproject.finalproject.models.UsersPrizes;
import com.javaproject.finalproject.models.Wallet;
import com.javaproject.finalproject.services.UserService;
import com.javaproject.finalproject.services.UsersEquipmentService;
import com.javaproject.finalproject.services.UsersPrizesService;
import com.javaproject.finalproject.services.WalletService;

@Controller
public class UserController {
 
	@Autowired
	private UserService userServ;
	
	@Autowired
	private UsersEquipmentService usersEquipServ;
	
	@Autowired
	private WalletService walletServ;
	
	@Autowired
	private UsersPrizesService usersPrizesServ;
 
 @GetMapping("/")
 public String index(Model model,
		 HttpSession session)
 {
	 if(session.getAttribute("user_id") != null)
	 {
		 return "redirect:/profile";
	 }
     // Bind empty User and LoginUser objects to the JSP
     // to capture the form input
     model.addAttribute("newUser", new User());
     model.addAttribute("wallet", new Wallet());
     model.addAttribute("newLogin", new LoginUser());
     return "index.jsp";
 }
 
 @PostMapping("/register")
 public String register(
		 @Valid 
		 @ModelAttribute("newUser") User newUser, 
         BindingResult result, 
         Model model, 
         HttpSession session)
 {
     User userPlaceHolder = userServ.register(newUser, result);
     
     
     // TO-DO Later -- call a register method in the service 
     // to do some extra validations and create a new user!
     
     if(result.hasErrors()) {
         // Be sure to send in the empty LoginUser before 
         // re-rendering the page.
         model.addAttribute("newLogin", new LoginUser());
         return "index.jsp";
     }
     
     // No errors! 
     // TO-DO Later: Store their ID from the DB in session, 
     // in other words, log them in.
     
     session.setAttribute("username", userPlaceHolder.getUsername());
     session.setAttribute("user_id", userPlaceHolder.getId());
     session.setAttribute("email", userPlaceHolder.getEmail());
     session.setAttribute("jackpotAnswered", false);
     session.setAttribute("lowBalance", true);
     
     System.out.println(session.getAttribute("jackpotAnswered"));
     
     Wallet wallet = new Wallet();
     wallet.setUser(userPlaceHolder);
     walletServ.addWallet(wallet);
 
     return "redirect:/profile";
 }
 
 @PostMapping("/login")
 public String login(
		 @Valid 
		 @ModelAttribute("newLogin") LoginUser newLogin, 
         BindingResult result,
         Model model, 
         HttpSession session) {
     
     // Add once service is implemented:
	 
	 User userLoginPlaceHolder = userServ.login(newLogin, result);
     // User user = userServ.login(newLogin, result);
 
     if(result.hasErrors()) {
         model.addAttribute("newUser", new User());
         return "index.jsp";
     }
     
     
     session.setAttribute("user_id", userLoginPlaceHolder.getId());
     session.setAttribute("username", userLoginPlaceHolder.getUsername());
     session.setAttribute("email", userLoginPlaceHolder.getEmail());
     session.setAttribute("jackpotAnswered", false);
     
     System.out.println(session.getAttribute("jackpotAnswered"));
     
     User user = userServ.findUserByEmail((String) session.getAttribute("email"));
     Wallet wallet = user.getWallet();
     
     Long userBalance = wallet.getCoins();
     
     if(userBalance < 10000) {
    	 session.setAttribute("lowBalance", true);
     }
 
     // No errors! 
     // TO-DO Later: Store their ID from the DB in session, 
     // in other words, log them in.
 
     return "redirect:/profile";
 }
 
 
 
 @RequestMapping("/logout")
 public String logout(
		 HttpSession session,
		 Model model)
 {
	 session.removeAttribute("user_id");
	 session.removeAttribute("email");
	 model.addAttribute("newUser", new User());
     model.addAttribute("newLogin", new LoginUser());
	 return "index.jsp";
 }
 
 @RequestMapping("/profile")
 public String profile(
		 HttpSession session,
		 Model model,
		 @ModelAttribute("updateEquip") UsersEquipment currentEquip)
 {
	 if(session.getAttribute("user_id") == null)
	 {
		 return "redirect:/";
	 }
	 Long id = (Long) session.getAttribute("user_id");
	 List<UsersEquipment> usersEquipment = usersEquipServ.findMyEquipment(id);
	 List<UsersPrizes> usersPrizes = usersPrizesServ.findMyPrizes(id);
	 User user = userServ.findUserByEmail((String) session.getAttribute("email"));
     Wallet wallet = user.getWallet();
     
     Long userBalance = wallet.getCoins();
     
     if(userBalance > 10000) {
    	 session.setAttribute("lowBalance", false);
     }
     
     
	 
     UsersEquipment singleEquipment = usersEquipServ.findEquipment(id);
     model.addAttribute("equipment", singleEquipment);
     
     model.addAttribute("wallet", wallet);
	 model.addAttribute("equipment", usersEquipment);
	 model.addAttribute("prizes", usersPrizes);
	 model.addAttribute("user_id", session.getAttribute("user_id"));
	 
	 return "profile.jsp";
 }
 
}