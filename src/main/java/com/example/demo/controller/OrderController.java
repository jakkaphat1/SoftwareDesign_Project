package com.example.demo.controller;

import com.example.demo.model.CartItem;
import com.example.demo.model.Menu;
import com.example.demo.model.Order;
import com.example.demo.model.OrderItem;
import com.example.demo.service.CartService;
import com.example.demo.service.MenuService;
import com.example.demo.service.OrderService;


import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private OrderService orderService;
    
    @Autowired
    private CartService cartService;

    	
    @PostMapping("/confirm")
    public String confirmOrder(HttpSession session, Model model) {
        List<CartItem> cartItems = cartService.getCartItems(session);
        if (!cartItems.isEmpty()) {
            Order order = new Order();
            order.setOrderDate(LocalDateTime.now());
            double totalAmount = 0.0;

            for (CartItem cartItem : cartItems) {
                OrderItem orderItem = new OrderItem();
                orderItem.setMenu(cartItem.getMenu());
                orderItem.setQuantity(cartItem.getQuantity());
                order.addOrderItem(orderItem);
                totalAmount += cartItem.getMenu().getPrice() * cartItem.getQuantity();
            }

            order.setTotalAmount(totalAmount);
            orderService.saveOrder(order);
            session.removeAttribute("cart"); // ล้างตะกร้าหลังยืนยันคำสั่งซื้อ
        }

        model.addAttribute("message", "คำสั่งซื้อของคุณได้รับการยืนยันแล้ว!");
        return "orderConfirmation";
    }
    
    @PostMapping("/place")
    public String placeOrder(@RequestParam Long menuId, @RequestParam int quantity) {
        Menu menu = menuService.getMenuById(menuId);
        if (menu != null) {
            Order order = new Order();
            order.setOrderDate(LocalDateTime.now());
            order.setTotalAmount(menu.getPrice() * quantity);

            OrderItem orderItem = new OrderItem();
            orderItem.setMenu(menu);
            orderItem.setQuantity(quantity);
            order.addOrderItem(orderItem);

            orderService.saveOrder(order);
        }
        return "redirect:/order/summary"; // เปลี่ยนเส้นทางไปยังหน้า summary
    }
}
