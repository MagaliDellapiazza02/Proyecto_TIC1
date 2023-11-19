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

    @ManyToMany
    private List<Flight> flights = new ArrayList<>();

    public Luggage(int weight, String trackingNumber) {
        this.weight = weight;
        this.trackingCode = trackingNumber;
    }
}