package com.example.demo.service;

import com.example.demo.model.Menu;
import com.example.demo.model.CartItem;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    private static final String CART_SESSION_ATTRIBUTE = "cart";

    public List<CartItem> getCartItems(HttpSession session) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute(CART_SESSION_ATTRIBUTE);
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute(CART_SESSION_ATTRIBUTE, cart);
        }
        return cart;
    }

    public void addToCart(Menu menu, int quantity, HttpSession session) {
        List<CartItem> cart = getCartItems(session);
        boolean itemExists = false;

        for (CartItem item : cart) {
            if (item.getMenu().getMenuId().equals(menu.getMenuId())) {
                item.setQuantity(item.getQuantity() + quantity);
                itemExists = true;
                break;
            }
        }

        if (!itemExists) {
            CartItem newItem = new CartItem(menu, quantity);
            cart.add(newItem);
        }
    }

    public void removeFromCart(Long menuId, HttpSession session) {
        List<CartItem> cart = getCartItems(session);
        cart.removeIf(item -> item.getMenu().getMenuId().equals(menuId));
    }
}
