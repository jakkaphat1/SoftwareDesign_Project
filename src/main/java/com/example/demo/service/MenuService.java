package com.example.demo.service;

import com.example.demo.model.Menu;
import com.example.demo.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }

    public void saveMenu(Menu menu) {
        menuRepository.save(menu);
    }

    public Menu getMenuById(Long menuId) {
        return menuRepository.findById(menuId).orElseThrow(() -> new RuntimeException("Menu not found"));
    }

    public void deleteMenu(Long menuId) {
        menuRepository.deleteById(menuId);
    }
}
