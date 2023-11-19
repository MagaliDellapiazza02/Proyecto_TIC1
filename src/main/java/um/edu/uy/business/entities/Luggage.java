package um.edu.uy.business.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "luggage")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Luggage {

    @Id
    @GeneratedValue(generator="luggages_ids")
    @GenericGenerator(name="luggages_ids", strategy = "increment")
    private long id;

    private int weight;

    private String trackingCode;

    private String state;

    @ManyToOne
    private Passenger passenger;
    private String passport;

    @ManyToOne
    private Flight flight;
    private String flightNumber;

    public Luggage(Passenger p, Flight f, int weight, String trackingNumber) {
        this.passenger = p;
        this.flight = f;
        this.weight = weight;
        this.trackingCode = trackingNumber;
        this.state = "Transito";
        this.passport = p.getPassport();
        this.flightNumber = f.getFlightNumber();
    }
}