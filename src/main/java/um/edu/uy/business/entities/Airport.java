package um.edu.uy.business.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@Entity
@Table(name = "airports")
public class Airport {

    @Id
    @GeneratedValue(generator = "airport_ids")
    @GenericGenerator(name = "airport_ids", strategy = "increment")
    private Long id;

    private String name;

    private String type;

    private String IATA;

    public Airport(String name, String type, String IATA) {
        this.name = name;
        this.type = type;
        this.IATA = IATA;
    }

    public Airport() {

    }
}
