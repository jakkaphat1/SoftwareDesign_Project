package com.example.demo.controller;

import com.example.demo.model.Menu;
import com.example.demo.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping
    public String listMenus(Model model) {
        model.addAttribute("menus", menuService.getAllMenus());
        return "menu"; // หน้า menu.html
    }
}
