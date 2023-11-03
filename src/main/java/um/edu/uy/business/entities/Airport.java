package um.edu.uy.business.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.LinkedList;
import java.util.List;

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

    @OneToMany(mappedBy = "airport")
    private List<Gate> gates;

    public Airport(String name, String type, String IATA) {
        this.name = name;
        this.type = type;
        this.IATA = IATA;
        gates = new LinkedList<Gate>();
    }

    public Airport() {

        gates = new LinkedList<Gate>();
    }
}
