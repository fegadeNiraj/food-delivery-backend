package com.niraj.food_delivery_backend.controller;


import com.niraj.food_delivery_backend.dto.MenuItemRequestDto;
import com.niraj.food_delivery_backend.dto.MenuItemResponseDto;
import com.niraj.food_delivery_backend.service.MenuItemService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/restaurants")
public class MenuItemController {

    private final MenuItemService menuItemService;

    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @PostMapping("/{restaurantId}/menu-items")
    public MenuItemResponseDto createMenuItem(@Valid @RequestBody MenuItemRequestDto menuItemRequestDto, @PathVariable Long restaurantId)
    {
        return menuItemService.createMenuItem(menuItemRequestDto,restaurantId);
    }

    //get Menu items
    @GetMapping("/{restaurantID}/menu-items")
    public List<MenuItemResponseDto> getMenuItems(@PathVariable Long restaurantID)
    {
        return menuItemService.getMenuItems(restaurantID);
    }

}
