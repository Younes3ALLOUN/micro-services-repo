package ma.enset.orderservice.web;

import lombok.AllArgsConstructor;
import ma.enset.orderservice.entities.Order;
import ma.enset.orderservice.model.Customer;
import ma.enset.orderservice.model.Product;
import ma.enset.orderservice.repository.OrderRepository;
import ma.enset.orderservice.repository.ProductItemRepository;
import ma.enset.orderservice.service.CustomerRestClientService;
import ma.enset.orderservice.service.ProductRestClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController @AllArgsConstructor
public class OrderREstController {
    private OrderRepository orderRepository;
    private ProductItemRepository itemRepository;
    private CustomerRestClientService customerRestClientService;
    private ProductRestClientService productRestClientService;
    @GetMapping(path = "fullOrder/{id}")
    public Order getOrder(@PathVariable Long id){
        Order order=orderRepository.findById(id).get();
        Customer customer=customerRestClientService.customerById(order.getCustomerId());
        order.setCustomer(customer);
        order.getProductItems().forEach(productItem ->{
            Product product=productRestClientService.productById(productItem.getProductId());
            productItem.setProduct(product);
        } );
        return order;
    }
}
