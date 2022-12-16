package ma.enset.orderservice.service;

import ma.enset.orderservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@FeignClient(name = "INVENTORY-SERVICE")
public interface ProductRestClientService {
    @GetMapping(path = "/products/{id}?projection=fullProduct")
    Product productById(@PathVariable Long id);

    @GetMapping(path = "/products?projection=fullProduct")
    PagedModel<Product> allproducts();

}
