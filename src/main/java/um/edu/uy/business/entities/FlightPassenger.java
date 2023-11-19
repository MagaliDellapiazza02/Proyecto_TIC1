package um.edu.uy.business.entities;

import jakarta.persistence.*;
import lombok.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "passengers_flight")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FlightPassenger {
    @Id
    @GeneratedValue(generator="flightPassengers_ids")
    @GenericGenerator(name="flightPassengers_ids", strategy = "increment")
    private Long id;

    @ManyToOne
    private Flight flight;

    @ManyToOne
    private Passenger passenger;

    private boolean checkedIn;

    public FlightPassenger(Flight flight, Passenger passenger) {
        this.flight = flight;
        this.passenger = passenger;
        this.checkedIn = false;
    }
}
