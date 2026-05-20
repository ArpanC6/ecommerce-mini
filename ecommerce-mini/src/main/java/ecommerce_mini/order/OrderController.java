package ecommerce_mini.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private ecommerce_mini.order.OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> placeOrder(
            @RequestParam Long customerId,
            @RequestParam Long productId,
            @RequestParam int quantity) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderService.placeOrder(customerId, productId, quantity));
    }
}