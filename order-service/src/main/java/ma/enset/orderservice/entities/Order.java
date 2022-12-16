package ma.enset.orderservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.orderservice.enums.OrderStatus;
import ma.enset.orderservice.model.Customer;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Entity @Data @AllArgsConstructor @NoArgsConstructor
@Builder
@Table(name = "ORDERS")
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date createdAt;
    private OrderStatus status;
    private Long customerId;
    @Transient
    private Customer customer;
    @OneToMany(mappedBy = "order")
    private List<ProductItem> productItems;
}
