package um.edu.uy.business.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Time;
import java.util.Date;

public class RunwayReservation {
    @Id
    @GeneratedValue(generator="reserves_id")
    @GenericGenerator(name="reserves", strategy = "increment")
    private Long id;

    private Long reservationNumber;

    @ManyToOne
    private Airport airport;

    @ManyToOne
    private Runway runway;

    private Date date;

    @ManyToOne
    private Flight designatedFlight;

    private Time occupiedTime;


}
