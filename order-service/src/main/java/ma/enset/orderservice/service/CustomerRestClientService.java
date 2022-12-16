package ma.enset.orderservice.service;

import ma.enset.orderservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerRestClientService {
    @GetMapping(path = "/customers/{id}?projection=fullCustomer")
    Customer customerById(@PathVariable Long id);

    @GetMapping(path = "/customers?projection=fullCustomer")
    PagedModel<Customer> allcustomers();

}
