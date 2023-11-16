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
public class RunwayReservation {
    @Id
    @GeneratedValue(generator = "reserves_id")
    @GenericGenerator(name = "reserves", strategy = "increment")
    private Long id;

    @ManyToOne
    private Airport airport;

    @ManyToOne
    private Runway runway;

    private int runwayNumber;

    private Date date;

    @ManyToOne
    private Flight designatedFlight;
    private String flightNumber;

    private Time occupiedTime;

    private boolean flightConfirmed;

    public RunwayReservation(Airport airport, Runway runway, Date date, Flight designatedFlight, Time occupiedTime) {
        this.airport = airport;
        this.runway = runway;
        this.date = date;
        this.designatedFlight = designatedFlight;
        this.occupiedTime = occupiedTime;
        this.flightConfirmed = false;
        this.runwayNumber = runway.getRunwayNumber();
        this.flightNumber = designatedFlight.getFlightNumber();
    }
}
