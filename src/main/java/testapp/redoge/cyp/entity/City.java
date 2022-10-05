package testapp.redoge.cyp.entity;

import javax.persistence.*;
import java.util.List;
@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @OneToMany(mappedBy ="cities")
    private List<Street> streets;
}
