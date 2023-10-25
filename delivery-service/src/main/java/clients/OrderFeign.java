package clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "order-service", url = "http://localhost:8083")
public interface OrderFeign {
    @PatchMapping("/order/{orderId}/courier/{courierId}")
    ResponseEntity<Void> updateCourierId(@PathVariable Long orderId, @PathVariable Long courierId);

}
