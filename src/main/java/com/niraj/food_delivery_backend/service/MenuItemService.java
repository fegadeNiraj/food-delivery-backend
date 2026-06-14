package com.niraj.food_delivery_backend.service;

import com.niraj.food_delivery_backend.dto.MenuItemRequestDto;
import com.niraj.food_delivery_backend.dto.MenuItemResponseDto;
import com.niraj.food_delivery_backend.entity.MenuItem;
import com.niraj.food_delivery_backend.entity.Restaurant;
import com.niraj.food_delivery_backend.exception.MenuItemAlreadyExistsException;
import com.niraj.food_delivery_backend.exception.MenuItemNotFoundException;
import com.niraj.food_delivery_backend.exception.RestaurantNotFoundException;
import com.niraj.food_delivery_backend.repository.MenuItemRepository;
import com.niraj.food_delivery_backend.repository.RestaurantRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;

    public MenuItemService(MenuItemRepository menuItemRepository, RestaurantRepository restaurantRepository)
    {
        this.menuItemRepository = menuItemRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public MenuItemResponseDto createMenuItem(MenuItemRequestDto menuItemRequestDto, Long restaurantId)
    {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                ()->new RestaurantNotFoundException(
                        "Restaurant Not Found with ID : "+restaurantId
                )
        );

        MenuItem menuItem = new MenuItem();
        if(menuItemRepository.existsByNameAndRestaurant(menuItemRequestDto.getName(),restaurant)){
           throw new MenuItemAlreadyExistsException(
                   "Menu Item Already Exists with name : "+menuItemRequestDto.getName()+" in restaurant : "+restaurant.getName()
           );
        }
        menuItem.setName(menuItemRequestDto.getName());
        menuItem.setPrice(menuItemRequestDto.getPrice());
        menuItem.setRestaurant(restaurant);

        MenuItem savedMenuItem = menuItemRepository.save(menuItem);

        return new MenuItemResponseDto(
                savedMenuItem.getId(),
                savedMenuItem.getName(),
                savedMenuItem.getPrice(),
                savedMenuItem.getRestaurant().getId()
        );
    }

    public List<MenuItemResponseDto> getMenuItems(Long restaurantId)
    {
        restaurantRepository.findById(restaurantId).orElseThrow(
                ()->new RestaurantNotFoundException(
                        "Restaurant Not Found with ID : "+restaurantId
                )
        );

        List<MenuItem> menuItems= menuItemRepository.findByRestaurantId(restaurantId);

        return menuItems.stream()
                .map(menuItem -> new MenuItemResponseDto(
                        menuItem.getId(),
                        menuItem.getName(),
                        menuItem.getPrice(),
                        menuItem.getRestaurant().getId()
                )).toList();

    }

    @Transactional
    public MenuItemResponseDto updateMenuItem(MenuItemRequestDto menuItemRequestDto, Long menuItemId)
    {

        MenuItem menuItem = menuItemRepository.findById(menuItemId).orElseThrow(
                ()->new MenuItemNotFoundException(
                        "Menu Item Not Found with ID : "+menuItemId
                )
        );

        if(menuItemRepository.existsByNameAndRestaurantAndIdNot(menuItemRequestDto.getName(), menuItem.getRestaurant(), menuItemId))
        {
            throw new MenuItemAlreadyExistsException(
                    "Menu Item Already exists with name : "+menuItemRequestDto.getName()+" in restaurant : "+menuItem.getRestaurant().getName()
            );

        }

        menuItem.setName(menuItemRequestDto.getName());
        menuItem.setPrice(menuItemRequestDto.getPrice());

        return new MenuItemResponseDto(
                menuItem.getId(),
                menuItem.getName(),
                menuItem.getPrice(),
                menuItem.getRestaurant().getId()
        );

    }

}
