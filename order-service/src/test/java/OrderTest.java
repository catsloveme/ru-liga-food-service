import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.liga.api.OrderService;
import ru.liga.dto.request.CreateOrderRequest;
import ru.liga.dto.request.MenuItem;
import ru.liga.dto.response.CreateOrderResponse;
import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
public class OrderTest {

    private final OrderService orderService;

    @Transactional
    @Rollback
    @Test
    public void methodAddOrder_CreateOrderRequest_Success() {
        Long customerId = 1L;
        Long restaurantId = 1L;
        List<MenuItem> menuItems = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            menuItems.add(MenuItem
                .builder()
                .quantity(1)
                .restaurantMenuItemId(1L)
                .build());
        }

        CreateOrderRequest request = CreateOrderRequest
            .builder()
            .customerId(customerId)
            .restaurantId(restaurantId)
            .menuItems(menuItems)
            .build();

        CreateOrderResponse response = orderService.addOrder(request);
        CreateOrderResponse expectedResponse = CreateOrderResponse
            .builder()
            .secretPaymentUrl(null)
            .estimatedTimeOfArrival(OffsetDateTime.now().plusHours(1L))
            .build();

        assertThat(response.getSecretPaymentUrl()).isEqualTo(expectedResponse.getSecretPaymentUrl());
        assertThat(response.getEstimatedTimeOfArrival()).isEqualTo(expectedResponse.getEstimatedTimeOfArrival());

    }
}
