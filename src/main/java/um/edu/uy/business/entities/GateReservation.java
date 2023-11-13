package um.edu.uy.business.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Time;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class GateReservation {
    @Id
    @GeneratedValue(generator = "reserves_id")
    @GenericGenerator(name = "reserves", strategy = "increment")
    private Long id;

    @ManyToOne
    private Airport airport;

    @ManyToOne
    private Gate gate;

    private int gateNumber;

    private Date date;

    @ManyToOne
    private Flight designatedFlight;
    private String flightNumber;

    private Time occupiedTime;

    private boolean flightConfirmed;

    public GateReservation(Airport airport, Gate gate, Date date, Flight designatedFlight, Time occupiedTime) {
        this.airport = airport;
        this.gate = gate;
        this.date = date;
        this.designatedFlight = designatedFlight;
        this.occupiedTime = occupiedTime;
        this.flightConfirmed = false;
        this.gateNumber = gate.getGateNumber();
        this.flightNumber = designatedFlight.getFlightNumber();
    }
}
