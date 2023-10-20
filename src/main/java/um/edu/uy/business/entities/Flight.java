package um.edu.uy.business.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vuelos")
@Builder
public class Flight {

    @Id
    @GeneratedValue(generator = "flight_ids")
    @GenericGenerator(name = "flight_ids", strategy = "increment")
    private Long id;

    @Column(name = "Origen")
    private String origin;

    @Column(name = "Destino")
    private String destiny;

    @Column(name = "Avion")
    private String airplane;

    @Column(name = "Aerolinea")
    private String airline;

    @Column(name = "Hora")
    private Date time;

    public Flight(String origin, String destiny, String airplane, String airline, Date time) {
        this.origin = origin;
        this.destiny = destiny;
        this.airplane = airplane;
        this.airline = airline;
        this.time = time;
    }
}
