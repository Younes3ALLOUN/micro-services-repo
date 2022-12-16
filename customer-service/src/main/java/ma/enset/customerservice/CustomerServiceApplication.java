package ma.enset.customerservice;

import ma.enset.customerservice.entities.Customer;
import ma.enset.customerservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner clr(CustomerRepository repository){
		return args -> {
			repository.saveAll(List.of(
					Customer.builder().name("ahmed").Email("ahmed@gmail.com").build(),
					Customer.builder().name("monir").Email("monir@gmail.com").build(),
					Customer.builder().name("marouan").Email("marouan@gmail.com").build()
			));
			repository.findAll().forEach(System.out::println);
		};

	}
}
