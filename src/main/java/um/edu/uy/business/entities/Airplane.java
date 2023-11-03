package um.edu.uy.business.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

    public Airplane(String licensePlate, String type, int seatCapacity, int luggageCapacity) {
        this.licensePlate = licensePlate;
        this.type = type;
        this.seatCapacity = seatCapacity;
        this.luggageCapacity = luggageCapacity;
    }
}
