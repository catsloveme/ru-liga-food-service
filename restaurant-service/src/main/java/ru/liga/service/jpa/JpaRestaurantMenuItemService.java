package ru.liga.service.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.dto.request.RestaurantMenuItemRequest;
import ru.liga.dto.response.RestaurantMenuItemResponse;
import ru.liga.entity.Restaurant;
import ru.liga.entity.RestaurantMenuItem;
import ru.liga.exception.DataNotFoundException;
import ru.liga.mapping.RestaurantMenuItemMapper;
import ru.liga.mapping.RestaurantMenuItemRequestMapper;
import ru.liga.repository.RestaurantMenuItemRepository;
import ru.liga.repository.RestaurantRepository;
import ru.liga.api.RestaurantMenuItemService;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class JpaRestaurantMenuItemService implements RestaurantMenuItemService {

    private final RestaurantMenuItemRepository jpaRestaurantMenuItemRepository;

    private final RestaurantRepository jpaRestaurantRepository;
    private final RestaurantMenuItemMapper restaurantMenuItemMapper;
    private final RestaurantMenuItemRequestMapper restaurantMenuItemRequestMapper;

    public RestaurantMenuItemResponse findRestaurantMenuItemById(Long id) {
        RestaurantMenuItem restaurantMenuItem = jpaRestaurantMenuItemRepository.findById(id).orElseThrow(() ->
            new DataNotFoundException(String.format("Restaurant menu item id = %d not found", id)));
        return restaurantMenuItemMapper.toDto(restaurantMenuItem);
    }

    public RestaurantMenuItemResponse addRestaurantMenuItem(RestaurantMenuItemRequest request) {
        RestaurantMenuItem restaurantMenuItem = restaurantMenuItemRequestMapper.toEntity(request);
        Long restaurantId = request.getRestaurantId();
        Restaurant restaurant = jpaRestaurantRepository.findById(restaurantId).orElseThrow(() ->
            new DataNotFoundException(String.format("Restaurant id = %d not found", restaurantId)));
        restaurantMenuItem.setRestaurant(restaurant);
        jpaRestaurantMenuItemRepository.save(restaurantMenuItem);
        return restaurantMenuItemMapper.toDto(restaurantMenuItem);
    }

    @Transactional
    public void deleteRestaurantMenuItemById(Long id) {
        jpaRestaurantMenuItemRepository.deleteById(id);
    }

    @Transactional
    public void updatePrice(BigDecimal price, Long id) {
        jpaRestaurantMenuItemRepository.updatePrice(price, id);
    }
}
