package um.edu.uy.business.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@Entity
@Table(name = "airplanes")

public class Airplane {

    @Id
    @GeneratedValue(generator = "airplanes_ids")
    @GenericGenerator(name = "airplanes_ids", strategy = "increment")
    private long id;

    @Column(name = "Matrícula")
    private String licensePlate;

    @Column(name = "Tipo")
    private String type; //  ej: Airbus A320, Boeing 777, etc

    @Column(name = "Capacidad asientos")
    private int seatCapacity;

    @Column(name = "Capacidad bultos")
    private int luggageCapacity;

    public Airplane(String licensePlate, String type, int seatCapacity, int luggageCapacity) {
        this.licensePlate = licensePlate;
        this.type = type;
        this.seatCapacity = seatCapacity;
        this.luggageCapacity = luggageCapacity;
    }

    public Airplane() {

    }
}
