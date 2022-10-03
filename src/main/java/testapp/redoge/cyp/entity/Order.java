package testapp.redoge.cyp.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
//TODO: entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private User employeeUser;
    private User customerUser;
    private BigDecimal price;
    private Address address;
    private OrderStatus orderStatus;
    private Date date;
    private Feedback feedback;
}
