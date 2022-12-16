package ma.enset.inventoryservice;

import ma.enset.inventoryservice.entities.Product;
import ma.enset.inventoryservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}
@Bean
	CommandLineRunner start(ProductRepository repository){
		return args -> {
			repository.saveAll(List.of(
					Product.builder().name("dell").price(3000.23).qte(5).build(),
					Product.builder().name("hp").price(4500).qte(10).build(),
					Product.builder().name("accer").price(6050.23).qte(3).build()
			));
			repository.findAll().forEach(System.out::println);
		};
	}
}
