package um.edu.uy.business.entities;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "airplanes")
@Builder
public class Airplane {

    @Id
    @GeneratedValue(generator = "airplanes_ids")
    @GenericGenerator(name = "airplanes_ids", strategy = "increment")
    private long id;

    private String licensePlate;

    private String type; //  ej: Airbus A320, Boeing 777, etc

    private int seatCapacity;

    private int luggageCapacity;

    @ManyToOne
    private Airline airline;

    public Airplane(String licensePlate, String type, int seatCapacity, int luggageCapacity, Airline airline) {
        this.licensePlate = licensePlate;
        this.type = type;
        this.seatCapacity = seatCapacity;
        this.luggageCapacity = luggageCapacity;
        this.airline = airline;
    }
}
