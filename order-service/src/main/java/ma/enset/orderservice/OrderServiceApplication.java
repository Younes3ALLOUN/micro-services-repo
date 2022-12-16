package ma.enset.orderservice;

import ma.enset.orderservice.entities.Order;
import ma.enset.orderservice.entities.ProductItem;
import ma.enset.orderservice.enums.OrderStatus;
import ma.enset.orderservice.model.Customer;
import ma.enset.orderservice.model.Product;
import ma.enset.orderservice.repository.OrderRepository;
import ma.enset.orderservice.repository.ProductItemRepository;
import ma.enset.orderservice.service.CustomerRestClientService;
import ma.enset.orderservice.service.ProductRestClientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.reactive.filter.OrderedHiddenHttpMethodFilter;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ProductItemRepository productItemRepository,
							OrderRepository orderRepository,
							CustomerRestClientService customerService,
							ProductRestClientService productservice){
		return args -> {
			List<Customer> customerCollection= customerService.allcustomers().getContent().stream().toList();
			List<Product>  productCollection= productservice.allproducts().getContent().stream().toList();
			Customer customer=customerService.customerById(1L);
			Random random=new Random();

			for (int i = 0; i < 20; i++) {
				Order order=Order.builder()
						.createdAt(new Date())
						.customerId(customerCollection.get(random.nextInt(customerCollection.size())).getId())
						.status(Math.random()>0.5? OrderStatus.CREATED:OrderStatus.PENDING)
						.build();
				Order saved = orderRepository.save(order);
				for (int j = 0; j < productCollection.size(); j++) {
					if (Math.random()>0.70){
						ProductItem productItem= ProductItem.builder()
								.order(saved)
								.productId(productCollection.get(j).getId())
								.price(productCollection.get(j).getPrice())
								.quantity(1+random.nextInt(10))
								.discount(Math.random()).build();
						productItemRepository.save(productItem);
					}

					
				}
				
			}


		};
	}

}
