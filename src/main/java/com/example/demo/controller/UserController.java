package com.example.demo.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.User.EmpUserDatailsService;
import com.example.demo.User.UserDetail;
import com.example.demo.User.UserDto;
import com.example.demo.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	EmpUserDatailsService empUserDatailsService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/registration")
	public String getRegistrationPage(Model model) {
		model.addAttribute("user", new UserDto());
		return "register";
	}
	
//	@PostMapping("/registration")
//	public String saveUser(@ModelAttribute("user") UserDto userDto,Model model) {
//		userService.save(userDto);
//		model.addAttribute("message","Registration Successfully");
//		return "register";
//	}
	@PostMapping("/registration")
	public String saveUser(@ModelAttribute("user") UserDto userDto, RedirectAttributes redirectAttributes) {
	    try {
	        userService.save(userDto);
	        redirectAttributes.addFlashAttribute("message", "Registration Successfully");
	        return "redirect:/login";  // Redirect ไปยังหน้า login
	    } catch (Exception e) {
	        redirectAttributes.addFlashAttribute("error", "Registration failed: " + e.getMessage());
	        return "redirect:/registration";
	    }
	}
	
	@GetMapping("/login")
	public String getLoginPage(Model model) {
		return "login";
	}
	
	@GetMapping("/user-page")
	public String userPage(Model model,Principal principal) {
		UserDetails userDetails = empUserDatailsService.loadUserByUsername(principal.getName());
		model.addAttribute("user",userDetails);
		return "user";
	}
	
	@GetMapping("/admin-page")
	public String adminPage(Model model,Principal principal) {
		UserDetails userDetails = empUserDatailsService.loadUserByUsername(principal.getName());
		model.addAttribute("user",userDetails);
		return "admin/admin";
	}
	
}
