package um.edu.uy.business.entities;

import jakarta.persistence.*;
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
    @GeneratedValue(generator="reserves_id")
    @GenericGenerator(name="reserves", strategy = "increment")
    private Long id;

    private Long reservationNumber;

    @ManyToOne
    private Airport airport;

    @ManyToOne
    private Gate gate;

    private Date date;

    @ManyToOne
    private Flight designatedFlight;

    private Time occupiedTime;

}
