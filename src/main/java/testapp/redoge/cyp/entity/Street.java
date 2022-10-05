package testapp.redoge.cyp.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Street {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name= "city_id", nullable = false)
    private City cities;
}
