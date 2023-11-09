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

    private String country;

    private String type;

    private String IATA;

    @OneToMany(mappedBy = "airport")
    private List<Gate> gates;

    @OneToMany(mappedBy = "airport")
    private List<Runway> runways;

    public Airport(String name, String country, String type, String IATA) {
        this.name = name;
        this.country = country;
        this.type = type;
        this.IATA = IATA;
        gates = new LinkedList<Gate>();
    }

    public Airport() {

        gates = new LinkedList<Gate>();
    }
}
