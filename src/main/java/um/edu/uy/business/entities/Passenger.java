package um.edu.uy.business.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "passengers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Passenger {

    @Id
    @GeneratedValue(generator="passengers_ids")
    @GenericGenerator(name="passengers_ids", strategy = "increment")
    private long id;

    private long document;

    private String passport;

    private String nationality;

    private String name;

    private String mail;

    private String password;

    //@OneToMany //relación 1-n entre pasajero y equipaje
    //private List<Luggage> luggageList = new ArrayList<>();

    //@ManyToMany(fetch = FetchType.EAGER)
    //private List<Flight> flights = new ArrayList<>();

    @OneToMany(mappedBy = "passenger")
    private List<FlightPassenger> flightPassengers = new ArrayList<>();

    public Passenger(long document, String passport, String nationality, String name, String mail, String password) {
        this.document = document;
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.passport = passport;
        this.nationality = nationality;
    }
}
