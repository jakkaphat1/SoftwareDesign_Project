package com.example.demo.controller;

import com.example.demo.model.Menu;
import com.example.demo.model.CartItem;
import com.example.demo.service.CartService;
import com.example.demo.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private MenuService menuService;

    @GetMapping
    public String viewCart(HttpSession session, Model model) {
        List<CartItem> cartItems = cartService.getCartItems(session);
        model.addAttribute("cartItems", cartItems);
        return "cart";
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam Long menuId, @RequestParam int quantity, HttpSession session) {
        Menu menu = menuService.getMenuById(menuId);
        cartService.addToCart(menu, quantity, session);
        return "redirect:/cart";
    }

    // เมธอดสำหรับลบรายการอาหารออกจากตะกร้า
    @PostMapping("/remove")
    public String removeFromCart(@RequestParam Long menuId, HttpSession session) {
        cartService.removeFromCart(menuId, session);
        return "redirect:/cart";
    }
}
