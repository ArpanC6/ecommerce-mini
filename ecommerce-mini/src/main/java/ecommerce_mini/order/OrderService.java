package ecommerce_mini.order;

import ecommerce_mini.customer.Customer;
import ecommerce_mini.customer.CustomerService;
import ecommerce_mini.order.Order;
import ecommerce_mini.order.OrderRepository;
import ecommerce_mini.product.Product;
import ecommerce_mini.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Order placeOrder(Long customerId, Long productId, int quantity) {

        // Customer and Product find
        Customer customer = customerService.getCustomerById(customerId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found: " + productId));

        // Stock check
        if (product.getStock() < quantity) {
            throw new RuntimeException("Not enough stock! Available: " + product.getStock());
        }

        // Stock
        product.setStock(product.getStock() - quantity);
        productRepository.save(product);

        // Order save
        Order order = new Order(customer, product, quantity);
        return orderRepository.save(order);
    }
}